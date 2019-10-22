
public class Transaction {

	public final int transactionId;
	public final LotSection lotUsed;
	public final long timeCreatedInMS;
	
	public Transaction(int id, LotSection sectionUsed) {
		transactionId = id;
		lotUsed = sectionUsed;
		timeCreatedInMS = System.currentTimeMillis();
	}
	
}
