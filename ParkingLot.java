import java.util.Scanner;

public class ParkingLot {
	
	public LotSection[] lotArray;
	public int lotId;

	public ParkingLot(int lotId){
		this.lotId = lotId;
	}

	public boolean addSection(LotSection lot){
		return false;
	}

	public boolean removeSection(LotSection lot){
		return false;
	}

	public LotSection getOpenLotSection(){
		return null;
	}
}