package data;

/**
 * Static class for payment related functions.
 */
public class PaymentManager {

	/**
	 * Adds a given monetary balance to a specified card.
	 * @param amount amount of money to add
	 * @param card card to add money to
	 * @return whether the addition was successful or not
	 */
	private static boolean addBalance(float amount, Card card) {
		//Return true right now since we're just simulating
		//the addition.
		return true;
	}
	
	/**
	 * Removes a given monetary balance from a specified card.
	 * @param amount amount of money to remove
	 * @param card card to add money to
	 * @return whether the addition was successful or not
	 */
	public static boolean subtractBalance(float amount, Card card) {
		//Return true right now since we're just simulating
		//the subtraction.
		return addBalance(amount, card);
	}
	
	/**
	 * Checks to see if a card is actually valid or not and returns
	 * this validiy.
	 * @param card card to check
	 * @return true if the card is valid; false otherwise.
	 */
	public static boolean checkCard(Card card) {
		//TODO: sometimes this will need to return false so we can test error handling.
		//Return true since this is just a simulation.
		if(card.reject == true) {
			return false;
		}//end if
		else {
			return true;
		}//end else
	}
	
}
