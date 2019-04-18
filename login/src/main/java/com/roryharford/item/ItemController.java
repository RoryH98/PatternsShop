package com.roryharford.item;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.roryharford.order.Order;
import com.roryharford.order.OrderService;
import com.roryharford.user.User;
import com.roryharford.user.UserService;

@Controller
public class ItemController {
	
	@Autowired
	private ItemService itemService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private OrderService orderService;


	
	@RequestMapping(value = "/addItemToCart", method = RequestMethod.POST)
	public String addToCart(Model model,@RequestParam("id") String id) {
		System.out.println("ADDED THE ITEM TO THE CART");
		int newId = Integer.parseInt(id);
		Item item = itemService.getItem(newId);
		
		itemService.addItemToCart(item);
		
		System.out.println("\n Cart Size now "+itemService.getCart().size());
		model.addAttribute("lists", itemService.getAllItems());
		return "success";
	}
	
	@RequestMapping(value = "/viewCart", method = RequestMethod.GET)
	public String viewCart(Model model) {
		System.out.println("\n Cart Size now "+itemService.getCart().size());
		model.addAttribute("cartPrice", itemService.getPrice());
		model.addAttribute("lists", itemService.getCart());
		return "usersCart";
	}
	
	@RequestMapping(value = "/searchProducts", method = RequestMethod.GET)
	public String searchProducts(Model model, @RequestParam("keyword") String keyword) {
		ArrayList<Item> items = new ArrayList<>();
		System.out.println(itemService.getAllItems().size());
		for(int i=0; i<itemService.getAllItems().size();i++) {
			if(itemService.getAllItems().get(i).getCategory().contains(keyword) || itemService.getAllItems().get(i).getItemName().contains(keyword) || itemService.getAllItems().get(i).getManufacturer().contains(keyword)) {
				items.add(itemService.getAllItems().get(i));
			}
		}
		model.addAttribute("lists",items);
		return "success";
	}
	
	@RequestMapping(value = "/goToPayment", method = RequestMethod.GET)
	public String payment(Model model) {
		System.out.println("\n Cart Full Price "+itemService.getPrice());
	//	model.addAttribute("lists", itemService.getCart());
		model.addAttribute("price", itemService.getPrice());
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.getUserByEmail(auth.getName());
		Order order = new Order(1);
		orderService.addOrder(order);
		Item item ;
		System.out.println("\n Cart Size now "+itemService.getCart().get(0).getItemName());
		for(int i=0; i<itemService.getCart().size();i++) {
		item =	itemService.getCart().get(i);
		item.setStock(item.getStock()-1);
		System.out.println(item.getItemName());
		order.addItem(item);
		}
		user.getOrders().add(order);
		
		orderService.updateOrder(order.getId(), order);
		
		System.out.println("User Purchased Basket");
		return "success";
	}
	
}
