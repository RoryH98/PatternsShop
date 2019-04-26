package com.roryharford.sorting;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.roryharford.item.Item;

public class SortByPrice implements SortingStrategy {


	@Override
	public List<Item> ascendingSort(List<Item> items) {
		// TODO Auto-generated method stub
		Collections.sort(items, new Comparator<Item>() {
		    public int compare(Item c1, Item c2) {
		        return Double.compare(c1.getPrice(), c2.getPrice());
		    }
		});

		return items;
	}

	@Override
	public List<Item> descendingSort(List<Item> items) {
		// TODO Auto-generated method stub
		Collections.sort(items, new Comparator<Item>() {
		    public int compare(Item c1, Item c2) {
		        return Double.compare(c1.getPrice(), c2.getPrice());
		    }
		});
		Collections.reverse(items);

		return items;
	}


}
