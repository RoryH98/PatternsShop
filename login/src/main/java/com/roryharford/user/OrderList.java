package com.roryharford.user;

import java.util.ArrayList;
import java.util.List;

import com.roryharford.item.Item;
import com.roryharford.order.Order;

public class OrderList implements Container {

	public List<Order> order;

	public OrderList(List<Order> order2) {
		this.order = order2;
	}

	@Override
	public Iterator getIterator() {
		// TODO Auto-generated method stub
		return new OrderIterator();
	}

	private class OrderIterator implements Iterator {
		int index;

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			if (index < order.size()) {
				return true;
			}
			return false;
		}

		@Override
		public Object next() {
			// TODO Auto-generated method stub
			if (this.hasNext()) {
				return order.get(index++);
			}
			return null;
		}
	}
}
