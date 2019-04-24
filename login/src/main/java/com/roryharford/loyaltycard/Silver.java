package com.roryharford.loyaltycard;

public class Silver implements LoyaltyCardDiscount {
//	 private final String name;
	private static Silver instance = new Silver();

	// instantiated
	private Silver() {

	}

	public static Silver getInstance() {
		return instance;
	}

	@Override
	public double discount(double amount) {
		// TODO Auto-generated method stub
		
		 amount = amount -(int)(amount*(20/100.0f));
			return amount;
	}
	
	
	
}
