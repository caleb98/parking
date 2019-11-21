package data;

import java.util.ArrayList;
import java.util.HashMap;

public class TicketManager {
	
	private HashMap<Integer, Pair<Transaction, Card>> outstandingTransactions = new HashMap<>();
	private ArrayList<ParkingLot> lots = new ArrayList<>();
	private ArrayList<Transaction> completedTransactions = new ArrayList<Transaction>();
	
	public boolean addLot(ParkingLot lot) {
		lot.setId(lots.size());
		return lots.add(lot);
	}
	
	public boolean removeLot(ParkingLot lot) {
		boolean success = lots.remove(lot);
		for(int i = 0; i < lots.size(); ++i) {
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
	 * Begins a transaction given the card that will be used for payment. This function
	 * should be called whenever a customer enters the lot.
	 * @param card payment card
	 * @return true if the transaction was created and the customer may park; false otherwise
	 */
	public boolean startTransaction(Card card, int lotIndex) {		
		//get open lot, and check if valid
		ParkingLot openLot = lots.get(lotIndex);
		if(openLot == null) return false;
		
		LotSection openSection = openLot.getOpenLotSection();
		
		// create new transaction
		Transaction transaction = new Transaction(completedTransactions.size() + outstandingTransactions.size(), openLot.getHourlyRate(), openLot, openSection);
		
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
	 * @param transactionId id of the transaction to complete
	 * @return true if the transaction was successfully completed; false otherwise
	 */
	public boolean completeTransaction(int transactionId) {
		// Get the transaction based on its ID
		Pair<Transaction, Card> transactionInfo = outstandingTransactions.get(transactionId);
		
		// Unable to find transaction
		if(transactionInfo == null) {
			return false;
		}
		
		// Pull transaction and card info
		Transaction transaction = transactionInfo.a;
		Card card = transactionInfo.b;
		
		// Charge the balance to the card
		//TODO: calculate amount to charge people for time parked.
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
	 * Searches the managed lots for an open lot section in which a new
	 * customer could park.
	 * @return a lot section with empty spots; null if all sections are full
	 */
	public LotSection getOpenLotSection(){
		LotSection section = null;	
		for(ParkingLot lot : lots) {
			if(lot.getOpenLotSection() == null) {
				section = lot.getOpenLotSection();
				break;
			}
		}
		return section;
	}
	
	/**
	 * Searches the managed lots for an open lot that has space for a new customer to
	 * park.
	 * @return a lot with empty spots; null if all lots are full
	 */
	public ParkingLot getOpenLot() {
		ParkingLot open = null;
		for(ParkingLot lot : lots) {
			if(lot.getNumOpenSections() > 0) {
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
	public ArrayList<ParkingLot> getLots () {
		return lots;
	}
	
}
