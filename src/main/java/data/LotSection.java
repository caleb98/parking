package data;

public class LotSection {
	
	private int totalSpots;
	private int occupiedSpots;
	
	private int sectionId;
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
	
	public int getTotalSpots() {
		return totalSpots;
	}
	
	
	/**
	 * Opens a spot in this lot.
	 */
	public void setOpen() {
		if (occupiedSpots > 0)
			occupiedSpots--;
		else {
			System.out.println("You shouldn't be in here! Something went wrong.");
			throw new IndexOutOfBoundsException("Car is trying to leave empty lot section " + name);
		}
	}
	
	public void setId(int id) {
		this.sectionId = id;
	}
	
	/**
	 * Fills a spot in this lot.
	 */
	public void fillSpot() {
		if (occupiedSpots < totalSpots)
			occupiedSpots++;
		else {
			System.out.println("Sorry, there are no spots open.");
			throw new IndexOutOfBoundsException("No spots open in lot section " + name);
		}
			
	}

	public String getName(){
		return this.name;
	}
	
	public int getId() {
		return sectionId;
	}

}
