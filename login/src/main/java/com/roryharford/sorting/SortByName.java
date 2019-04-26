package com.roryharford.sorting;


import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.roryharford.item.Item;

public class SortByName implements SortingStrategy  {

	@Override
	public List<Item> ascendingSort(List<Item> items) {
		Collections.sort(items, 
                (o1, o2) -> o1.getItemName().compareTo(o2.getItemName()));
		for(int i=0; i<items.size();i++) {
			System.out.println(i+"st One "+items.get(i).getItemName());
		}
		  return items;
	}

	@Override
	public List<Item> descendingSort(List<Item> items) {
		// TODO Auto-generated method stub
		Collections.sort(items, 
                (o1, o2) -> o1.getItemName().compareTo(o2.getItemName()));
		Collections.reverse(items);
		for(int i=0; i<items.size();i++) {
			System.out.println(i+"st One "+items.get(i).getItemName());
		}
		return items;
	}

	
	
}
