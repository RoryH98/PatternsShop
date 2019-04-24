package com.roryharford.loyaltycard;

public class Standard implements LoyaltyCardDiscount {

	private final String name;
	
	@Override
	public double discount(double amount) {
		// TODO Auto-generated method stub
		 amount = amount -(int)(amount*(10/100.0f));
			return amount;
	}
	
	public Standard (String name) {
		this.name = name;
	
	}

}
