package ru.netology;


class Order implements IOrder {
    private Cart cart;
    private String deliveryAddress;

    public Order(Cart cart, String deliveryAddress) {
        this.cart = cart;
        this.deliveryAddress = deliveryAddress;
    }

    public void trackOrder() {
        System.out.println("Курьер уже в пути...");
        System.out.println("Доставка будет осуществлена по адресу: " + deliveryAddress + "\n");
    }
}
