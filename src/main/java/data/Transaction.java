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
	public long getTimeDifferenceInMS(){
		return ((this.timeEndedInMS == 0) ? System.currentTimeMillis() : this.timeEndedInMS) - this.timeCreatedInMS;
	}
}
