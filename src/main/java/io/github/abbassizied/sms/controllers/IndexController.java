package io.github.abbassizied.sms.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import io.github.abbassizied.sms.services.ProductService;

import org.springframework.ui.Model;

@Controller
public class IndexController {

	private final ProductService productService;

	// Constructor injection
	public IndexController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping()
	public String index(Model model) {
		model.addAttribute("products", productService.listProducts());
		return "frontoffice/index"; // Returns index.html
	}

	@GetMapping("/product-details/{id}")
	public String productDetails(@PathVariable long id, Model model) {
		model.addAttribute("product", productService.getProductById(id));
		return "frontoffice/productDetails"; // Returns index.html
	}

}
