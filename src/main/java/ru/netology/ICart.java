package ru.netology;

interface ICart {
    void addToCart(Product product, int quantity);

    void showCart();

    boolean removeFromCart(Product product, int quantity);

    boolean isEmpty();

    boolean containsProduct(Product productToRemove);

    int getQuantity(Product productToRemove);

    void clearCart();
}
