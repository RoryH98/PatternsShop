package com.roryharford.order;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


//Starting application starts embedded database apache derby
@Service
public class OrderService {

	// links it with the customerRepository
	@Autowired
	private OrderRepository orderRepository;

//	private ArrayList<Event> list = new ArrayList<Event>();

	public List<Order> getAllOrders() {
		// connects to the database and runs a query
		List<Order> orders = new ArrayList<>();
		// adds each User into the array
		orderRepository.findAll().forEach(orders::add);
		return orders;
	}

	public Order getOrder(int id) {
		// return Users.stream().filter(t -> t.getId().equals(id)).findFirst().get();
		// It knows the id is a String because we set it in the User class
		return orderRepository.getOne( id);
	}
	
//	public User getUserByEmail(String email) {
//		// return Users.stream().filter(t -> t.getId().equals(id)).findFirst().get();
//		// It knows the id is a String because we set it in the User class
//		return customerRepository.getOne(email);
//	}

	public void addOrder(Order order) {
		orderRepository.save(order);
	}

	public void updateOrder(int i, Order order) {
		// A save can update and add a User because the User has information about what
		// it is an a repository can check if it already exists or not.
		orderRepository.save(order);
	}

//	public void deleteUser(int id) {
//		customerRepository.deleteById( id);




}
