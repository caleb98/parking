package data;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;

public class Transaction {

	public final int transactionId;
	public final ParkingLot lotUsed;
	public final LotSection sectionUsed;
	public final long timeEnteredInMS;
	public final Date timeEnteredDate;
	public final float hourlyRate;

	private long timeExitedInMS = 0;
	private Date timeExitedDate = null;
	private float totalCost;

	public Transaction(int id, float hourlyRate, ParkingLot lotUsed, LotSection sectionUsed) {
		transactionId = id;
		this.lotUsed = lotUsed;
		this.sectionUsed = sectionUsed;
		timeEnteredInMS = System.currentTimeMillis();
		timeEnteredDate = new Date(timeEnteredInMS);
		this.hourlyRate = hourlyRate;
	}

	public void closeTransaction() {
		timeExitedInMS = System.currentTimeMillis();
		timeExitedDate = new Date(timeExitedInMS);

		totalCost = hourlyRate * (timeExitedInMS - timeEnteredInMS) / 1000 / 60 / 60;

		try {
			FileWriter fWriter = new FileWriter("TransactionLog.txt", true);
			PrintWriter writer = new PrintWriter(fWriter);
			writer.println("[transaction]\n" + "transaction_id: " + transactionId + "\n" + "lot_used: " + lotUsed.getLotName()
					+ "\n" + "section_used: " + sectionUsed.getName() + "\n" + "check_in: " + timeEnteredDate.toString() + "\n"
					+ "check_out: " + timeExitedDate.toString() + "\n" + "payment: $" + totalCost);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public long getTimeExitedMS() {
		return timeExitedInMS;
	}
	
	public Date getTimeExitedDate() {
		return timeExitedDate;
	}
	
	public float getTotalCost(){
		return totalCost;
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
