package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import data.LotSection;
import data.ParkingLot;
import data.TicketManager;

@TestMethodOrder(OrderAnnotation.class)
class EnterExitTest {

	static ParkingLot southLot = new ParkingLot(0, "South Lot");
    static TicketManager ticketManager = new TicketManager();
    static int i;
    
    
	@Test
	@Order(1)
	@BeforeAll
	static void allSpotsUsedTest() {
		
		southLot.addSection(new LotSection("A", 200));
	    southLot.addSection(new LotSection("B", 300));
	    
	    ticketManager.addLot(southLot);
		
	    i = 0;
	    while (ticketManager.getLots().get(0).getOpenSectionSize() > 0) {
	    	ticketManager.getLots().get(0).getOpenLotSection().fillSpot();
	    	i++;
	    }
	    
	    // Assert that there are no open sections
	    assertEquals(ticketManager.getLots().get(0).getOpenLotSection(), null);
	    
	    // Assert that all 500 spots were taken up
	    assertEquals(i, 500);
	}
	
	@Test
	@Order(2)
	void setOpenTest() {
	    for (i = 0; i < 50; i++)
	    	ticketManager.getLots().get(0).sections.get(0).setOpen();
	    
	    // Assert that there are 50 open spots
	    assertEquals(ticketManager.getLots().get(0).sections.get(0).getOpenSpots(), 50);
	    
	}
	
	@Test
	@Order(3)
	void setAllOpenTest() {
	    i = 0;
	    while (ticketManager.getLots().get(0).sections.get(0).getOpenSpots() != ticketManager.getLots().get(0).sections.get(0).getTotalSpots()) {
	    	ticketManager.getLots().get(0).sections.get(0).setOpen();
	    	i++;
	    }
	    
	    // Assert that all 200 spots are open
	    assertEquals(ticketManager.getLots().get(0).sections.get(0).getOpenSpots(), 200);
	}
	
	@Test
	@Order(4)
	void fullLotExceptionTest() {
	    while (ticketManager.getLots().get(0).getOpenSectionSize() > 0) {
	    	ticketManager.getLots().get(0).getOpenLotSection().fillSpot();
	    }
	    
	    assertThrows(IndexOutOfBoundsException.class, () -> {
	    	ticketManager.getLots().get(0).sections.get(0).fillSpot();
	    });
	}
	
	@Test
	@Order(5)
	void emptyLotExceptionTest() {
	    i = 0;
		while (ticketManager.getLots().get(0).sections.get(0).getOpenSpots() != ticketManager.getLots().get(0).sections.get(0).getTotalSpots()) {
			ticketManager.getLots().get(0).sections.get(0).setOpen();
		    i++;
		}
	    
		assertThrows(IndexOutOfBoundsException.class, () -> {
	    	ticketManager.getLots().get(0).sections.get(0).setOpen();
		});
	}

}
