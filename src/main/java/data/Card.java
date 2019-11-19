package data;

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
	public final String cardNumber;
	/**
	 * The date that the card expires.
	 */
	public final String expirationDate;
	/**
	 * The three digit security code printed on the back of the card.
	 */
	public final int securityCode;
	
	public static boolean reject = false;
	
	public Card(String name, String cardNumber, String expirationDate, int securityCode){
		this.name = name;
		this.cardNumber = cardNumber;
			if(cardNumber.length() < 16) {
				System.out.println("Error: Card number is too short.");
				rejectCard(101);
			}//end if
			else if(cardNumber.length() > 16) {
				System.out.println("Error: Card number is too long.");
				rejectCard(102);
			}//end else if
			/* Trick to tell if card number is genuine:
		    A. Double every other number (starting with the first)
		    B. Separate any double-digit numbers that result into the sum of their parts (e.g. 14 becomes 1 and 4)
		    C. Calculate the sum of the resulting numbers
		    D. Calculate the sum of the numbers that were not doubled (i.e. the odd digits in the card number)
		    E. Add the result of step c to the result of step d
		    F. Divide by 10
			*/
			int sum = 0;
			int number = 0;
			for(int i = 0; i < cardNumber.length(); i++) {
				if(i % 2 == 0) {
					number = Integer.parseInt(cardNumber.substring(i,i + 1)) * 2;
				}//end if
					if(number > 10) {
						number = 1 + number - 10;
					}//end inner if
				else {
					number = Integer.parseInt(cardNumber.substring(i, i + 1));
				}//end else
				sum = sum + number;
			}//end for
			if(sum % 10 != 0) {
				System.out.println("Error: Card number is not legitimate.");
				rejectCard(103);
			}//end if
			
		this.expirationDate = expirationDate;
			int cardYear = Integer.parseInt(expirationDate.substring(2,3));
			int cardMonth = Integer.parseInt(expirationDate.substring(0,1));
			int currentYear = Integer.parseInt((java.time.LocalDateTime.now().toString().substring(2,4))); 
			int currentMonth = Integer.parseInt((java.time.LocalDateTime.now().toString().substring(5,7)));
			if((cardYear < currentYear) || ((cardMonth < currentMonth) && (cardYear == currentYear))){
				System.out.println("Error: Credit card has expired.");
				rejectCard(104);
			}//end if
		this.securityCode = securityCode;
			if(securityCode - 100 < 0){
				System.out.println("Error: Security code has too few digits.");
				rejectCard(105);
			}//end if
			else if(securityCode - 100 > 899) {
				System.out.println("Error: Security code has too many digits.");
				rejectCard(106);
			}//end else if
	}//end card constructor
	
	public boolean rejectCard(int errorCode){
			if(errorCode > 100 && errorCode < 200) {
				reject = true;
			}//end if
		return reject;
	}//end rejectCard method
}//end class
