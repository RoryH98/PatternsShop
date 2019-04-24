package com.roryharford.item;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.roryharford.comment.Comment;
import com.roryharford.user.User;
import com.roryharford.user.UserRepository;

@Service
public class ItemService {

	// links it with the customerRepository
	@Autowired
	private ItemRepository itemRepository;
	
	
	
	public List<Item> getAllItems() {
		// connects to the database and runs a query
		List<Item> items = new ArrayList<>();
		// adds each User into the array
		itemRepository.findAll().forEach(items::add);
		return items;
	}

	public Item getItem(int id) {
		// return Users.stream().filter(t -> t.getId().equals(id)).findFirst().get();
		// It knows the id is a String because we set it in the User class
		return itemRepository.getOne( id);
	}
	
//	public double getPrice() {
//		double price = 0;
//		for(int i=0; i<cart.size();i++) {
//			price = price + cart.get(i).getPrice();
//		}
//		return price;
//	}
	
//	public User getUserByEmail(String email) {
//		// return Users.stream().filter(t -> t.getId().equals(id)).findFirst().get();
//		// It knows the id is a String because we set it in the User class
//		return customerRepository.getOne(email);
//	}

	public void addItem(Item item) {
		itemRepository.save(item);
	}

	public void updateItem(int newId, Item item) {
		// A save can update and add a User because the User has information about what
		// it is an a repository can check if it already exists or not.
		itemRepository.save(item);
	}
	
	
	
	

	public List<Comment> getCommentForItem(int newId) {
		Item item =this.getItem(newId);
		
		return item.getComments();
	}

//	public void deleteUser(int id) {
//		customerRepository.deleteById( id);
//	}
	
}
