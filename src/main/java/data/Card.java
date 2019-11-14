package src.main.java.data;

/**
 * Represents a credit/debit card used for payment.
 */
public class Card {
	
	/**
	 * The card holder's name as printed on the card.
	 */
	public final String name;
	/**
	 * The card number.
	 */
	public final long cardNumber;
	/**
	 * The date that the card expires.
	 */
	public final String expirationDate;
	/**
	 * The three digit security code printed on the back of the card.
	 */
	public final int securityCode;
	
	public Card(String name, long cardNumber, String expirationDate, int securityCode){
		this.name = name;
		this.cardNumber = cardNumber;
		this.expirationDate = expirationDate;
		this.securityCode = securityCode;
	}
	
}
