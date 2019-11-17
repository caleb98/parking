package data;

import java.util.ArrayList;

public class ParkingLot {
	
	/**
	 * The default hourly rate for a parking lot.
	 */
	private static final float DEFAULT_LOT_RATE = 100.00f;
	
	public ArrayList<LotSection> sections = new ArrayList<>();
	public float hourlyRate;
	public int lotId;
	public String lotName;
	
	public ParkingLot(int lotId, String lotName) {
		this(lotId, lotName, DEFAULT_LOT_RATE);
	}
	
	public ParkingLot(int lotId, String lotName, float rate) {
		this.lotId = lotId;
		this.lotName = lotName;
		hourlyRate = rate;
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
}