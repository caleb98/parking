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
	void cardLongNumberTest(){
		Card longNumber = new Card(name, longCardNumber, goodExpDate, goodSecCode);
		assertTrue(longNumber.reject);
	}//end cardLongNumberTest
	
	@Test
	void cardShortNumberTest() {
		Card shortNumber = new Card(name, shortCardNumber, goodExpDate, goodSecCode);
		assertTrue(shortNumber.reject);
	}//end cardShortNumberTest
	
	@Test
	void badExpOneTest() {
		Card badExpOne = new Card(name, goodCardNumber, expDateOne, goodSecCode);
		assertTrue(badExpOne.reject);
	}//end badExpOneTest		
		
	@Test
	void badExpTwoTest() {
		Card badExpTwo = new Card(name, goodCardNumber, expDateTwo, goodSecCode);
		assertTrue(badExpTwo.reject);
	}//end badExpTwoTest
	
	@Test
	void badSecCodeOneTest() {
		Card badSecOne = new Card(name, goodCardNumber, goodExpDate, secCodeOne);
		assertTrue(badSecOne.reject); 
	}//end badSecCodeOneTest
	
	@Test
	void badSecCodeTwoTest() {
		Card badSecTwo = new Card(name, goodCardNumber, goodExpDate, secCodeTwo);
		assertTrue(badSecTwo.reject);
	}//end badSecCodeTwoTest
	
	@Test
	void alphaSecCodeTest() {
		Card badSecTwo = new Card(name, goodCardNumber, goodExpDate, alphaSecCode);
		assertTrue(badSecTwo.reject);
	}//end alphaSecCodeTest
	
	@Test
	void goodCardTest() {
		Card lastOne = new Card(name, goodCardNumber, goodExpDateTwo, goodSecCode);
		assertTrue(!lastOne.reject); 
	}//end goodCardTest
}//end test class
