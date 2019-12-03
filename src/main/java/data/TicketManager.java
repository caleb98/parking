package data;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class TicketManager {

	private HashMap<Integer, Pair<Transaction, Card>> outstandingTransactions = new HashMap<>();
	private ArrayList<ParkingLot> lots = new ArrayList<>();
	private ArrayList<Transaction> completedTransactions = new ArrayList<Transaction>();

	public TicketManager() {
		lots = loadLots();
	}

	public boolean addLot(ParkingLot lot) {
		lot.setId(lots.size());
		return lots.add(lot);
	}

	public boolean removeLot(ParkingLot lot) {
		boolean success = lots.remove(lot);
		for (int i = 0; i < lots.size(); ++i) {
			lots.get(i).setId(i);
		}

		return success;
	}

	public HashMap<Integer, Pair<Transaction, Card>> getOutstandingTransactions() {
		return outstandingTransactions;
	}

	public ArrayList<Transaction> getCompletedTransactions() {
		return completedTransactions;
	}

	/**
	 * Begins a transaction given the card that will be used for payment. This
	 * function should be called whenever a customer enters the lot.
	 * 
	 * @param card payment card
	 * @return true if the transaction was created and the customer may park; false
	 *         otherwise
	 */
	public boolean startTransaction(Card card, int lotIndex) {
		// get open lot, and check if valid
		ParkingLot openLot = lots.get(lotIndex);
		if (openLot == null)
			return false;

		LotSection openSection = openLot.getOpenLotSection();

		// create new transaction
		Transaction transaction = new Transaction(completedTransactions.size() + outstandingTransactions.size(),
				openLot.getHourlyRate(), openLot, openSection);

		// fill lot section spot
		transaction.sectionUsed.fillSpot();

		// add to outstanding transactions
		outstandingTransactions.put(transaction.transactionId, new Pair<>(transaction, card));
		transaction.lotUsed.printTicket(transaction);
		return true;
	}

	/**
	 * Completes an outstanding transaction given the transaction ID. This function
	 * should be called whenever a customer leaves the lot.
	 * 
	 * @param transactionId id of the transaction to complete
	 * @return true if the transaction was successfully completed; false otherwise
	 */
	public boolean completeTransaction(int transactionId) {
		// Get the transaction based on its ID
		Pair<Transaction, Card> transactionInfo = outstandingTransactions.get(transactionId);

		// Unable to find transaction
		if (transactionInfo == null) {
			return false;
		}

		// Pull transaction and card info
		Transaction transaction = transactionInfo.a;
		Card card = transactionInfo.b;

		// Charge the balance to the card
		// TODO: calculate amount to charge people for time parked.
		transaction.closeTransaction();
		PaymentManager.subtractBalance(transaction.getTotalCost(), card);

		// Remove the transaction from outstanding and insert it in the
		// completed transactions list, dumping card info.
		outstandingTransactions.remove(transactionId);
		completedTransactions.add(transaction);

		// Set an open spot in the lot where this customer was parked.
		transaction.sectionUsed.setOpen();

		// Print receipt
		transaction.lotUsed.printReceipt(transaction);
		return true;
	}

	/**
	 * Searches the managed lots for an open lot section in which a new customer
	 * could park.
	 * 
	 * @return a lot section with empty spots; null if all sections are full
	 */
	public LotSection getOpenLotSection() {
		LotSection section = null;
		for (ParkingLot lot : lots) {
			if (lot.getOpenLotSection() == null) {
				section = lot.getOpenLotSection();
				break;
			}
		}
		return section;
	}

	/**
	 * Searches the managed lots for an open lot that has space for a new customer
	 * to park.
	 * 
	 * @return a lot with empty spots; null if all lots are full
	 */
	public ParkingLot getOpenLot() {
		ParkingLot open = null;
		for (ParkingLot lot : lots) {
			if (lot.getNumOpenSections() > 0) {
				open = lot;
				break;
			}
		}
		return open;
	}

	/**
	 * @return the total number lots managed by this manager
	 */
	public int getNumLots() {
		return lots.size();
	}

	/**
	 * @return gets the lots managed by this manager
	 */
	public ArrayList<ParkingLot> getLots() {
		return lots;
	}

	public ArrayList<ParkingLot> loadLots() {
		ArrayList<ParkingLot> result = new ArrayList<ParkingLot>();

		try {
			File fXmlFile = new File("LotStorage.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder;
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();

			NodeList nodes = doc.getDocumentElement().getChildNodes();

			for (int i = 0; i < nodes.getLength(); i++) {
				if (nodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
					Element currentElement = (Element) nodes.item(i);

					ParkingLot newLot = new ParkingLot(i, currentElement.getElementsByTagName("lotName").item(0).getTextContent());

					// For each lot section in the lot
					for (int j = 0; j < currentElement.getElementsByTagName("lotSection").getLength(); j++) {
						Element sectionElement = (Element) currentElement.getElementsByTagName("lotSection").item(j);

						String sectionName = sectionElement.getElementsByTagName("name").item(0).getFirstChild().getTextContent();
						int sectionMaxSpots = Integer.parseInt(sectionElement.getElementsByTagName("totalSpots").item(0).getFirstChild().getTextContent());

						newLot.addSection(new LotSection(sectionName, sectionMaxSpots));
					}
					result.add(newLot);
				}

			}

		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}

		 return result;
	}

	/*
	 * public void saveData() { try { File fXmlFile = new File("LotStorage.xml");
	 * DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	 * DocumentBuilder dBuilder; dBuilder = dbFactory.newDocumentBuilder(); Document
	 * doc = dBuilder.parse(fXmlFile); doc.getDocumentElement().normalize();
	 * 
	 * Element newLot = doc.createElement("lot");
	 * 
	 * Element root = doc.getDocumentElement();
	 * 
	 * Element nameElement = doc.createElement("lotName"); Element idElement =
	 * doc.createElement("lotId"); Element rateElement =
	 * doc.createElement("hourlyRate");
	 * 
	 * nameElement.appendChild(doc.createTextNode(lotName));
	 * idElement.appendChild(doc.createTextNode("" + lotId));
	 * rateElement.appendChild(doc.createTextNode("" + rate));
	 * 
	 * newLot.appendChild(nameElement); newLot.appendChild(idElement);
	 * newLot.appendChild(rateElement);
	 * 
	 * root.appendChild(newLot);
	 * 
	 * TransformerFactory factory = TransformerFactory.newInstance(); Transformer
	 * transformer = factory.newTransformer(); DOMSource domSource = new
	 * DOMSource(doc);
	 * 
	 * transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	 * transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount",
	 * "2");
	 * 
	 * StreamResult streamResult = new StreamResult(new File("LotStorage.xml"));
	 * transformer.transform(domSource, streamResult);
	 * 
	 * } catch (ParserConfigurationException | SAXException | IOException |
	 * TransformerException e) { e.printStackTrace(); } }
	 */

}
