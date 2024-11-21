package io.github.abbassizied.sms.controllers;

import io.github.abbassizied.sms.entities.Order;
import io.github.abbassizied.sms.services.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/{lang}/orders")
public class OrderController {

	private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
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
        model.addAttribute("order", new Order());
        return "order/addOrder";
    }

    @PostMapping("/add")
    public String addOrder(@ModelAttribute Order order) {
        orderService.saveOrder(order);
        return "redirect:/order/list";
    }

    @GetMapping("/show/{id}")
    public String showOrder(@PathVariable("id") long id, Model model) {
        model.addAttribute("order", orderService.getOrderById(id));
        return "order/showOrder";
    }

    @GetMapping("/update/{id}")
    public String updateOrderForm(@PathVariable("id") long id, Model model) {
        model.addAttribute("order", orderService.getOrderById(id));
        return "order/updateOrder";
    }

    @PostMapping("/update/{id}")
    public String updateOrder(@PathVariable("id") long id, @ModelAttribute Order order) {
        //order.setId(id);
        orderService.saveOrder(order);
        return "redirect:/order/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteOrder(@PathVariable("id") long id) {
        orderService.deleteOrderById(id);
        return "redirect:/order/list";
    }
}
