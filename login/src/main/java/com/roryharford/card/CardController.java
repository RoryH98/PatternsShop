package com.roryharford.card;

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
public class CardController {
	
	@Autowired
	private CardService cardService;
	
	

}
