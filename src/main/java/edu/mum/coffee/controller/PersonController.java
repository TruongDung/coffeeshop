package edu.mum.coffee.controller;

import edu.mum.coffee.adapters.UserAdapter;
import edu.mum.coffee.domain.Order;
import edu.mum.coffee.domain.Person;
import edu.mum.coffee.domain.User;
import edu.mum.coffee.service.OrderService;
import edu.mum.coffee.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.websocket.server.PathParam;
import java.util.List;

@Controller
public class PersonController {

	@Autowired
	private PersonService personService;

	@Autowired
	private OrderService orderService;

	@GetMapping("/person")
	public String createPersonPage(Model model) {
		model.addAttribute("person", new Person());
		return "person";
	}

	@GetMapping("/my-account")
	public String myProfile(Model model, Authentication authentication) {
		UserAdapter userAdapter = (UserAdapter) authentication.getPrincipal();
		List<Person> persons = personService.findByEmail(userAdapter.getUser().getEmail());
		model.addAttribute("person", persons.get(0));

		List<Order> orders = orderService.findByPerson(persons.get(0));
		model.addAttribute("orders", orders);
		return "myAccount";
	}

	@PostMapping("/my-account")
	public String updateAccount(@ModelAttribute("person") Person person) {
		personService.savePerson(person);
		return "redirect:/my-account";
	}

	@GetMapping("/person/{personId}")
	public String personDetailPage(@PathVariable("personId") Long personId, Model model) {
		model.addAttribute("person", personService.findById(personId));
		return "person";
	}

	@GetMapping("/persons")
	public String personList(Model model) {
		model.addAttribute("persons", personService.findAll());
		return "personList";
	}

	@PostMapping("/person")
	public String createPerson(@ModelAttribute("person") Person person) {
		personService.savePerson(person);
		return "redirect:/persons";
	}
}
