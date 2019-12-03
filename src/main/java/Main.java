import data.TicketManager;
import ui.ProgramWindow;

public class Main {

    public static void main(String args[]){
        TicketManager ticketManager = new TicketManager();
        
        new ProgramWindow(ticketManager);

    }

}