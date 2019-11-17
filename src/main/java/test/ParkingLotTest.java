package test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import data.LotSection;
import data.ParkingLot;

class ParkingLotTest {

	private static ParkingLot testLot = new ParkingLot(0, "Test Lot");       
	private static LotSection notPresent = new LotSection("NotPresent", 100);
	private static LotSection present = new LotSection("Present", 100);    
	
	private static int sectionsTestSize = 100;
	
	@Test
	void testAddSection() {
		//Get the number of sections before adding
		int numSections = testLot.getNumSections();
		
		testLot.addSection(present);
		
		//Test number of sections
		assertEquals(numSections + 1, testLot.getNumSections(), "Number of sections in lot equals " + numSections + " + 1");
	}
	
	@Test
	void testRemovePresentSection() {
		//Get the number of sections before removing
		int numSections = testLot.getNumSections();
		
		assertTrue(testLot.removeSection(present));
		
		//Test number of sections
		assertEquals(numSections - 1, testLot.getNumSections(), "Number of sections in lot equals " + numSections + " - 1");		
	}
	
	@Test
	void testRemoveNonPresentSection() {
		//Get the number of sections before removing
		int numSections = testLot.getNumSections();
		
		assertTrue(!testLot.removeSection(notPresent));
		
		//Test number of sections
		assertEquals(numSections, testLot.getNumSections(), "Number of sections in lot equals " + numSections);
	}
	
	@Test
	void testAddMultipleSections() {
		//Get the number of sections before adding
		int numSections = testLot.getNumSections();
		
		for(int i = 0; i < sectionsTestSize; ++i) {
			testLot.addSection(new LotSection(String.format("Section %s", i), 50));
			testSectionIDs();
		}
		
		//Make sure that all sections were added
		assertEquals(numSections + sectionsTestSize, testLot.getNumSections(), "Number of sections in lot equals " + numSections + " + " + sectionsTestSize);
	}
	
	@Test
	void testRemoveAllSections() {
		//Loop through and remove all sections, testing ids as we go
		while(testLot.getNumSections() > 0) {
			assertTrue(testLot.removeSection(testLot.getAllSections().get(0)));
			testSectionIDs();
		}
	}
	
	@AfterEach
	void testSectionIDs() {
		//Check that the ids of all sections in the lot are correct and none are missing
		for(int i = 0; i < testLot.getNumSections(); ++i) {
			assertEquals(i, testLot.getAllSections().get(i).getId(), "Lot section has correct id " + String.valueOf(i));
		}
	}

}
