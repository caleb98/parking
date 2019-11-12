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
	
	/**
	 * @return the number of open spots in this lot
	 */
	public int getOpenSpots() {
		return totalSpots - occupiedSpots;
	}
	
	/**
	 * @return whether or not this lot has at least 1 open spot
	 */
	public boolean hasOpenSpots() {
		return getOpenSpots() != 0;
	}
	
	/**
	 * Opens a spot in this lot.
	 */
	public void setOpen() {
		occupiedSpots--;
	}
	
	/**
	 * Fills a spot in this lot.
	 */
	public void fillSpot() {
		occupiedSpots++;
	}

	public String getName(){
		return this.name;
	}

}
