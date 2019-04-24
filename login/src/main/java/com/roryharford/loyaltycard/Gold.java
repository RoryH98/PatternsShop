package com.roryharford.loyaltycard;
public class Gold implements LoyaltyCardDiscount{

	 private final String name;
	 
	@Override
	public double discount(double amount) {
		// TODO Auto-generated method stub
//		amount = amount-(amount*100/30);
		 amount = amount -(int)(amount*(30/100.0f));
		return amount;
	}
	
	public Gold(String name ) {
		
		this.name = name;
	   
	}

}
