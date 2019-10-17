public class Card {
	
	public final String name;
	public final int cardNumber;
	public final String expirationDate;
	public final int securityCode;
	
	public Card(String name, int cardNumber, String expirationDate, int securityCode){
		this.name = name;
		this.cardNumber = cardNumber;
		this.expirationDate = expirationDate;
		this.securityCode = securityCode;
	}
	
}