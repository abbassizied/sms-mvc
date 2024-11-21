package io.github.abbassizied.sms.services;

import java.util.List;

import io.github.abbassizied.sms.entities.Order;

public interface OrderService {

    List<Order> listOrders();

    Order getOrderById(long id);

    Order saveOrder(Order order);

    void deleteOrderById(long id);
}
