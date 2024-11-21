package io.github.abbassizied.sms.services;

import java.util.List;

import io.github.abbassizied.sms.entities.Customer;

public interface CustomerService {

    List<Customer> listCustomers();

    Customer getCustomerById(long id);

    Customer saveCustomer(Customer customer);

    void deleteCustomerById(long id);
}
