package io.github.abbassizied.sms.controllers;

import io.github.abbassizied.sms.dtos.CartItemDTO;
import io.github.abbassizied.sms.dtos.OrderDTO;
import io.github.abbassizied.sms.entities.Customer;
import io.github.abbassizied.sms.entities.Order;
import io.github.abbassizied.sms.entities.OrderItem;
import io.github.abbassizied.sms.entities.Product;
import io.github.abbassizied.sms.entities.User;
import io.github.abbassizied.sms.services.CustomerService;
import io.github.abbassizied.sms.services.OrderService;
import io.github.abbassizied.sms.services.ProductService;
import io.github.abbassizied.sms.services.UserService;

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
	private final UserService userService;
	private final CustomerService customerService;
	
    public OrderController( OrderService orderService,
                            ProductService productService,
                            UserService userService,
                            CustomerService customerService) {
        this.orderService = orderService;
        this.productService = productService;
        this.userService = userService;
        this.customerService = customerService;
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
        
        List<Customer> customers = customerService.listCustomers();
        
        model.addAttribute("order", new Order());
        model.addAttribute("products", products);
        model.addAttribute("customers", customers);
        
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
		List<OrderItem> newOrderItems = new ArrayList<>(); // Ensure this is initialized properly
		
		for(CartItemDTO cartItem : orderRequest.items()) {
			OrderItem orderItem = new OrderItem();
			orderItem.setQuantity(cartItem.quantity());
			 
			Product product = productService.getProductById(cartItem.productId());
			orderItem.setProduct(product);			
			newOrderItems.add(orderItem); 
		}
		
		order.setOrderItems(newOrderItems);
		
		String email = principal.getName();
		User user= userService.findByEmail(email);
		Customer customer = new Customer();
		customer.setEmail(user.getEmail());
		customer.setFirstName(user.getFirstName());
		customer.setLastName(user.getLastName()); 
		
		System.out.println(customer);
 
		order.setCustomer(customer);
		System.out.println("Order created: " + order);
		  
		try { 
			// Example: Save to database  
			orderService.saveOrder(order);
	        // Return a success message
	        return ResponseEntity.ok("Purchase completed successfully!");

		} catch (Exception e) {
			System.out.println("error message: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
		}        
        
    }	



}
