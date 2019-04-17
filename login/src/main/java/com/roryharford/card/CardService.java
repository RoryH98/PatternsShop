package com.roryharford.card;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.roryharford.user.User;
import com.roryharford.user.UserRepository;

@Service
public class CardService {

	// links it with the customerRepository
	@Autowired
	private CardRepository cardRepository;
	
	private ArrayList<Card> cart = new ArrayList<Card>();
	
	public List<Card> getAllCards() {
		// connects to the database and runs a query
		List<Card> Cards = new ArrayList<>();
		// adds each User into the array
		cardRepository.findAll().forEach(Cards::add);
		return Cards;
	}

	public Card getCard(int id) {
		// return Users.stream().filter(t -> t.getId().equals(id)).findFirst().get();
		// It knows the id is a String because we set it in the User class
		return cardRepository.getOne( id);
	}
	

//	public User getUserByEmail(String email) {
//		// return Users.stream().filter(t -> t.getId().equals(id)).findFirst().get();
//		// It knows the id is a String because we set it in the User class
//		return customerRepository.getOne(email);
//	}

	public void addCard(Card Card) {
		cardRepository.save(Card);
	}

	public void updateCard(String id, Card Card) {
		// A save can update and add a User because the User has information about what
		// it is an a repository can check if it already exists or not.
		cardRepository.save(Card);
	}
	



//	public void deleteUser(int id) {
//		customerRepository.deleteById( id);
//	}
	
}
