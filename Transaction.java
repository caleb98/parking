
public class Transaction {

	public final int transactionId;
	public final LotSection lotUsed;
	public final int timeCreated;
	
	public Transaction(int id, LotSection sectionUsed, int transactionTime) {
		transactionId = id;
		lotUsed = sectionUsed;
		timeCreated = transactionTime;
	}
	
}
