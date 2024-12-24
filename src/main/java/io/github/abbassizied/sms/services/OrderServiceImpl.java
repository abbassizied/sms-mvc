package io.github.abbassizied.sms.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.abbassizied.sms.entities.Customer;
import io.github.abbassizied.sms.entities.Order;
import io.github.abbassizied.sms.entities.OrderItem;
import io.github.abbassizied.sms.entities.Product;
import io.github.abbassizied.sms.exceptions.OrderNotFoundException;
import io.github.abbassizied.sms.repositories.OrderItemRepository;
import io.github.abbassizied.sms.repositories.OrderRepository;
import jakarta.transaction.Transactional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private OrderItemRepository orderItemRepository;
    
    @Autowired
    private CustomerService customerService;
    
    @Autowired
    private ProductService productService;

    @Override
    public List<Order> listOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOrderById(long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Invalid order Id:" + id));
    }

    @Transactional
    @Override
    public Order saveOrder(Order order) {
    	
    	// Step 1: Ensure customer is saved
    	Customer customer = customerService.saveCustomer(order.getCustomer());
          	
        // Step 2: Create and save the Order
        // Create a new Order
        Order newOrder = new Order();
        newOrder.setCustomer(customer);  // Associate the customer
        newOrder.setTotalAmount(order.getTotalAmount());  // Set total amount 
        // Save the newOrder to generate the ID
        newOrder = orderRepository.save(newOrder);  // Make sure to save the newOrder first!

        // Step 3: Now, create and associate order items (after the Order is saved)
        for (OrderItem item : order.getOrderItems()) {
            Product product = productService.getProductById(item.getProduct().getId());
            
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(product);
            orderItem.setQuantity(item.getQuantity());
            orderItem.setOrder(newOrder); // Set the order after it has been saved
            
            orderItemRepository.save(orderItem);  // Save the order item
        }        
        
        
        
        return newOrder;
    }

    @Override
    public void deleteOrderById(long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Invalid order Id:" + id));
        orderRepository.delete(order);
    }
}
