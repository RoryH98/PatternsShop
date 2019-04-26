package com.roryharford.sorting;

import java.util.Collections;
import java.util.List;

import com.roryharford.item.Item;

public class SortByManufacturer implements SortingStrategy {


	@Override
	public List<Item> ascendingSort(List<Item> items) {
		// TODO Auto-generated method stub
		Collections.sort(items, (o1, o2) -> o1.getManufacturer().compareTo(o2.getManufacturer()));

		return items;
	}

	@Override
	public List<Item> descendingSort(List<Item> items) {
		// TODO Auto-generated method stub
		Collections.sort(items, (o1, o2) -> o1.getManufacturer().compareTo(o2.getManufacturer()));
		Collections.reverse(items);
		
		return items;
	}

}