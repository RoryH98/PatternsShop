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
//import com.roryharford.event.Event;
//import com.roryharford.ticket.Ticket;
//import com.roryharford.ticket.TicketController;
//import com.roryharford.ticket.TicketService;
import com.roryharford.item.Item;
import com.roryharford.item.ItemService;


//will eventually be mapped to Customer
@Controller
public class UserController {
	//since not an api think of adapting it more
	private ArrayList<Item> list = new ArrayList<Item>();

	@Autowired
	private UserService userService;
	
	@Autowired
	private ItemService itemService;
	

//	@Autowired
//	private TicketService ticketService;

	@RequestMapping(value = "/")
	public String index() {

		return "homepage";
	}

	@RequestMapping(value = "/homepage")
	public String redirect(Model model) {
//		Item item = new Item("Toaster",20,1"https://target.scene7.com/is/image/Target/GUEST_087da4b9-d9a0-47ad-bed0-e39af7bcf89b?wid=488&hei=488&fmt=pjpeg");
//		list.add(item);
		System.out.println("AMOUNT"+itemService.getAllItems().size());
		model.addAttribute("lists", itemService.getAllItems());
		return "success";
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

	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public String login() {

		return "homepage";
	}


	@RequestMapping("/logout")
	public String logoutCustomer(HttpServletRequest request) {
		

		HttpSession session = request.getSession();
		session.invalidate();
		return "homepage";

	}

	@PostMapping("/register")
	public String createUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
		// change to their email
		User userExists = userService.getUser(user.getId());

//		if (userExists != null) {
//			bindingResult.rejectValue("username", "error.user");
//		}
//		if (bindingResult.hasErrors()) {
//			//return to error page
////			model.setViewName("user/register");
////			String errorMessage = "";
////			model.addObject("errorMessage", errorMessage);
//		} else {
		userService.createCustomer(user);

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
