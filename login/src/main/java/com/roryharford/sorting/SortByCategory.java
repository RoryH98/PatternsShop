package com.roryharford.sorting;

import java.util.Collections;
import java.util.List;

import com.roryharford.item.Item;

public class SortByCategory implements SortingStrategy  {


	@Override
	public List<Item> ascendingSort(List<Item> items) {
		// TODO Auto-generated method stub
		Collections.sort(items, (o1, o2) -> o1.getCategory().compareTo(o2.getCategory()));

		return items;
	}

	@Override
	public List<Item> descendingSort(List<Item> items) {
		// TODO Auto-generated method stub
		Collections.sort(items, (o1, o2) -> o1.getCategory().compareTo(o2.getCategory()));
		Collections.reverse(items);
		
		return items;
	}
}
