package test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import data.LotSection;
import data.ParkingLot;
import data.TicketManager;
import data.Transaction;

@TestMethodOrder(OrderAnnotation.class)
class TransactionTest {

	private TicketManager manager;
	private ParkingLot lot;
	private Transaction transcaction;

	@Test
	@Order(1)
	void testLotCreation(){
        lot = new ParkingLot(1, "North", 3.0f);
        lot.addSection(new LotSection("A", 50));
        lot.addSection(new LotSection("B", 25));
        
        assertTrue(lot != null);
        assertTrue(lot.getAllSections().size() > 0);
	}

	@Test
	@Order(2)
	void tranasctionCreation(){
		lot = new ParkingLot(1, "North", 3.0f);
        lot.addSection(new LotSection("A", 50));
        lot.addSection(new LotSection("B", 25));
		LotSection lotSection = lot.getOpenLotSection();
        transcaction = new Transaction(0, lot.hourlyRate, lot, lotSection);
        assertTrue(transcaction != null);
    }

	@Test
	@Order(3)
	void rateInitialization(){
		lot = new ParkingLot(1, "North", 3.0f);
        lot.addSection(new LotSection("A", 50));
        lot.addSection(new LotSection("B", 25));
		LotSection lotSection = lot.getOpenLotSection();
        transcaction = new Transaction(0, lot.hourlyRate, lot, lotSection);
        assertEquals(transcaction.getHourlyRate(), lot.hourlyRate);
	}

	@Test
	@Order(4)
	void rateConsistency(){
		lot = new ParkingLot(1, "North", 3.0f);
        lot.addSection(new LotSection("A", 50));
        lot.addSection(new LotSection("B", 25));
		LotSection lotSection = lot.getOpenLotSection();
        transcaction = new Transaction(0, lot.hourlyRate, lot, lotSection);
        double savedRate = transcaction.getHourlyRate();
        lot.hourlyRate = 3.5f;
        assertEquals(savedRate, transcaction.getHourlyRate());
        assertTrue(transcaction.getHourlyRate() != lot.hourlyRate);
    }

	@Test
	@Order(5)
	void totalCalculated(){
		lot = new ParkingLot(1, "North", 3.0f);
        lot.addSection(new LotSection("A", 50));
        lot.addSection(new LotSection("B", 25));
		LotSection lotSection = lot.getOpenLotSection();
        transcaction = new Transaction(0, lot.hourlyRate, lot, lotSection);
        transcaction.timeEnteredInMS = transcaction.timeEnteredInMS - (1000 * 60 * 60 * 3);
        transcaction.closeTransaction();
        assertTrue(transcaction.getTotalCost() > 0);
    }

}