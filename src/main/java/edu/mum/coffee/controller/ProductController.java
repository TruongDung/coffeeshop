package edu.mum.coffee.controller;

import edu.mum.coffee.domain.Product;
import edu.mum.coffee.domain.ProductType;
import edu.mum.coffee.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@Controller
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping("/product")
	public String productPage(Model model) {
		model.addAttribute("product", new Product());
		model.addAttribute("productTypes", ProductType.values());
		return "product";
	}

	@GetMapping("/product/{productId}")
	public String productDetailPage(@PathVariable("productId") Long productId, Model model) {
		model.addAttribute("product", productService.findById(productId));
		model.addAttribute("productTypes", ProductType.values());
		return "product";
	}

	@GetMapping("/products")
	public String productList(Model model) {
		model.addAttribute("products", productService.findAll());
		return "productList";
	}

	@PostMapping("/product")
	public String createProduct(@ModelAttribute Product product) {
		productService.save(product);
		return "redirect:/products";
	}
}
