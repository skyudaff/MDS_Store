package ru.netology;

public interface IOrderManager {
    Order makeOrder(Cart cart, String deliveryAddress);
}
