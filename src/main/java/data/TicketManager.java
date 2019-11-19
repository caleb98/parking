package data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class TicketManager implements Hardware {
	
	private boolean gateOpen = false;

	private HashMap<Integer, Pair<Transaction, Card>> outstandingTransactions = new HashMap<>();
	private ParkingLot lot;
	private ArrayList<Transaction> completedTransactions = new ArrayList<Transaction>();
	
	public TicketManager(ParkingLot lot){
		this.lot = lot;
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
		LotSection lotsection = lot.getOpenLotSection();
		if(lotsection == null) return false;
		
		// create new transaction
		Transaction transaction = new Transaction(completedTransactions.size() + outstandingTransactions.size(), lot.hourlyRate, lotsection);
		
		// fill lot section spot
		transaction.lotUsed.fillSpot();
		
		// add to outstanding transactions
		outstandingTransactions.put(transaction.transactionId, new Pair<>(transaction, card));
		this.printTicket(transaction);
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
		PaymentManager.subtractBalance(0, card);
		transaction.timeExitedInMS = System.currentTimeMillis();
		
		// Remove the transaction from outstanding and insert it in the 
		// completed transactions list, dumping card info.
		outstandingTransactions.remove(transactionId);
		completedTransactions.add(transaction);
		transaction.closeTransaction();
		
		// Set an open spot in the lot where this customer was parked.
		transaction.lotUsed.setOpen();
		
		// Print receipt
		printReceipt(transaction);
		return true;
	}

	/**
	 * Searches the managed lots for an open lot section in which a new
	 * customer could park.
	 * @return a lot section with empty spots; null if all sections are full
	 */
	public LotSection getOpenLotSection(int lotIndex){
		LotSection section = lot.getOpenLotSection();		
		return section;
	}
	
	//IDK even know - Alec
	/*public int getNumLots() {
		return lots.size();
	}*/

	// public ArrayList<ParkingLot> getLots () {
	// 	return lots;
	// }
	
	
	
	@Override
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
		//TODO FIX THIS
		Card card = new Card(name, String.valueOf(cardNum), expirationDate, ssn);
		//TODO Validate Card
		return card;
	}

	@Override
    public void setGateOpen(){
		this.gateOpen = true;
		System.out.println("Gate is open");
	}

	@Override
    public void setGateClosed(){
		this.gateOpen = false;
		System.out.println("Gate is closed");
	}

    /**
     * Scans a ticket and returns the id listed on ticket. If
     * an error occurs will return -1.
     * @return ticket id; -1 if error
     */
	@Override
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
		System.out.println("Ticket ID: " + transaction.transactionId);
		System.out.println("Lot Assigned: " + transaction.lotUsed.getName());
		System.out.println("Time Assigned: " + transaction.timeEnteredInMS);
		System.out.println("----------------------------------------");
	}

	@Override
	public void printReceipt(Transaction completed) {
		System.out.println("----------------------------------------");
		System.out.println("Ticket ID: " + completed.transactionId);
		System.out.println("Lot Used: " + completed.lotUsed.getName());
		System.out.println("Time Used: " + completed.getTimeDifferenceInMS());
		System.out.println("----------------------------------------");
	}
	
}
