package com.roryharford.item;

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
import javax.persistence.Table;

import com.roryharford.comment.Comment;
import com.roryharford.order.Order;

//Creates Table in DB
@Entity
@Table(name = "item")
public class Item {

	// Marks Id as Primary key
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String itemName;
	private double price;
	private double stock;
	private String imageUrl;
	private String manufacturer;
	private String category;
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private List<Comment> comments = new ArrayList<>();
	

	public Item() {

	}

	public Item(String itemName, double price, double stock, String imageUrl, String manufacturer, String category) {

		this.itemName = itemName;
		this.price = price;
		this.stock = stock;
		this.imageUrl = imageUrl;
		this.manufacturer = manufacturer;
		this.category = category;
	}
	
	

	
	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public double getStock() {
		return stock;
	}

	public void setStock(double stock) {
		this.stock = stock;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

}
