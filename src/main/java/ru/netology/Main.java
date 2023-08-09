package ru.netology;


public class Main {
    public static void main(String[] args) {
        ProductRepository productRepository = new ProductRepository()
                .addProduct(new Product("Молоко", 99.99, "Сарафаново", 5, 100))
                .addProduct(new Product("Масло", 199.99, "Сарафаново", 4, 101))
                .addProduct(new Product("Молоко", 89.50, "Простоквашино", 3, 102))
                .addProduct(new Product("Кефир", 100.00, "Простоквашино", 2, 103))
                .addProduct(new Product("Сметана", 69.90, "Домик в деревне", 1, 104));

        RecommendationSystem recommendationSystem = new RecommendationSystem(productRepository.getAllProducts());
        Cart cart = new Cart();
        OrderManager orderManager = new OrderManager();

        Store store = new Store(productRepository, recommendationSystem, cart, orderManager);
        System.out.println("ДОБРО ПОЖАЛОВАТЬ!");
        store.showRecommendation();
        store.startShopping();
        store.close();
    }
}
