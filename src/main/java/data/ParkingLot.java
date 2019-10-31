package data;

import java.util.ArrayList;
import java.util.Scanner;

public class ParkingLot {
	
	public ArrayList<LotSection> sections = new ArrayList<>();
	public int lotId;

	public ParkingLot(int lotId){
		this.lotId = lotId;
	}

	public boolean addSection(LotSection section){
		return sections.add(section);
	}

	public boolean removeSection(LotSection section){
		return sections.remove(section);
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