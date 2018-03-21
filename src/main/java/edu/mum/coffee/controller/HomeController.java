package edu.mum.coffee.controller;

import edu.mum.coffee.domain.Order;
import edu.mum.coffee.domain.Orderline;
import edu.mum.coffee.domain.Person;
import edu.mum.coffee.service.PersonService;
import edu.mum.coffee.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {
	@Autowired
	private ProductService productService;

	@Autowired
	private PersonService personService;
	
	@GetMapping({"/"})
	public String homePage(Model model) {
		model.addAttribute("products", productService.findAll());
		return "index";
	}

	@GetMapping({"/login"})
	public String securePage() {
		return "login";
	}

	@GetMapping("/signup")
	public String signupPage(Model model) {
		model.addAttribute("person", new Person());
		return "signup";
	}

	@PostMapping("/signup")
	public String signup(@ModelAttribute Person person) {
		personService.signup(person);
		return "redirect:/login";
	}

	@GetMapping("/shopping-cart")
	public String shoppingCart(Model model, HttpSession session) {
		Object orderCartObj = session.getAttribute("orderCart");
		double total = 0;
		if (orderCartObj != null) {
			 Order order = (Order) orderCartObj;
			model.addAttribute("order", order);
			for (Orderline orderline : order.getOrderLines()) {
				total += orderline.getSubTotal();
			}
		}
		model.addAttribute("total", total);

		return "shoppingCart";
	}
}
