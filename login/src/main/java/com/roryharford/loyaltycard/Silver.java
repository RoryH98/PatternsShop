package com.roryharford.loyaltycard;

public class Silver implements LoyaltyCardDiscount {
	
	  private final String name;

	  
	  public Silver(String name) {
			this.name = name;

		}

	@Override
	public double discount(double amount) {
		// TODO Auto-generated method stub
		
		 amount = amount -(int)(amount*(20/100.0f));
			return amount;
	}
	
	
	
}
