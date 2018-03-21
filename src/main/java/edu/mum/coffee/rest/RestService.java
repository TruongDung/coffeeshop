package edu.mum.coffee.rest;

import edu.mum.coffee.domain.Order;
import edu.mum.coffee.domain.Orderline;
import edu.mum.coffee.domain.Product;
import edu.mum.coffee.domain.ShoppingCartItem;
import edu.mum.coffee.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@RestController
public class RestService {
	@Autowired
	private ProductService productService;

	@RequestMapping(value = "/product/{productId}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void deleteProduct(@PathVariable("productId") Long productId) {
		productService.deleteById(productId);
	}

	@RequestMapping(value = "/product-list", method = RequestMethod.GET)
	public List<Product> getAllProduct() {
		return productService.findAll();
	}

	@RequestMapping(value = "/cart", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public void addProductToCart(@RequestBody Long productId, HttpSession session) {
		Object orderObj = session.getAttribute("orderCart");
		if (orderObj == null) {
			orderObj = new Order();
			session.setAttribute("orderCart", orderObj);
		}
		Order order = (Order) orderObj;

		Optional<Orderline> orderLine = order.getOrderLines().stream().filter(orderline
				-> orderline.getProduct().getId() == productId).findFirst();
		if (orderLine.isPresent()) {
			Orderline orderline = orderLine.get();
			orderline.setQuantity(orderline.getQuantity() + 1);
		} else {
			Product product = productService.findById(productId);
			Orderline orderline  = new Orderline();
			orderline.setProduct(product);
			orderline.setQuantity(1);
			order.addOrderLine(orderline);
		}
	}

	@RequestMapping(value = "/shopping-cart", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public void updateItemInShoppingCart(@RequestBody ShoppingCartItem item, HttpSession session) {
		Object orderObj = session.getAttribute("orderCart");
		if (orderObj == null) {
			orderObj = new Order();
			session.setAttribute("orderCart", orderObj);
		}
		Order order = (Order) orderObj;

		Optional<Orderline> orderLine = order.getOrderLines().stream().filter(orderline
				-> orderline.getProduct().getId() == item.getProductId()).findFirst();
		if (orderLine.isPresent()) {
			if (item.getQuantity() <= 0) {
				order.removeOrderLine(orderLine.get());
			} else {
				orderLine.get().setQuantity(item.getQuantity());
			}
		}
	}

	@RequestMapping(value = "/shopping-cart/{productId}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void deleteItemInShoppingCart(@PathVariable("productId") Long productId, HttpSession session) {
		Object orderObj = session.getAttribute("orderCart");
		if (orderObj == null) {
			orderObj = new Order();
			session.setAttribute("orderCart", orderObj);
		}
		Order order = (Order) orderObj;

		Optional<Orderline> orderLine = order.getOrderLines().stream().filter(orderline
				-> orderline.getProduct().getId() == productId).findFirst();
		if (orderLine.isPresent()) {
			order.removeOrderLine(orderLine.get());
		}
	}
}
