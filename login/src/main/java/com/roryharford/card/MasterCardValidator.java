package com.roryharford.card;
import javax.swing.JOptionPane;

public class MasterCardValidator extends AbstractValidator {

	public MasterCardValidator(CardValidator app, String firstName, String surname, String address, String town,
			String county, String cardNumber, int month, int year) {
		
		super(app, firstName, surname, address, town, county, cardNumber, month, year);

	}
	
	/* Overridden method */

	protected boolean validateCardNumberFormat() {

		/* Check number is in correct format for MasterCard */

		boolean errorInFormat = false;
		
		if (cardNumber.charAt(0) == '5' && (cardNumber.charAt(1) >= '1' && cardNumber.charAt(1) <= '5')) {
								
		}
		else {
				
			System.out.println( "Card format incorrect");
			errorInFormat = true;									
											
		}
		
		return !errorInFormat;
			
	}

}
