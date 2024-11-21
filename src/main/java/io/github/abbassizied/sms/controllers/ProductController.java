package io.github.abbassizied.sms.controllers;

import io.github.abbassizied.sms.entities.Product;
import io.github.abbassizied.sms.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductController {
	
    @Autowired
    private ProductService productService;

	// Redirect root URL to the paginated list view
    @GetMapping
    public String index() {  
        return "redirect:products/list";
    }  

    @GetMapping("/list")
    public String listProducts(Model model) {
        model.addAttribute("products", productService.listProducts());
        return "product/listProducts";
    }

    @GetMapping("/add")
    public String addProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "product/addProduct";
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute Product product) {
        productService.saveProduct(product);
        return "redirect:/product/list";
    }

    @GetMapping("/show/{id}")
    public String showProduct(@PathVariable("id") long id, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        return "product/showProduct";
    }

    @GetMapping("/update/{id}")
    public String updateProductForm(@PathVariable("id") long id, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        return "product/updateProduct";
    }

    @PostMapping("/update/{id}")
    public String updateProduct(@PathVariable("id") long id, @ModelAttribute Product product) {
       // product.setId(id);
        productService.saveProduct(product);
        return "redirect:/product/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") long id) {
        productService.deleteProductById(id);
        return "redirect:/product/list";
    }
    
}
