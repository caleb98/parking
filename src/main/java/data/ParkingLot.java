package data;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
		
		//UI Display
		JPanel inputPanel = new JPanel(new GridBagLayout());
		GridBagConstraints con = new GridBagConstraints();
		con.insets = new Insets(5, 0, 0, 5);
		con.fill = GridBagConstraints.HORIZONTAL;
		
		con.weightx = 0;
		con.gridx = 0;
		con.gridy = 0;
		inputPanel.add(new JLabel("Name:"), con);
		
		con.weightx = 1;
		con.gridx = 1;
		JTextField nameEntry = new JTextField();
		inputPanel.add(nameEntry, con);
		
		con.weightx = 0;
		con.gridx = 0;
		con.gridy = 1;
		inputPanel.add(new JLabel("Card No.:"), con);
		
		con.weightx = 1;
		con.gridx = 1;
		JTextField cardNumEntry = new JTextField("4242 4242 4242 4242");
		inputPanel.add(cardNumEntry, con);
		
		con.weightx = 0;
		con.gridx = 0;
		con.gridy = 2;
		inputPanel.add(new JLabel("Card CVV:"), con);
		
		con.weightx = 1;
		con.gridx = 1;
		JTextField cardCVVEntry = new JTextField("XXX");
		inputPanel.add(cardCVVEntry, con);
		
		con.weightx = 0;
		con.gridx = 0;
		con.gridy = 3;
		inputPanel.add(new JLabel("Expiry Date:"), con);
		
		con.weightx = 1;
		con.gridx = 1;
		JTextField expEntry = new JTextField("MM/YY");
		inputPanel.add(expEntry, con);
			
		Card scanned = null;
		
		while(scanned == null) {
			int result = JOptionPane.showConfirmDialog(
					null, 
					inputPanel,
					"Please Enter Card Info",
					JOptionPane.DEFAULT_OPTION
			);
			
			if(result == JOptionPane.OK_OPTION) {
				
				//Read string values from the text areas
				String name = nameEntry.getText();
				String number = cardNumEntry.getText().replaceAll("[ -]", ""); //Replace dashes or spaces in between card numbers
				String exp = expEntry.getText(); 
				String cvv = cardCVVEntry.getText();
				
				//Make sure that a name was entered
				if(name.length() == 0) {
					JOptionPane.showMessageDialog(
							null,
							"Please enter the name printed on your card.",
							"Invalid Card Info", 
							JOptionPane.ERROR_MESSAGE
					);
					continue;
				}
				//Make sure card number is of correct length
				else if(number.length() != 16) {
					JOptionPane.showMessageDialog(
							null,
							"Card number not formatted properly.\nPlease check input.",
							"Invalid Card Info", 
							JOptionPane.ERROR_MESSAGE
					);
					continue;
				}
				//Make sure expiration date is properly formatted
				else if(!exp.matches("[0-9]{2}/[0-9]{2}")) {
					JOptionPane.showMessageDialog(
							null,
							"Expiration date must match the format \"MM/YY\"",
							"Invalid Card Info", 
							JOptionPane.ERROR_MESSAGE
					);
					continue;
				}
				//Make sure cvv is appropriate length
				else if(!cvv.matches("[0-9]{3}")) {
					JOptionPane.showMessageDialog(
							null,
							"Invalid CVV format.\nPlease check input.",
							"Invalid Card Info", 
							JOptionPane.ERROR_MESSAGE
					);
					continue;
				}
				
				exp = exp.replace("/", "");
				
				scanned = new Card(name, number, exp, cvv);
			}
			else {
				JOptionPane.showMessageDialog(
						null,
						"Please enter your card info.",
						"Invalid Card Info", 
						JOptionPane.ERROR_MESSAGE
				);
			}
		}
		
		return scanned;
		
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
		
		JPanel inputPanel = new JPanel(new GridBagLayout());
		GridBagConstraints con = new GridBagConstraints();
		con.insets = new Insets(5, 0, 0, 5);
		
		con.fill = GridBagConstraints.HORIZONTAL;
		con.gridx = 0;
		con.gridy = 0;
		inputPanel.add(new JLabel("Ticket ID:"), con);
		
		con.weightx = 1;
		con.gridx = 1;
		JTextField idEntry = new JTextField();
		inputPanel.add(idEntry, con);
		
		int ticketId = -1;
		
		while(ticketId == -1) {
			
			//Show dialog for inputting the ID
			int result = JOptionPane.showConfirmDialog(
					null,
					inputPanel,
					"Please Enter Ticket ID",
					JOptionPane.DEFAULT_OPTION
			);
			
			//User clicked the "Okay" button
			if(result == JOptionPane.OK_OPTION) {
				
				//Get ID string
				String idString = idEntry.getText();
				
				//Try to parse the integer
				try {
					ticketId = Integer.parseInt(idString);
				} catch (NumberFormatException nfe) {
					JOptionPane.showMessageDialog(
							null,
							"Please enter a number for the ticket ID.",
							"Invalid Ticket ID", 
							JOptionPane.ERROR_MESSAGE
					);
					continue;
				}
				
				//Make sure that the value is positive (we should have no negative ID tickets!)
				if(ticketId < 0) {
					JOptionPane.showMessageDialog(
							null,
							"Ticket ID must be a positive number.",
							"Invalid Ticket ID", 
							JOptionPane.ERROR_MESSAGE
					);
					ticketId = -1; //Reset ticket ID value so we continue the while loop
					continue;
				}
				
				
			}
			
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