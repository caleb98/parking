package data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class ParkingLot implements Hardware {
	
	/**
	 * The default hourly rate for a parking lot.
	 */
	private static final float DEFAULT_LOT_RATE = 100.00f;
	
	private ArrayList<LotSection> sections = new ArrayList<>();
	private float hourlyRate;
	private int lotId;
	private String lotName;
	
	private boolean gateOpen = false;
	
	public ParkingLot(int lotId, String lotName) {
		this(lotId, lotName, DEFAULT_LOT_RATE);
	}
	
	public ParkingLot(int lotId, String lotName, float rate) {
		this.lotId = lotId;
		this.lotName = lotName;
		hourlyRate = rate;
	}
	
	/**
	 * @return the dollar-amount charger per hour of parking
	 */
	public float getHourlyRate() {
		return hourlyRate;
	}
	
	/**
	 * Sets the dollar-amount charged per hour of parking
	 * @param rate rate per hour
	 */
	public void setHourlyRate(float rate) {
		hourlyRate = rate;
	}
	
	public int getLotId() {
		return lotId;
	}
	
	public void setId(int id) {
		lotId = id;
	}
	
	public String getLotName() {
		return lotName;
	}
	
	public void setLotName(String name) {
		lotName = name;
	}
	
	/**
	 * Adds a section to this lot and sets the section's ID
	 * appropriately.
	 * @param section the section to add
	 * @return 
	 */
	public boolean addSection(LotSection section){
		section.setId(this.getNumSections());
		return sections.add(section);
	}

	/**
	 * Removes the given section from this lot and updates the IDs of 
	 * the remaining sections.
	 * @param section the section to remove
	 * @return true if the lot contained the given section; false otherwise
	 */
	public boolean removeSection(LotSection section) {
		boolean success = sections.remove(section);
		for(int i = 0; i < sections.size(); ++i) {
			sections.get(i).setId(i);
		}
		return success;
	}
	
	/**
	 * @return the total number of sections in this lot
	 */
	public int getNumSections() {
		return sections.size();
	}
	
	/**
	 * @return the number of sections with open spaces
	 */
	public int getNumOpenSections() {
		int size = 0;
		for(int i = 0; i < sections.size(); i++){
			if(sections.get(i).hasOpenSpots()){
				size++;
			}
		}
		return size;
	}

	/**
	 * @return the first open lot section available
	 */
	public LotSection getOpenLotSection(){
		LotSection section = null;
		for(int i = 0; i < sections.size(); i++){
			if(sections.get(i).hasOpenSpots()){
				section = sections.get(i);
				break;
			}
		}
		return section;
	}
	
	/**
	 * @return a list of all sections in this lot
	 */
	public ArrayList<LotSection> getAllSections() {
		return sections;
	}
	
	@Override
	public Card scanCard(){
		String name = "";
		String cardNum = "";
		String cvv = "";
		String expirationDate = "";
		
		try{
			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(isr);
			
			System.out.print("Enter your name: ");
			name = br.readLine();
			
			System.out.print("Enter Card Number: ");
			cardNum = br.readLine();
			
			System.out.print("Enter CVV of Card: ");
			cvv= br.readLine();
			
			System.out.print("Enter expiration date: ");
			expirationDate = br.readLine();
			
		}catch(IOException e){
			e.printStackTrace();
		}
		//TODO FIX THIS
		Card card = new Card(name, String.valueOf(cardNum), expirationDate, cvv);
		//TODO Validate Card
		return card;
	}

	@Override
    public void setGateOpen(){
		gateOpen = true;
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
		System.out.println("Lot Assigned: " + transaction.lotUsed.lotName);
		System.out.println("Section: " + transaction.sectionUsed.getName());
		System.out.println("Arrival Time: " + transaction.timeEnteredDate);
		System.out.println("----------------------------------------");
	}

	@Override
	public void printReceipt(Transaction completed) {
		System.out.println("----------------------------------------");
		System.out.println("Ticket ID: " + completed.transactionId);
		System.out.println("Lot Used: " + completed.lotUsed.lotName);
		System.out.println("Section: " + completed.sectionUsed.getName());
		System.out.println("Departure Time: " + completed.getTimeExitedDate());
		System.out.println("----------------------------------------");
	}
	
}