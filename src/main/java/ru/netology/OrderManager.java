package ru.netology;

import java.util.ArrayList;
import java.util.List;

public class OrderManager implements IOrderManager {
    private List<Order> ordersDatabase = new ArrayList<>();

    public Order makeOrder(Cart cart, String deliveryAddress) {
        Order order = new Order(cart, deliveryAddress);
        ordersDatabase.add(order);
        return order;
    }
}
