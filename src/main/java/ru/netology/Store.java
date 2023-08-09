package ru.netology;

import java.util.List;
import java.util.Scanner;


public class Store implements IStore {
    private IProductRepository productRepository;
    private IRecommendationSystem recommendationSystem;
    private ICart cart;
    private IOrderManager orderManager;
    private Scanner scanner;

    public Store(IProductRepository productRepository, IRecommendationSystem recommendationSystem, ICart cart, IOrderManager orderManager) {
        this.productRepository = productRepository;
        this.recommendationSystem = recommendationSystem;
        this.cart = cart;
        this.orderManager = orderManager;
        this.scanner = new Scanner(System.in);
    }

    public void startShopping() {
        boolean isContinue = true;
        showMenu();

        while (isContinue) {
            String action = scanner.nextLine();

            switch (action) {
                case "1":
                    showProducts();
                    break;
                case "2":
                    showFinderMenu();
                    break;
                case "3":
                    addProductToCart();
                    break;
                case "4":
                    showProductCart();
                    break;
                case "5":
                    removeProductFromCart();
                    break;
                case "6":
                    makeOrder();
                    break;
                case "7":
                    isContinue = false;
                    System.out.println("\nДО НОВЫХ ВСТРЕЧ!");
                    break;
                default:
                    System.out.println("\nВведите корректный номер меню\n");
            }
            if (isContinue) {
                showMenu();
            }
        }
    }

    private void showMenu() {
        System.out.print("Выберите операцию:" +
                "\n" +
                "1. Показать список всех доступных товаров\n" +
                "2. Отфильтровать товары\n" +
                "3. Добавить товар в корзину\n" +
                "4. Показать корзину\n" +
                "5. Удалить товар из корзины\n" +
                "6. Оформить заказ\n" +
                "7. Покинуть магазин\n" +
                "Ваш выбор: ");
    }

    private void showProducts() {
        List<Product> allProducts = productRepository.getAllProducts();
        System.out.println("\nСписок доступных товаров:");
        for (Product product : allProducts) {
            product.showInfo();
        }
    }

    private void showFinderMenu() {
        System.out.print("\nВыберите тип поиска:" +
                "\n" +
                "1. Отфильтровать по названию\n" +
                "2. Отфильтровать по ценам\n" +
                "3. Отфильтровать по производителям\n" +
                "4. Вернуться в предыдущее меню\n" +
                "Ваш выбор: ");

        String action = scanner.nextLine();
        switch (action) {
            case "1":
                System.out.print("Введите слово для поиска: ");
                String keyWord = scanner.nextLine();
                addFilter(FilterType.KEYWORD, keyWord);
                break;
            case "2":
                System.out.print("Введите диапазон цен для поиска (пример: 0-100): ");
                String priceRange = scanner.nextLine();
                try {
                    addFilter(FilterType.PRICE, priceRange);
                } catch (Exception e) {
                    System.out.println("\nНекорректный ввод. Попробуйте еще раз\n");
                    showFinderMenu();
                }
                break;
            case "3":
                System.out.print("Введите производителя для поиска: ");
                String manufacturer = scanner.nextLine();
                addFilter(FilterType.MANUFACTURER, manufacturer);
                break;
            case "4":
                break;
            default:
                System.out.println("\nВведите корректный номер меню");
                showFinderMenu();
        }
    }

    private void addFilter(FilterType filterType, String filterValue) {
        List<Product> filteredProducts = productRepository.filterProducts(filterType, filterValue);
        showFilteredProducts(filteredProducts);
    }

    private void showFilteredProducts(List<Product> filteredProducts) {
        if (filteredProducts.isEmpty()) {
            System.out.println("\nНет доступных товаров");
            showFinderMenu();
        } else {
            System.out.println("\nCписок отфильтрованных товаров:");
            for (Product product : filteredProducts) {
                product.showInfo();
            }
        }
    }

    private void addProductToCart() {
        System.out.print("Введите артикул товара, чтобы добавить в корзину: ");
        String productNumber = scanner.nextLine();

        try {
            int productId = Integer.parseInt(productNumber);
            Product productToAdd = getProductById(productId);

            if (productToAdd != null) {
                System.out.print("Введите количество товара: ");
                int quantity = Integer.parseInt(scanner.nextLine());

                if (quantity > 0) {
                    cart.addToCart(productToAdd, quantity);
                    System.out.println("\nДобавлено в корзину: " + productToAdd.getName() + " \"" +
                            productToAdd.getManufacturer() + "\" - " + quantity + " шт.\n");
                } else {
                    System.out.println("\nМинимальное количество для добавления - 1 шт.\n");
                }
            } else {
                System.out.println("\nТовар с артикулом \"" + productId + "\" не найден.\n");
            }
        } catch (NumberFormatException e) {
            System.out.println("\nВведите корректный артикул товара\n");
        }
    }

    private void showProductCart() {
        cart.showCart();
    }

    private void removeProductFromCart() {
        System.out.print("Введите артикул товара, чтобы удалить из корзины: ");
        String productNumber = scanner.nextLine();

        try {
            int productId = Integer.parseInt(productNumber);
            System.out.print("Введите количество для удаления: ");
            int quantity = Integer.parseInt(scanner.nextLine());
            Product productToRemove = getProductById(productId);

            if (productToRemove != null) {
                if (cart.containsProduct(productToRemove)) {
                    int currentQuantity = cart.getQuantity(productToRemove);

                    if (quantity <= currentQuantity && quantity > 0) {
                        cart.removeFromCart(productToRemove, quantity);
                        System.out.println("\nИз корзины удалено " + productToRemove.getName() + " \"" +
                                productToRemove.getManufacturer() + "\" - " + quantity + " шт.\n");
                    } else {
                        System.out.println("\nУказанно неверное количество товара\n");
                    }
                } else {
                    System.out.println("\nТовар с артикулом \"" + productNumber + "\" не найден в корзине\n");
                }
            } else {
                System.out.println("\nТовар с артикулом \"" + productNumber + "\" не найден\n");
            }
        } catch (Exception e) {
            System.out.println("\nВведите корректный артикул товара и количество\n");
        }
    }


    private void makeOrder() {
        System.out.println("\nВаш заказ:");
        cart.showCart();

        if (cart.isEmpty()) {
            System.out.println("Добавьте товары в корзину перед оформлением заказа\n");
            return;
        }

        System.out.print("Введите адрес доставки (0 для отмены): ");
        String deliveryAddress = scanner.nextLine();

        if (!deliveryAddress.equals("0")) {
            Order order = orderManager.makeOrder((Cart) cart, deliveryAddress);
            System.out.println("\nВаш заказ оформлен.\n");
            order.trackOrder();
            cart.clearCart();
        } else System.out.println("\nОформление заказа отменено\n");
    }

    public void showRecommendation() {
        List<Product> recommendedProducts = recommendationSystem.recommendProducts();
        System.out.println("Рекомендуем к покупке:");
        for (Product product : recommendedProducts) {
            product.showInfo();
        }
    }

    private Product getProductById(int productId) {
        List<Product> allProducts = productRepository.getAllProducts();
        for (Product product : allProducts) {
            if (product.getId() == productId) {
                return product;
            }
        }
        return null;
    }

    public void close() {
        scanner.close();
    }
}

