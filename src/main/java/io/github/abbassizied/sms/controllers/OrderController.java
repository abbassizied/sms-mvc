package io.github.abbassizied.sms.controllers;

import io.github.abbassizied.sms.dtos.CartItemDTO;
import io.github.abbassizied.sms.dtos.OrderDTO;
import io.github.abbassizied.sms.entities.Customer;
import io.github.abbassizied.sms.entities.Order;
import io.github.abbassizied.sms.entities.OrderItem;
import io.github.abbassizied.sms.entities.Product;
import io.github.abbassizied.sms.services.OrderService;
import io.github.abbassizied.sms.services.ProductService;

import java.security.Principal;
import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;


@Controller
@RequestMapping("/orders")
public class OrderController {

	private final OrderService orderService;
	private final ProductService productService;
	
    public OrderController( OrderService orderService,
                            ProductService productService) {
        this.orderService = orderService;
        this.productService = productService;
    } 
 
	// Redirect root URL to the paginated list view
	@GetMapping
	public String index() { 
		return "redirect:orders/list";
	}    

    @GetMapping("/list")
    public String listOrders(Model model) {  	 
        model.addAttribute("orders", orderService.listOrders());
        return "order/listOrders";
    }


    @GetMapping("/add")
    public String addOrderForm(Model model) {
    	
        List<Product> products = productService.listProducts();  // Fetch products
        System.out.println("Products: " + products);  // Debugging line to check the content  
        
        model.addAttribute("order", new Order());
        model.addAttribute("products", products);
        
        return "order/addOrder";
    }

    @PostMapping("/add")
    public String addOfflineOrder(@ModelAttribute("order") Order order) {
    	System.out.println("********************************************************");
    	System.out.println(order);
    	System.out.println("********************************************************");
    	
    	
    	
    	
    	
    	
    	//orderService.saveOrder(customer);
        return "redirect:/orders/list";
    }
   
    

    @GetMapping("/show/{id}")
    public String showOrder(@PathVariable("id") long id, Model model) {
        model.addAttribute("order", orderService.getOrderById(id));
        return "order/showOrder";
    }
    
    
    @PostMapping("/checkout")
    public ResponseEntity<?> checkout(@RequestBody OrderDTO orderRequest, Principal principal) {

        if (principal == null) {
            // User is not authenticated, so return a 401 Unauthorized status
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You must be logged in to place an order.");
        }    	
    	
    	// In your controller method
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	System.out.println("Current user: " + auth.getName());    	
    	
        System.out.println("Received request at /orders/checkout");
        System.out.println(orderRequest);  // This will help you see if the method is being entered
        System.out.println("Inside checkout method!"); // Log to verify controller method execution
     
		Order order = new Order();
		List<OrderItem> orderItems = new ArrayList<>(); // Ensure this is initialized properly
		
		for(CartItemDTO cartItem : orderRequest.items()) {
			OrderItem orderItem = new OrderItem();
			orderItem.setQuantity(cartItem.quantity());
			 
			Product product = productService.getProductById(cartItem.productId());
			orderItem.setProduct(product);
	        orderItems.add(orderItem); 
		}
		
		order.setOrderItems(orderItems);
		System.out.println("Order created: " + order);
		  
		try { 
			// Example: Save to database  
			
	        // Return a success message
	        return ResponseEntity.ok("Purchase completed successfully!");

		} catch (Exception e) {
			System.out.println("error message: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
		}        
        
    }	



}
