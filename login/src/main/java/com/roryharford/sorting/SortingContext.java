package com.roryharford.sorting;

import java.util.List;

import com.roryharford.item.Item;

public class SortingContext {
	
	private SortingStrategy strategy;
	
	public void setSortingMethod(SortingStrategy strategy) {
		this.strategy = strategy;
	}
	
	public SortingStrategy getStrategy() {
		return strategy;
	}
	
	public List<Item> ascendingSort(List<Item> items){
		return strategy.ascendingSort(items);
	}
	public List<Item> descendingSort(List<Item> items){
		return strategy.descendingSort(items);
	}
}
