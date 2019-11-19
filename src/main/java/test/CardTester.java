package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import data.Card;

class CardTester {

	public static final String name = "Joe Smith";
	public static final String longCardNumber = "12345678901234567";
	public static final String shortCardNumber = "123456789012345";
	public static final String goodCardNumber = "1234567890123437";
	public static final String expDateOne = "1010";
	public static final String expDateTwo = "1119";
	public static final String goodExpDate = "1120";
	public static final String goodExpDateTwo = "1219";
	public static final String secCodeOne = "23";
	public static final String secCodeTwo = "5342";
	public static final String goodSecCode = "345";
	public static final String alphaSecCode = "abc";
	
	
	@Test
	void cardLongNumberTest(Card card){
		@SuppressWarnings("unused")
		Card longNumber = new Card(name, longCardNumber, goodExpDate, goodSecCode);
		assertTrue(Card.reject);
	}//end cardLongNumberTest
	
	@Test
	void cardShortNumberTest(Card card) {
		@SuppressWarnings("unused")
		Card shortNumber = new Card(name, shortCardNumber, goodExpDate, goodSecCode);
		assertTrue(Card.reject);
	}//end cardShortNumberTest
	
	@Test
	void badExpOneTest(Card card) {
		@SuppressWarnings("unused")
		Card badExpOne = new Card(name, goodCardNumber, expDateOne, goodSecCode);
		assertTrue(Card.reject);
	}//end badExpOneTest		
		
	@Test
	void badExpTwoTest(Card card) {
		@SuppressWarnings("unused")
		Card badExpTwo = new Card(name, goodCardNumber, expDateTwo, goodSecCode);
		assertTrue(Card.reject);
	}//end badExpTwoTest
	
	@Test
	void badSecCodeOneTest(Card card) {
		@SuppressWarnings("unused")
		Card badSecOne = new Card(name, goodCardNumber, goodExpDate, secCodeOne);
		assertTrue(Card.reject); 
	}//end badSecCodeOneTest
	
	@Test
	void badSecCodeTwoTest(Card card) { 
		@SuppressWarnings("unused")
		Card badSecTwo = new Card(name, goodCardNumber, goodExpDate, secCodeTwo);
		assertTrue(Card.reject);
	}//end badSecCodeTwoTest
	
	@Test
	void alphaSecCodeTest(Card card) { 
		@SuppressWarnings("unused")
		Card badSecTwo = new Card(name, goodCardNumber, goodExpDate, alphaSecCode);
		assertTrue(Card.reject);
	}//end alphaSecCodeTest
	
	@Test
	void goodCardTest(Card card) {
		@SuppressWarnings("unused")
		Card lastOne = new Card(name, goodCardNumber, goodExpDateTwo, goodSecCode);
		assertFalse(Card.reject); 
	}//end goodCardTest
}//end test class
