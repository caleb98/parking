import data.LotSection;
import data.ParkingLot;
import data.TicketManager;
import ui.ProgramWindow;

public class Main {

    public static void main(String args[]){
    	//TEST
    	
    	
        ParkingLot southLot = new ParkingLot(0, "South Lot");
        southLot.addSection(new LotSection("A", 200));
        southLot.addSection(new LotSection("B", 300));
        
        ParkingLot northLot = new ParkingLot(1, "North Parking Deck");
        northLot.addSection(new LotSection("Level 1", 200));
        northLot.addSection(new LotSection("Level 2", 200));
        northLot.addSection(new LotSection("Level 3", 200));

        TicketManager ticketManager = new TicketManager();
        ticketManager.addLot(southLot);
        ticketManager.addLot(northLot);
        
        ProgramWindow win = new ProgramWindow(ticketManager);

    }

}