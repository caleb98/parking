package data;

import java.util.Date;

public class Transaction {

	public final int transactionId;
	public final LotSection lotUsed;
	public final float hourlyRate;
	public final long timeEnteredInMS;
	public final Date timeEnteredDate;
	
	public long timeExitedInMS = 0;
	public Date timeExitedDate = null;
	public float totalCost;
	
	public Transaction(int id, float rate, LotSection sectionUsed) {
		transactionId = id;
		lotUsed = sectionUsed;
		hourlyRate = rate;
		timeEnteredInMS = System.currentTimeMillis();
		timeEnteredDate = new Date(timeEnteredInMS);
	}
	
	public void closeTransaction() {
		timeExitedInMS = System.currentTimeMillis();
		timeExitedDate = new Date(timeExitedInMS);
		
		totalCost = (float) Math.ceil(((timeExitedInMS - timeEnteredInMS) / 1000f) / 60f) / 60f * hourlyRate;
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
