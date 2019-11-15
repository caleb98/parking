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
	
	public boolean addSection(LotSection section){
		section.setId(this.getSectionSize());
		return sections.add(section);
	}

	public boolean removeSection(LotSection section) {
		boolean success = sections.remove(section);
		for(int i = 0; i < sections.size(); ++i) {
			sections.get(i).setId(i);
		}
		return success;
	}
	
	public int getSectionSize () {
		return sections.size();
	}
	
	public int getOpenSectionSize() {
		int size = 0;
		for(int i = 0; i < sections.size(); i++){
			if(sections.get(i).hasOpenSpots()){
				size++;
			}
		}
		return size;
	}

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
}