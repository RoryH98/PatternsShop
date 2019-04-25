package com.roryharford.user;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.roryharford.card.Card;
import com.roryharford.decorator.TypeOfUser;
import com.roryharford.order.Order;

//Creates Table in DB
@Entity
@Table(name = "user")
public class User  implements TypeOfUser{

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;
	
	@OneToOne( cascade = {CascadeType.ALL})
	Card card;
	
	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private List<Order> orders = new ArrayList<>();
	// Marks Id as Primary key
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private int active;
	private String username;
	private String choice;
	private String email;
	private String shipping_Address;

	private String password;

	public User() {

	}

	public User(String username, String choice, String email, String shipping_Address, String password,int active) {
		this.username = username;
		this.choice = choice;
		this.email = email;
		this.shipping_Address = shipping_Address;
		this.password = password;
	}
	
	

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return username;
	}

	public void setName(String name) {
		this.username = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getChoice() {
		return choice;
	}

	public void setChoice(String choice) {
		this.choice = choice;
	}

	public String getShipping_Address() {
		return shipping_Address;
	}

	public void setShipping_Address(String shipping_Address) {
		this.shipping_Address = shipping_Address;
	}

	// public void addTicket(Ticket ticket) {
//		tickets.add(ticket);
//	}
//
//	public List<Ticket> getTickets() {
//		return tickets;
//	}
//
//	public void setTickets(List<Ticket> tickets) {
//		this.tickets = tickets;
//	}
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		// TODO Auto-generated method stub
		this.roles = roles;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	@Override
	public String login() {
		// TODO Auto-generated method stub
		return "success";
	}
	
	

}
