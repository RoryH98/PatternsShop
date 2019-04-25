package com.roryharford.user;

import java.util.ArrayList;

public class CustomerList implements Container {

	public ArrayList<User> user;
	
	public CustomerList(ArrayList<User> user) {
		this.user=user;
	}

	@Override
	public Iterator getIterator() {
		// TODO Auto-generated method stub
		return new CustomerIterator();
	}

	private class CustomerIterator implements Iterator {
		int index;

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			if (index < user.size()) {
				return true;
			}
			return false;
		}

		@Override
		public Object next() {
			// TODO Auto-generated method stub
			if (this.hasNext()) {
				return user.get(index++);
			}
			return null;
		}

	}

}
