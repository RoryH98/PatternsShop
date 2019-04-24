package com.roryharford.loyaltycard;

public class Gold implements LoyaltyCardDiscount {

//	 private final String name;
	private static Gold instance = new Gold();

	// instantiated
	private Gold() {

	}

	public static Gold getInstance() {
		return instance;
	}

	@Override
	public double discount(double amount) {
		// TODO Auto-generated method stub
//		amount = amount-(amount*100/30);
		amount = amount - (int) (amount * (30 / 100.0f));
		return amount;
	}

}

//public class SingleObject {
//
//	   //create an object of SingleObject
//	   private static SingleObject instance = new SingleObject();
//
//	   //make the constructor private so that this class cannot be
//	   //instantiated
//	   private SingleObject(){}
//
//	   //Get the only object available
//	   public static SingleObject getInstance(){
//	      return instance;
//	   }
//
//	   public void showMessage(){
//	      System.out.println("Hello World!");
//	   }
//	}
