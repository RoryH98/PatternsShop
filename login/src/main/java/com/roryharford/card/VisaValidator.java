package com.roryharford.card;
import javax.swing.JOptionPane;

public class VisaValidator extends AbstractValidator {

	public VisaValidator(CardValidator app, String firstName, String surname, String address, String town,
			String county, String cardNumber, int month, int year) {

		super(app, firstName, surname, address, town, county, cardNumber, month, year);

	}

	/* Overridden method */

	protected boolean validateCardNumberFormat() {

		/* Check number is in correct format for Visa */

		boolean errorInFormat = false;

		if (cardNumber.charAt(0) != '4') {

			System.out.println("Card format incorrect");
			errorInFormat = true;									

		}
		else {


		}

		return !errorInFormat;

	}
}
