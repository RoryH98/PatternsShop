package com.roryharford.loyaltycard;

public class Standard implements LoyaltyCardDiscount {

	private static Standard instance = new Standard();

	// instantiated
	private Standard() {

	}

	public static Standard getInstance() {
		return instance;
	}
	
	@Override
	public double discount(double amount) {
		// TODO Auto-generated method stub
		 amount = amount -(int)(amount*(10/100.0f));
			return amount;
	}


}
