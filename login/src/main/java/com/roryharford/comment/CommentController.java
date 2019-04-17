package com.roryharford.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.roryharford.item.Item;
import com.roryharford.item.ItemService;
import com.roryharford.order.Order;
import com.roryharford.order.OrderService;
import com.roryharford.user.User;
import com.roryharford.user.UserService;

@Controller
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private UserService userService;



	
	@RequestMapping(value = "/viewComments", method = RequestMethod.POST)
	public String viewComments(Model model,@RequestParam("id") String id) {
		int newId = Integer.parseInt(id);
		model.addAttribute("list", itemService.getItem(newId));
		model.addAttribute("comments",itemService.getCommentForItem(newId));
		return "searchResults";
		
	}
	
	

	@RequestMapping(value = "/submitReview", method = RequestMethod.POST)
	public String postComment(Model model,@RequestParam("rating") String rating,@RequestParam("comment") String comment,@RequestParam("id") String id) {
		int newId = Integer.parseInt(id);
		int newRating = Integer.parseInt(rating);
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.getUserByEmail(auth.getName());
		
		Item item = itemService.getItem(newId);
        System.out.println("The comment is: "+rating);
        Comment user_coment = new Comment(user.getName(),comment,newRating);
		item.getComments().add(user_coment);
		
		commentService.addComment(user_coment);
		itemService.updateItem(newId, item);
		
		model.addAttribute("list", itemService.getItem(newId));
		model.addAttribute("comments",itemService.getCommentForItem(newId));
		return "searchResults";
		
	}
	
}
