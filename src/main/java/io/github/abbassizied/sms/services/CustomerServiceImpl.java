package io.github.abbassizied.sms.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.abbassizied.sms.entities.Customer;
import io.github.abbassizied.sms.exceptions.CustomerNotFoundException;
import io.github.abbassizied.sms.repositories.CustomerRepository; 

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> listCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Invalid customer Id:" + id));
    }

    @Override
    public Customer getCustomerByEmail(String email) {
        return customerRepository.findByEmail(email)
                .orElseThrow(() -> new CustomerNotFoundException("Invalid customer Email:" + email));
    }   
    
    
	/*
	 * The @Transactional annotation ensures that the database operation is
	 * performed within a transaction and is atomic. If the email already exists,
	 * the update operation will be performed; otherwise, the insert will happen.
	 * 
	 * If there is a chance that multiple requests are trying to insert a customer
	 * with the same email simultaneously, you might face a race condition. To
	 * handle this, you can add transactional behavior or use database-level locking
	 * mechanisms.
	 * 
	 */

    @Override
    public Customer saveCustomer(Customer customer) {
        Optional<Customer> existingCustomer = customerRepository.findByEmail(customer.getEmail());

         System.out.println("-----------------" + existingCustomer.get());
        if (existingCustomer.isPresent()) { 
            return existingCustomer.get();
        } else {
            // Handle the insert logic
        	Customer newCustomer = new Customer();
        	newCustomer.setEmail(customer.getEmail());
        	newCustomer.setFirstName(customer.getFirstName()); 
        	newCustomer.setLastName(customer.getLastName()); // Set other necessary fields
            return customerRepository.save(newCustomer);  // Save the new customer
        }
    }



    @Override
    public void deleteCustomerById(long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Invalid customer Id:" + id));
        customerRepository.delete(customer);
    }
}
