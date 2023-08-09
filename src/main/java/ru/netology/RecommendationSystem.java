package ru.netology;

import java.util.ArrayList;
import java.util.List;


class RecommendationSystem implements IRecommendationSystem {
    private List<Product> products;
    private static final int MAX_RATING = 5;

    public RecommendationSystem(List<Product> products) {
        this.products = products;
    }

    public List<Product> recommendProducts() {
        List<Product> recommendedProducts = new ArrayList<>();
        for (Product product : products) {
            if (product.getRating() >= MAX_RATING) {
                recommendedProducts.add(product);
            }
        }
        return recommendedProducts;
    }
}
