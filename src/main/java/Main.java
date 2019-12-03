import data.LotSection;
import data.ParkingLot;
import data.TicketManager;
import ui.ProgramWindow;

public class Main {

    public static void main(String args[]){
        //southLot.addSection(new LotSection("A", 200));
        //southLot.addSection(new LotSection("B", 300));
        
        //northLot.addSection(new LotSection("Level 1", 200));
        //northLot.addSection(new LotSection("Level 2", 200));
        //northLot.addSection(new LotSection("Level 3", 200));

        TicketManager ticketManager = new TicketManager();
        
        new ProgramWindow(ticketManager);

    }

}