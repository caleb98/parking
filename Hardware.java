
public interface Hardware {

	//Scanning functions
	/**
	 * Returns a card that was scanned from the card reader. 
	 * Note that a scanned card may be invalid for transactions and 
	 * must be checked with the PaymentManager class before any
	 * charges to the card are made.
	 * @return A card representing the card that was scanned; null if error.
	 */
	public Card scanCard();
	/**
	 * Scans a customer's parking ticket and get's the ID for
	 * the corresponding transaction.
	 * @return id of transaction represented by that ticket
	 */
	public int scanTicket();
	
	//Printing functions
	/**
	 * Prints a parking ticket for a customer to keep until 
	 * their departure.
	 */
	public void printTicket();
	/**
	 * Prints a receipt for a completed transaction.
	 */
	public void printReceipt(Transaction completed);
	
	//Gate management
	/**
	 * Set's the exit gate to an open state.
	 */
	public void setGateOpen();
	/**
	 * Sets the exit gate to a closed state.
	 */
	public void setGateClosed();
	
}
