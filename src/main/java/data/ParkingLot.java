package src.main.java.data;

import java.util.ArrayList;
import java.util.Scanner;

public class ParkingLot {
	
	public ArrayList<LotSection> sections = new ArrayList<>();
	public int hourlyRate;
	public int lotId;
	public String lotName;

	public ParkingLot(int lotId, int hourlyRate){
		this.lotId = lotId;
		this.lotName = "";
	}
	
	public ParkingLot(int lotId, String lotName){
		this.lotId = lotId;
		this.lotName = lotName;
		this.hourlyRate = hourlyRate;
	}

	public boolean addSection(LotSection section){
		return sections.add(section);
	}

	public boolean removeSection(LotSection section){
		return sections.remove(section);
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