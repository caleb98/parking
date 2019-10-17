import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String args[]){
        TicketManager ticketManager = new TicketManager();

        while(true){
            System.out.println("1. Entering\n2.Leaving");
            int option = 0;
            try{
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);
            option = Integer.parseInt(br.readLine());
            isr.close();
            br.close();
            }catch(IOException e){
                e.printStackTrace();
            }
            switch(option){
                case 1: 
                    ticketManager.scanCard();
                    break;
                case 2:
                    ticketManager.scanTicket();
                    break;
                default:
                    System.out.println("Invalid Option");
            }

        }
    }

}