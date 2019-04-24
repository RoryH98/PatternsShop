package com.roryharford.order;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.roryharford.item.Item;


@Entity
@Table(name = "orders")
public class Order {
	
//	@ManyToMany(cascade = CascadeType.ALL)
////	@JoinTable(name = "id", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "item_id"))
//	private Set<Item>items = new HashSet<>();

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private double amount;
	
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToMany
	List<Item> items = new ArrayList<>();
	
	
	

	public Order() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Order(int amount) {
		this.amount=amount;
		
	}

	public List<Item> getItems() {
		return this.items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}
	
	
	
	public double getAmount() {
		return amount;
	}

	public void setAmount(double d) {
		this.amount = d;
	}

	public void addItem(Item item) {
		System.out.println("FWHOUWHOI"+item.getManufacturer());
		items.add(item);
	}
	
	
	

}
