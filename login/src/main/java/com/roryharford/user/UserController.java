package com.roryharford.user;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.catalina.connector.Request;
import org.apache.jasper.tagplugins.jstl.core.Url;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.roryharford.card.Card;
import com.roryharford.card.CardService;
import com.roryharford.card.CardValidator;
import com.roryharford.decorator.TypeOfUser;
import com.roryharford.item.InStockItem;
//import com.roryharford.event.Event;
//import com.roryharford.ticket.Ticket;
//import com.roryharford.ticket.TicketController;
//import com.roryharford.ticket.TicketService;
import com.roryharford.item.Item;
import com.roryharford.item.ItemService;
import com.roryharford.item.ItemState;
import com.roryharford.item.OutOfItem;

//will eventually be mapped to Customer
@Controller
public class UserController {
	// since not an api think of adapting it more
	private ArrayList<Item> list = new ArrayList<Item>();

	@Autowired
	private UserService userService;

	@Autowired
	private ItemService itemService;

	@Autowired
	private CardService cardService;

	@Autowired
	private CardValidator cardValidator;

//	@Autowired
//	private TicketService ticketService;

	@RequestMapping(value = "/")
	public String index() {

		return "homepage";
	}

//	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
//	public String login(@RequestParam("email") String email,@RequestParam("password") String password) {
////		String username = request.getParameter("username");
////		String password = request.getParameter("password");
//		System.out.println("HELLO "+password);
//		 if (email.equalsIgnoreCase("Admin@Admin.Admin") && password.equalsIgnoreCase("Admin123")) {
////				session.setAttribute("admin", username);
//				
////				UserType user1 = new AdminUser();
////				return user1.login();
//
//			} 
//
//		return "homepage";
//	}

	@RequestMapping(value = "/homepage")
	public String redirect(Model model, HttpSession session) {
//		Item item = new Item("Toaster",20,1"https://target.scene7.com/is/image/Target/GUEST_087da4b9-d9a0-47ad-bed0-e39af7bcf89b?wid=488&hei=488&fmt=pjpeg");
//		list.add(item);
		List<Item> items = itemService.getAllItems();

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.getUserByEmail(auth.getName());
        System.out.println("Users password "+user.getPassword());
		
		boolean state;
		ItemState outOfItem = new OutOfItem();
		ItemState inStockItem = new InStockItem();
//		 User newAdmin = new User("Admin", "N/A", "Admin@Admin.Admin", "N/A", "Admin", 2);
//		 userService.createCustomer(newAdmin);
		for (int i = 0; i < itemService.getAllItems().size(); i++) {
			Item item = itemService.getItem(i + 1);
			if (itemService.getAllItems().get(i).getStock() <= 0) {
				state = outOfItem.stateOfStock();
				item.setItemState(state);
				System.out.println("Item name Plus " + state);
//				 model.addAttribute("state","Out Of Stock");
			} else {
				state = inStockItem.stateOfStock();
				item.setItemState(state);
				System.out.println("Item name Plus " + item.getItemName() + "State of item " + state);
//				 state = inStockItem.stateOfStock();
//				 model.addAttribute("state","In Stock");
			}
			itemService.updateItem(item.getId(), item);

		}
		System.out.println("AMOUNT" + itemService.getAllItems().size());
		session.setAttribute("searchList", items);
		model.addAttribute("lists", items);
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

	@RequestMapping("/Admin")
	public String Admin() {
		System.out.println("\nGoing to the Admin Page");
		return "success";
	}

	@RequestMapping("/userDetails")
	public String userDetails(Model model) {
		final ArrayList<User> users;
		users = (ArrayList<User>) userService.getAllUsers();
		CustomerList user = new CustomerList(users);

		ArrayList<User> listAll = new ArrayList<User>();
		for (Iterator iter = user.getIterator(); iter.hasNext();) {
			User user1 = (User) iter.next();
			listAll.add(user1);
		}
		model.addAttribute("lists", listAll);
		return "usersTickets";

	}

	@RequestMapping("/users")
	public List<User> getAllUsers() {
		System.out.println("ENTERED " + userService.getAllUsers().toString());
		return userService.getAllUsers();
	}

//	@RequestMapping("/users/{id}")
//	public User getUser(@PathVariable String id) {
//		return userService.getUser(id);
//	}

	@PostMapping(value = "/users")
	public void addUser(@RequestBody User User) {
		userService.addUser(User);
	}

	@PutMapping(value = "/users/{id}")
	public void updateUser(@RequestBody User User, @PathVariable String id) {
		userService.updateUser(id, User);
	}

//	@DeleteMapping(value = "/users/{id}")
//	public void deleteUser(@PathVariable String id) {
//		userService.deleteUser(id);
//	}

	@RequestMapping(value = "/Customer", method = RequestMethod.GET)
	public String Customer() {
		return "Customer";

	}

	@RequestMapping("/logout")
	public String logoutCustomer(HttpServletRequest request) {

		HttpSession session = request.getSession();
		session.invalidate();
		return "homepage";

	}

	@PostMapping("/register")
	public String createUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult,
			@RequestParam String address, @RequestParam String town, @RequestParam String county,
			@RequestParam String number, @RequestParam String choice, @RequestParam String fname,
			@RequestParam String lname, @RequestParam int date, @RequestParam int year) {
		// change to their email
		User userExists = userService.getUser(user.getId());
		int type = Integer.parseInt(choice);
		Card card = new Card(fname, lname, address, town, county, number, type, date, year);

		Card validCard = cardValidator.initComponents(card);
		if (validCard != null) {
			cardService.addCard(validCard);
			user.setCard(validCard);
			userService.createCustomer(user);
		} else {
			System.out.println("ERROR-TRY AGAIN");
			return "register";
		}

		System.out.println("ADDRESS " + address);
//			bindingResult.rejectValue("username", "error.user");
//		}
//		if (bindingResult.hasErrors()) {
//			//return to error page
////			model.setViewName("user/register");
////			String errorMessage = "";
////			model.addObject("errorMessage", errorMessage);
//		} else {

//			String successMessage = "";
//			model.addObject("successMessage", successMessage);
//			model.addObject("user", new User());
//			model.setViewName("user/register");
		return "homepage";
//		}
//  return null;

	}

	@RequestMapping("/registerPage")
	public String showRegister() {
		return "register";
	}

}
