package com.roryharford.item;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.roryharford.cart.Cart;
import com.roryharford.decorator.TypeOfUser;
import com.roryharford.loyaltycard.Gold;
import com.roryharford.loyaltycard.Silver;
import com.roryharford.loyaltycard.Standard;
import com.roryharford.order.Order;
import com.roryharford.order.OrderService;
import com.roryharford.user.Admin;
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

	Cart cart = new Cart();

	@RequestMapping(value = "/addItemToCart", method = RequestMethod.POST)
	public String addToCart(Model model, @RequestParam("id") String id) {
		System.out.println("ADDED THE ITEM TO THE CART");
		int newId = Integer.parseInt(id);
		Item item = itemService.getItem(newId);

		cart.addItemToCart(item);

		System.out.println("\n Cart Size now " + cart.getCart().size());
		model.addAttribute("lists", itemService.getAllItems());
		return "success";
	}

//	@RequestMapping(value = "/AscendingByName", method = RequestMethod.POST)
//	public String addToCart(Model model) {
//		System.out.println("ADDED THE ITEM TO THE CART");
//		int newId = Integer.parseInt(id);
//		Item item = itemService.getItem(newId);
//
//		cart.addItemToCart(item);
//
//		System.out.println("\n Cart Size now " + cart.getCart().size());
//		model.addAttribute("lists", itemService.getAllItems());
//		return "success";
//	}

	@RequestMapping(value = "/viewCart", method = RequestMethod.GET)
	public String viewCart(Model model) {
		System.out.println("\n Cart Size now " + cart.getCart().size());
		model.addAttribute("cartPrice", cart.calcTotalCost());
		model.addAttribute("lists", cart.getCart());
		return "usersCart";
	}

	@RequestMapping(value = "/confirmLoyaltyCard")
	public String pickLoyaltyCard(Model model, @RequestParam String loyaltyCard) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.getUserByEmail(auth.getName());
		model.addAttribute("card", loyaltyCard);

		if (loyaltyCard.equalsIgnoreCase("Gold")) {
			Gold object = Gold.getInstance();
			cart.setTotal(cart.discount(object));
		} else if (loyaltyCard.equalsIgnoreCase("Silver")) {
			Silver object = Silver.getInstance();
			cart.setTotal(cart.discount(object));
		} else if (loyaltyCard.equalsIgnoreCase("Standard")) {
			Standard object = Standard.getInstance();
			cart.setTotal(cart.discount(object));
		}

		model.addAttribute("cartPrice", cart.getTotal());
		model.addAttribute("lists", cart.getCart());
		return "usersCartAfterLoyalty";
	}

	@RequestMapping(value = "/searchProducts", method = RequestMethod.GET)
	public String searchProducts(Model model, @RequestParam("keyword") String keyword, HttpSession session) {
		ArrayList<Item> items = new ArrayList<>();
		System.out.println(itemService.getAllItems().size());
		for (int i = 0; i < itemService.getAllItems().size(); i++) {
			if (itemService.getAllItems().get(i).getCategory().contains(keyword)
					|| itemService.getAllItems().get(i).getItemName().contains(keyword)
					|| itemService.getAllItems().get(i).getManufacturer().contains(keyword)) {
				items.add(itemService.getAllItems().get(i));
			}
		}
		session.setAttribute("searchList", items);
		model.addAttribute("lists", items);
		return "success";
	}

	@RequestMapping(value = "/updateStock", method = RequestMethod.POST)
	public String updateStock(Model model, @RequestParam("id") String id) {
		System.out.println("ADDED THE ITEM TO THE CART");
		int newId = Integer.parseInt(id);
		Item item = itemService.getItem(newId);
		model.addAttribute("list", item);
		return "updateStock";
	}

	

	@RequestMapping(value="/orderInProduct",method=RequestMethod.POST)
	public String orderInProduct(Model model,@RequestParam("id") String id,@RequestParam("amount") String amount) {
		int newAmount = Integer.parseInt(amount);
		int newId = Integer.parseInt(id);
		Item item = itemService.getItem(newId);
		item.setStock(item.getStock()+newAmount);
        itemService.updateItem(item.getId(), item);
       
		System.out.println("\n Cart Full Price " + cart.calcTotalCost());
		// model.addAttribute("lists", itemService.getCart());
		
		model.addAttribute("price", cart.calcTotalCost());
		model.addAttribute("lists", itemService.getAllItems());
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			User user = userService.getUserByEmail(auth.getName());
		TypeOfUser user1;
		 if (user.getEmail().equalsIgnoreCase("Admin@Admin.Admin") && user.getPassword().equalsIgnoreCase("$2a$10$HtFJVCLq2OCduYJzySEQfu1YztpUZfaTvfyZBufgrWGdjCUigYPHe")) {
//				session.setAttribute("admin", username);
			
				 user1 = new Admin();
				return user1.login();

		} 
		 else
		 {
			 user1 = new User();
			 return user1.login();
		 }

	}

	@RequestMapping(value = "/goToPayment", method = RequestMethod.GET)
	public String payment(Model model) {
		System.out.println("\n Cart Full Price " + cart.calcTotalCost());
		// model.addAttribute("lists", itemService.getCart());
		model.addAttribute("price", cart.calcTotalCost());
		if (cart.calcTotalCost() == 0) {
			cart.setTotal(cart.calcTotalCost());
		}

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.getUserByEmail(auth.getName());
		Order order = new Order(1);
		orderService.addOrder(order);
		Item item;
		System.out.println("\n Cart Size now " + cart.getCart().get(0).getItemName());
		for (int i = 0; i < cart.getCart().size(); i++) {
			item = cart.getCart().get(i);
			item.setStock(item.getStock() - 1);
			System.out.println(item.getItemName());
			order.addItem(item);
		}
		user.getOrders().add(order);
		order.setAmount(cart.getTotal());

		orderService.updateOrder(order.getId(), order);

		System.out.println("User Purchased Basket");
		model.addAttribute("lists", itemService.getAllItems());
		return "success";
	}

}
