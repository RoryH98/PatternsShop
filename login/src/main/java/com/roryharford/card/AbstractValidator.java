package com.roryharford.card;

import java.util.Calendar;


/* Abstract class containing template method */

/* Common structure and common methods across classes so makes
 * sense to use an abstract/concrete class arrangement rather 
 * than an interface */

public abstract class AbstractValidator {

	/* Variables are protected so that they can be accessed by the concrete
	 * subclasses */
	
	/* Passed to validation methods to allow messages to be displayed over app */
	protected CardValidator app;

	protected String firstName;
	protected String surname;
	protected String address;
	protected String town;
	protected String county;
	protected String cardNumber;
	protected int month;
	protected int year;

	/* No need to pass card type as a parameter because this check is being done in the
	 * main application */
	
	public AbstractValidator(CardValidator app, String firstName, String surname, String address, String town, String county,
			String cardNumber, int month, int year) {

		this.app = app;
		this.firstName = firstName;
		this.surname = surname;
		this.address = address;
		this.town = town;
		this.county = county;
		this.cardNumber = cardNumber;
		this.month = month;
		this.year = year;

	}

	/* Template method
	 * 
	 * We define our algorithm as a series of steps, maintaining the sequence whereby each test
	 * is only performed if the previous step has succeeded.
	 * 
	 */
	
	public boolean validate() {

		boolean nameAddressValidated = validateNameAddress();
		
		if (nameAddressValidated) {

			boolean expiryDateValidated = validateExpiryDate();
			
			if (expiryDateValidated) {
			
				boolean cardNumberLengthValidated = validateCardNumberLength();
				
				if (cardNumberLengthValidated) {

					boolean cardNumberFormatValidated = validateCardNumberFormat();
					
					if (nameAddressValidated && expiryDateValidated && cardNumberLengthValidated && cardNumberFormatValidated)
						return true;
					else
						return false;
					
				}
			}
		}
		
		return false;

	}

	/* These two stages are the same across all card types so it makes sense
	 * to define them only once in the abstract class and allow the concrete
	 * classes to inherit directly
	 */
	
	protected boolean validateNameAddress() {

		boolean errorInName = false;

		if (firstName.length() == 0) {
			System.out.println("First Name Missing");
			errorInName = true;
		}
		if (surname.length() == 0) {
			System.out.println("Surname Missing");
			errorInName = true;
		}
		if (address.length() == 0) {
			System.out.println("Address Missing");
			errorInName = true;
		}
		if (town.length() == 0) {
			System.out.println("Town Missing");
			errorInName = true;
		}
		if (county.length() == 0) {
			System.out.println("County Missing");
			errorInName = true;
		}

		return !errorInName;
	}

	protected boolean validateExpiryDate() {

		boolean errorInDate = false;

		Calendar rightNow = Calendar.getInstance();

		int thisMonth = rightNow.get(Calendar.MONTH) + 1;

		int thisYear = rightNow.get(Calendar.YEAR);

		if (year < thisYear || (year == thisYear && month < thisMonth)) {

			System.out.println("Card is out of date");
			errorInDate = true;

		}

		return !errorInDate;

	}

	/* This method only differs for one of the subclasses (American Express) so
	 * it makes more sense to define it as a default here and allow subclasses 
	 * to override where necessary */
	
	protected boolean validateCardNumberLength() {

		boolean errorInNumber = false;

		if (cardNumber.length() != 16) {

			System.out.println("Card number must be 16 digits long");
			errorInNumber = true;

		}
		else {

			for (int i = 0; i < 16; i++) {

				if (cardNumber.charAt(i) > '9' || cardNumber.charAt(i) < '0') {

					System.out.println("Card number must consist of all digits");
					errorInNumber = true;									

				}
			}
		}

		return !errorInNumber;

	}

	/* This method differs for every concrete class so we define a stub here */
	
	protected boolean validateCardNumberFormat() {

		return false;

	}
	
}
