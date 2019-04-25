package com.roryharford.cart;

import java.util.ArrayList;
import java.util.List;

import com.roryharford.item.Item;
import com.roryharford.loyaltycard.LoyaltyCardDiscount;

public class Cart {

	double total = 0.0;

	private List<Item> items = new ArrayList<>();

	public Cart() {

	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public double calcTotalCost() {

		double total = 0.0;
		for (Item item : items) {

			total += item.getPrice();
		}

		return total;
	}

	public double discount(LoyaltyCardDiscount method) {

		double totalCost = calcTotalCost();
		// setTotal(totalCost);
		System.out.println("le price " + method.discount(totalCost));
		return method.discount(totalCost);

	}

	public void addItemToCart(Item item) {
		items.add(item);
	}

	public void removeItemToCart(Item item) {
		items.remove(item);
	}

	public List<Item> getCart() {
		// connects to the database and runs a query
		return items;
	}

	public Item getCartItem(int i) {

		return this.items.get(i);
	}

//		  public boolean discount(LoyaltyCardDiscount method) {
//		 
//		    double totalCost = calcTotalCost();
//		    return method.discount(totalCost);
//		 }

}
