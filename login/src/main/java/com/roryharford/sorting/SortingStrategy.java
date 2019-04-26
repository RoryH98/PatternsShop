package com.roryharford.sorting;

import java.util.List;

import com.roryharford.item.Item;

public interface SortingStrategy {

	public List<Item> ascendingSort(List<Item> items);
	
	public List<Item> descendingSort(List<Item> items);
}
