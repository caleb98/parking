package data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class TicketManager implements Hardware{
	
	private boolean gateOpen = false;

	private HashMap<Transaction, Card> outstandingTransactions = new HashMap<>();
	private ArrayList<ParkingLot> lots = new ArrayList<ParkingLot>();
	private ArrayList<Transaction> completedTransactions = new ArrayList<Transaction>();
	
	public boolean addLot(ParkingLot parkingLot) {
		return lots.add(parkingLot);
	}
	
	public boolean removeLot(ParkingLot parkingLot) {
		return lots.remove(parkingLot);
	}
	
	public boolean startTransaction(Card card) {
		//get open lot, and check if valid
		LotSection lotsection = getOpenLotSection();
		if(lotsection == null) return false;
		// create new transaction
		Transaction transaction = new Transaction(
			completedTransactions.size() + outstandingTransactions.size(), lotsection);
		// fill lot section spot
		transaction.lotUsed.fillSpot();
		// add to outstanding transactions
		outstandingTransactions.put(transaction, card);
		this.printTicket(transaction);
		return true;
	}
	
	public boolean completeTransaction(int transactionId) {
		// find transaction in outstanding transactions
		Transaction transaction = null;
		for(Map.Entry outTrans : outstandingTransactions.entrySet()){
			Transaction key = (Transaction) outTrans.getKey();
			if(key.transactionId == transactionId){
				transaction = key;
				break;
			}
		}
		//could not find ticket
		if(transaction == null) return false;
		// close transaction and charge card
		PaymentManager.subtractBalance(0, outstandingTransactions.get(transaction));
		outstandingTransactions.remove(transaction);
		transaction.timeEndedInMS = System.currentTimeMillis();
		transaction.lotUsed.setOpen();
		completedTransactions.add(transaction);
		// print receipt
		printReceipt(transaction);
		return true;
	}

	public LotSection getOpenLotSection(){
		LotSection section = null;
		//find open section from parking lots
		for(int i = 0; i < lots.size(); i++){
			//get open section
			section = lots.get(i).getOpenLotSection();
			//make sure it exists/isn't full
			if(section != null){
				break;
			}
		}
		return section;
	}

	public Card scanCard(){
		String name = "";
		long cardNum = 0;
		int ssn = 0;
		String expirationDate = "";
		try{
			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(isr);
			System.out.print("Enter your name: ");
			name = br.readLine();
			System.out.print("Enter Card Number: ");
			String cardNumber = br.readLine();
			cardNum = Long.parseLong(cardNumber);
			System.out.print("Enter SSN of Card: ");
			ssn = Integer.parseInt(br.readLine());
			System.out.print("Enter expiration date: ");
			expirationDate = br.readLine();
		}catch(IOException e){
			e.printStackTrace();
		}
		Card card = new Card(name, cardNum, expirationDate, ssn);
		//TODO Validate Card
		return card;
	}

    public void setGateOpen(){
		this.gateOpen = true;
		System.out.println("Gate is open");
	}

    public void setGateClosed(){
		this.gateOpen = false;
		System.out.println("Gate is closed");
	}

    /**
     * Scans a ticket and returns the id listed on ticket. If
     * an error occurs will return -1.
     * @return ticket id; -1 if error
     */
    public int scanTicket(){
		int ticketId = -1;
		try{
			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(isr);
			System.out.print("Enter Ticket ID: ");
			String card = br.readLine();
			ticketId = Integer.parseInt(card);
		}catch(IOException e){
			e.printStackTrace();
		}
		return ticketId;
	}

	@Override
	public void printTicket(Transaction transaction) {
		System.out.println("----------------------------------------");
		System.out.println("Transaction ID: " + transaction.transactionId);
		System.out.println("Lot Assigned: " + transaction.lotUsed.getName());
		System.out.println("Time Assigned: " + transaction.timeCreatedInMS);
		System.out.println("----------------------------------------");
	}

	@Override
	public void printReceipt(Transaction completed) {
		System.out.println("----------------------------------------");
		System.out.println("Transaction ID: " + completed.transactionId);
		System.out.println("Lot Used: " + completed.lotUsed.getName());
		System.out.println("Time Used: " + completed.getTimeDifferenceInMS());
		System.out.println("----------------------------------------");
	}
	
}
