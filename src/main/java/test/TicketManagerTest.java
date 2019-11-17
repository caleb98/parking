package test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import data.ParkingLot;
import data.TicketManager;

class TicketManagerTest {

	private static TicketManager manager = new TicketManager();
	private static ParkingLot notPresent = new ParkingLot(-1, "Not Present");
	private static ParkingLot present = new ParkingLot(-1, "Present");
	
	private static int numTestLots = 100;
	
	@Test
	void testAddLot() {
		//Get number of lots before adding
		int numLots = manager.getNumLots();
		
		manager.addLot(present);
		
		//Number of lots after add should be one greater
		assertEquals(numLots + 1, manager.getNumLots(), "Number of lots should be " + numLots + " + 1");
	}
	
	@Test 
	void testRemovePresentLot() {
		//Get number of lots before removing
		int numLots = manager.getNumLots();
		
		assertTrue(manager.removeLot(present));
		
		//Number of lots after removing should be one less
		assertEquals(numLots - 1, manager.getNumLots(), "Number of lots should be " + numLots + " - 1");
	}
	
	@Test
	void testRemoveNonPresentLot() {
		//Get number of lots before removing
		int numLots = manager.getNumLots();
		
		assertTrue(!manager.removeLot(notPresent));

		//Number of lots after removing should be same, since the lot wasn't in our manager
		assertEquals(numLots, manager.getNumLots(), "Number of lots should be exactly " + numLots);
	}
	
	@Test
	void testAllMultipleLots() {
		//Get the number of lots before adding
		int numLots = manager.getNumLots();
		
		for(int i = 0; i < numTestLots; ++i) {
			manager.addLot(new ParkingLot(0, "Lot" + i));
			testLotIDs();
		}
		
		//Make sure that all the lots were added
		assertEquals(numLots + numTestLots, manager.getNumLots(), "Number of total lots should be " + numLots + " + " + numTestLots);
	}
	
	@Test
	void testRemoveAllLots() {
		//Remove all lots, checking ids as we go
		while(manager.getNumLots() > 0) {
			assertTrue(manager.removeLot(manager.getLots().get(0)));
			testLotIDs();
		}
	}
	
	@AfterEach
	void testLotIDs() {
		for(int i = 0; i < manager.getNumLots(); ++i) {
			assertEquals(i, manager.getLots().get(i).lotId, "ID of lot " + manager.getLots().get(i).lotName + " should be " + i);
		}
	}

}
