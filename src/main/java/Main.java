import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import data.Card;
import data.LotSection;
import data.ParkingLot;
import data.TicketManager;

public class Main {

    public static void main(String args[]){
        ParkingLot parkingLot = new ParkingLot(1);
        parkingLot.addSection(new LotSection("A", 5));

        TicketManager ticketManager = new TicketManager();
        ticketManager.addLot(parkingLot);

        InputStreamReader isr = new InputStreamReader(System.in);
        while(true){
            System.out.println("1. Entering\n2.Leaving");
            int option = 0;
            try{
                BufferedReader br = new BufferedReader(isr);
                option = Integer.parseInt(br.readLine());
            }catch(IOException e){
                e.printStackTrace();
                break;
            }
            switch(option){
                case 1: 
                    Card card = ticketManager.scanCard();
                    boolean success = ticketManager.startTransaction(card);
                    if(success){
                        ticketManager.setGateOpen();
                        try{
                            Thread.sleep(1000);
                        }catch(InterruptedException e){
                            e.printStackTrace();
                        }
                        ticketManager.setGateClosed();
                    }else{
                        System.out.println("An open spot could not be found!");
                    }
                    break;
                case 2:
                    int ticketId = ticketManager.scanTicket();
                    boolean tranasctionComplete = ticketManager.completeTransaction(ticketId);
                    if(tranasctionComplete)
                        System.out.println("Transaction has been completed");
                    else
                        System.out.println("Transaction failed, check ticked id");
                    break;
                default:
                    System.out.println("Invalid Option");
            }
            if(option==3) break;
        }
        try{
            isr.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

}