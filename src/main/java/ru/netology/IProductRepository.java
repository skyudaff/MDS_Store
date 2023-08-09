package ru.netology;

import java.util.List;

interface IProductRepository {
    List<Product> getAllProducts();

    List<Product> filterProducts(FilterType filterType, String filterValue);

    ProductRepository addProduct(Product product);
}
