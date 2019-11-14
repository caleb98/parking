package src.main.java.data;

import java.util.Date;

public class Transaction {

	public final int transactionId;
	public final LotSection lotUsed;
	public final long timeEnteredInMS;
	public final Date timeEnteredDate;
	
	public long timeExitedInMS = 0;
	public Date timeExitedDate = null;
	
	public Transaction(int id, LotSection sectionUsed) {
		transactionId = id;
		lotUsed = sectionUsed;
		timeEnteredInMS = System.currentTimeMillis();
		timeEnteredDate = new Date(timeEnteredInMS);
	}
	
	public void closeTransaction() {
		timeExitedInMS = System.currentTimeMillis();
		timeExitedDate = new Date(timeExitedInMS);
	}
	
	// return the time difference of the transaction
	// if timeEnded is not up, get the differnce from now
	/**
	 * @return if the transaction is open, returns the time since the start of the transaction;
	 * if the transaction is closed, returns the time from the start of the transaction to its end
	 */
	public long getTimeDifferenceInMS(){
		return ((this.timeExitedInMS == 0) ? System.currentTimeMillis() : this.timeExitedInMS) - this.timeEnteredInMS;
	}
}
