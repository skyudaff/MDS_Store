package ru.netology;

import java.util.HashMap;
import java.util.Map;


public class Cart implements ICart {
    private Map<Product, Integer> cartItems;

    public Cart() {
        cartItems = new HashMap<>();
    }

    public void addToCart(Product product, int quantity) {
        cartItems.put(product, cartItems.getOrDefault(product, 0) + quantity);
    }

    public void showCart() {
        if (cartItems.isEmpty()) {
            System.out.println("\nКорзина пуста\n");
        } else {
            System.out.println("\nСодержимое корзины:");
            double totalAmount = 0.0;

            for (Map.Entry<Product, Integer> entry : cartItems.entrySet()) {
                Product product = entry.getKey();
                int quantity = entry.getValue();
                System.out.println(product.getName() + " (количество: " + quantity + ")");
                product.showInfo();
                double productTotal = product.getPrice() * quantity;
                totalAmount += productTotal;
            }
            System.out.println("Итого к оплате: " + totalAmount + " руб\n");
        }
    }

    public boolean removeFromCart(Product product, int quantity) {
        if (cartItems.containsKey(product)) {
            int existingQuantity = cartItems.get(product);
            if (quantity >= existingQuantity) {
                cartItems.remove(product);
            } else {
                cartItems.put(product, existingQuantity - quantity);
            }
            return true;
        }
        return false;
    }

    public boolean containsProduct(Product product) {
        return cartItems.containsKey(product);
    }

    public int getQuantity(Product product) {
        return cartItems.getOrDefault(product, 0);
    }

    @Override
    public boolean isEmpty() {
        return cartItems.isEmpty();
    }

    public void clearCart() {
        cartItems.clear();
    }
}