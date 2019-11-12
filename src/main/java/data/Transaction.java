package data;

public class Transaction {

	public final int transactionId;
	public final LotSection lotUsed;
	public final long timeCreatedInMS;
	
	public long timeEndedInMS = 0;
	
	public Transaction(int id, LotSection sectionUsed) {
		transactionId = id;
		lotUsed = sectionUsed;
		timeCreatedInMS = System.currentTimeMillis();
	}
	
	// return the time difference of the transaction
	// if timeEnded is not up, get the differnce from now
	/**
	 * @return if the transaction is open, returns the time since the start of the transaction;
	 * if the transaction is closed, returns the time from the start of the transaction to its end
	 */
	public long getTimeDifferenceInMS(){
		return ((this.timeEndedInMS == 0) ? System.currentTimeMillis() : this.timeEndedInMS) - this.timeCreatedInMS;
	}
}
