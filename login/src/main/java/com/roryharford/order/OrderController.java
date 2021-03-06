package com.roryharford.order;

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
import com.roryharford.sorting.SortByCategory;
import com.roryharford.sorting.SortByManufacturer;
import com.roryharford.sorting.SortByName;
import com.roryharford.sorting.SortByPrice;
import com.roryharford.sorting.SortingContext;
import com.roryharford.user.CustomerList;
import com.roryharford.user.Iterator;
import com.roryharford.user.OrderList;
import com.roryharford.user.User;
import com.roryharford.user.UserService;

//will eventually be mapped to Customer
@Controller
public class OrderController {
	// since not an api think of adapting it more

	@Autowired
	private OrderService orderService;

	@Autowired
	private UserService userService;

	@Autowired
	private ItemService itemService;

//	@Autowired
//	private TicketService ticketService;

	@RequestMapping("/viewUsersPurchaseHistory")
	public String userDetails(Model model, @RequestParam("id") String id) {
		int newId = Integer.parseInt(id);
		User user = userService.getUser(newId);
		List<Order> usersOrder = user.getOrders();

		final List<Order> order;
		order = user.getOrders();
		OrderList customOrders = new OrderList(order);

		ArrayList<Order> listAll = new ArrayList<Order>();
		for (Iterator iter = customOrders.getIterator(); iter.hasNext();) {
			Order nextOrder = (Order) iter.next();
//			if(nextOrder.get)
			for (Order o : usersOrder) {
				if (o.getId() == nextOrder.getId()) {
					System.out.println("nextOrder Name" +nextOrder.getAmount());
					listAll.add(nextOrder);

				}
			}
		}
		model.addAttribute("lists", listAll);
		return "usersOrders";

	}
	
	@RequestMapping(value ="/AscendingByName", method = RequestMethod.GET)
	public String ascendByName(Model model,HttpSession session) {
		SortingContext context = new SortingContext();
		context.setSortingMethod(new SortByName());
		List<Item> items = (List<Item>) session.getAttribute("searchList");
		context.ascendingSort(items);
		model.addAttribute("lists", items);
		return "success";
	}
	@RequestMapping(value ="/DecendingByName", method = RequestMethod.GET)
	public String decendByName(Model model,HttpSession session) {
		SortingContext context = new SortingContext();
		context.setSortingMethod(new SortByName());
		List<Item> items = (List<Item>) session.getAttribute("searchList");
		context.descendingSort(items);
		model.addAttribute("lists", items);
		return "success";
	}
	
	@RequestMapping(value ="/AscendingByPrice", method = RequestMethod.GET)
	public String ascendByPrice(Model model,HttpSession session) {
		SortingContext context = new SortingContext();
		context.setSortingMethod(new SortByPrice());
		List<Item> items = (List<Item>) session.getAttribute("searchList");
		context.ascendingSort(items);
		model.addAttribute("lists", items);
		return "success";
	}
	@RequestMapping(value ="/DecendingByPrice", method = RequestMethod.GET)
	public String decendByPrice(Model model,HttpSession session) {
		SortingContext context = new SortingContext();
		context.setSortingMethod(new SortByPrice());
		List<Item> items = (List<Item>) session.getAttribute("searchList");
		context.descendingSort(items);
		model.addAttribute("lists", items);
		return "success";
	}
	
	@RequestMapping(value ="/AscendingByManufacturer", method = RequestMethod.GET)
	public String ascendByManufacturer(Model model,HttpSession session) {
		SortingContext context = new SortingContext();
		context.setSortingMethod(new SortByManufacturer());
		List<Item> items = (List<Item>) session.getAttribute("searchList");
		context.ascendingSort(items);
		model.addAttribute("lists", items);
		return "success";
	}
	@RequestMapping(value ="/DecendingByManufacturer", method = RequestMethod.GET)
	public String decendByManufacturer(Model model,HttpSession session) {
		SortingContext context = new SortingContext();
		context.setSortingMethod(new SortByManufacturer());
		List<Item> items = (List<Item>) session.getAttribute("searchList");
		context.descendingSort(items);
		model.addAttribute("lists", items);
		return "success";
	}
	
	@RequestMapping(value ="/AscendingByCategory", method = RequestMethod.GET)
	public String ascendByCategory(Model model,HttpSession session) {
		SortingContext context = new SortingContext();
		context.setSortingMethod(new SortByCategory());
		List<Item> items = (List<Item>) session.getAttribute("searchList");
		context.ascendingSort(items);
		model.addAttribute("lists", items);
		return "success";
	}
	@RequestMapping(value ="/DecendingByCategory", method = RequestMethod.GET)
	public String decendByCategory(Model model,HttpSession session) {
		SortingContext context = new SortingContext();
		context.setSortingMethod(new SortByCategory());
		List<Item> items = (List<Item>) session.getAttribute("searchList");
		context.descendingSort(items);
		model.addAttribute("lists", items);
		return "success";
	}



}
