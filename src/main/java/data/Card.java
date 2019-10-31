package data;

public class Card {
	
	public final String name;
	public final long cardNumber;
	public final String expirationDate;
	public final int securityCode;
	
	public Card(String name, long cardNumber, String expirationDate, int securityCode){
		this.name = name;
		this.cardNumber = cardNumber;
		this.expirationDate = expirationDate;
		this.securityCode = securityCode;
	}
	
}
