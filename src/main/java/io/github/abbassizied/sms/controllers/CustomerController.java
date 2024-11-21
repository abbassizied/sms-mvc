package io.github.abbassizied.sms.controllers;

import io.github.abbassizied.sms.entities.Customer;
import io.github.abbassizied.sms.services.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/customers")
public class CustomerController {

	private final CustomerService customerService;
    
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    } 
    
	// Redirect root URL to the paginated list view
	@GetMapping
	public String index() { 
		return "redirect:customers/list";
	}

    @GetMapping("/list")
    public String listCustomers(Model model) {
        model.addAttribute("customers", customerService.listCustomers());
        return "customer/listCustomers";
    }

    @GetMapping("/add")
    public String addCustomerForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "customer/addCustomer";
    }

    @PostMapping("/add")
    public String addCustomer(@ModelAttribute Customer customer) {
        customerService.saveCustomer(customer);
        return "redirect:/customer/list";
    }

    @GetMapping("/show/{id}")
    public String showCustomer(@PathVariable("id") long id, Model model) {
        model.addAttribute("customer", customerService.getCustomerById(id));
        return "customer/showCustomer";
    }

    @GetMapping("/update/{id}")
    public String updateCustomerForm(@PathVariable("id") long id, Model model) {
        model.addAttribute("customer", customerService.getCustomerById(id));
        return "customer/updateCustomer";
    }

    @PostMapping("/update/{id}")
    public String updateCustomer(@PathVariable("id") long id, @ModelAttribute Customer customer) {
        //customer.setId(id);
        customerService.saveCustomer(customer);
        return "redirect:/customer/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable("id") long id) {
        customerService.deleteCustomerById(id);
        return "redirect:/customer/list";
    }
    
}

