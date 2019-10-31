package data;

public class LotSection {
	
	private int totalSpots;
	private int occupiedSpots;
	private String name;
	
	public LotSection(String sectionName, int maxSpots) {
		totalSpots = maxSpots;
		occupiedSpots = 0;
		name = sectionName;
	}
	
	public int getOpenSpots() {
		return totalSpots - occupiedSpots;
	}
	
	public boolean hasOpenSpots() {
		return getOpenSpots() != 0;
	}
	
	public void setOpen() {
		occupiedSpots--;
	}
	
	public void fillSpot() {
		occupiedSpots++;
	}

	public String getName(){
		return this.name;
	}

}
