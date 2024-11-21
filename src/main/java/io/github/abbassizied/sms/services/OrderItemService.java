package io.github.abbassizied.sms.services;

import java.util.List;

import io.github.abbassizied.sms.entities.OrderItem;

public interface OrderItemService {

    List<OrderItem> listOrderItems();

    OrderItem getOrderItemById(long id);

    OrderItem saveOrderItem(OrderItem orderItem);

    void deleteOrderItemById(long id);
}
