package ru.netology;

import java.util.ArrayList;
import java.util.List;

class ProductRepository implements IProductRepository {
    private List<Product> allProducts = new ArrayList<>();

    public ProductRepository addProduct(Product product) {
        allProducts.add(product);
        return this;
    }

    public List<Product> getAllProducts() {
        return allProducts;
    }

    public List<Product> filterProducts(FilterType filterType, String filterValue) {
        List<Product> filteredProducts = new ArrayList<>();
        switch (filterType) {
            case KEYWORD:
                for (Product product : allProducts) {
                    if (product.getName().toLowerCase().contains(filterValue.toLowerCase())) {
                        filteredProducts.add(product);
                    }
                }
                break;
            case PRICE:
                String[] priceRange = filterValue.split("-");
                if (priceRange.length == 2) {
                    double minPrice = Double.parseDouble(priceRange[0]);
                    double maxPrice = Double.parseDouble(priceRange[1]);
                    for (Product product : allProducts) {
                        if (product.getPrice() >= minPrice && product.getPrice() <= maxPrice) {
                            filteredProducts.add(product);
                        }
                    }
                }
                break;
            case MANUFACTURER:
                for (Product product : allProducts) {
                    if (product.getManufacturer().toLowerCase().contains(filterValue.toLowerCase())) {
                        filteredProducts.add(product);
                    }
                }
                break;
        }
        return filteredProducts;
    }
}

enum FilterType {
    KEYWORD, PRICE, MANUFACTURER
}