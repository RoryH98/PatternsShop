package com.roryharford.card;

import javax.swing.*;

import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;
import java.util.Date;

@Service
public class CardValidator {

//	JButton validateSaveButton, cancelButton;
//	String firstNameLabel, surnameLabel, addressLabel, townLabel, countyLabel, cardNumberLabel, cardTypeLabel, expiryDateLabel;
//	JTextField firstNameTextField, surnameTextField, addressTextField, townTextField, countyTextField, cardNumberTextField;
//	JComboBox<String> cardTypeComboBox, monthComboBox, yearComboBox;
//	String[] months = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
//	String[] years = {"2019", "2020", "2021", "2022", "2023"};
//	String[] cardTypes = {"Visa", "MasterCard", "AmericanExpress"};

	public CardValidator() {

//		super("Card Validator");

//		initComponents(Card card);

	}

	public Card initComponents(Card card) {
		{

			String firstName = card.getFirstName();
			String surname = card.getSurname();
			String address = card.getAddress();
			String town = card.getTown();
			String county = card.getCounty();
			String cardNumber = card.getCardNumber();
			int cardType = card.getCardType();
			int month = card.getExpiryMonth();
			int year = card.getExpiryYear();

			boolean result = false;

			/*
			 * Using an abstract/concrete class arrangement here makes for much simpler code
			 */

			/* Validator object - reference to abstract */
			AbstractValidator validator = null;

			/*
			 * Depending on the card type chosen, we instantiate the validator with an
			 * object of the appropriate concrete class
			 */

			if (cardType == 1) {

				validator = new VisaValidator(CardValidator.this, firstName, surname, address, town, county, cardNumber,
						month, year);

			} else if (cardType == 2) {

				validator = new MasterCardValidator(CardValidator.this, firstName, surname, address, town, county,
						cardNumber, month, year);

			} else if (cardType == 3) {

				validator = new AmericanExpressValidator(CardValidator.this, firstName, surname, address, town, county,
						cardNumber, month, year);

			}

			/*
			 * Just in case the validator hasn't been instantiated, we check that it's
			 * non-null
			 */

			if (validator != null) {

				/*
				 * Call template method - exact implementation will depend on type of concrete
				 * class object
				 */

				result = validator.validate();

				/* Success */
				if (result) {

					Card validatedCard = new Card(firstName, surname, address, town, county, cardNumber, cardType,
							month, year);
					return validatedCard;

				}
			}

		}
		return null;

	}

}
