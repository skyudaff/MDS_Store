package ru.netology;

class Product implements IProduct {
    private String name;
    private double price;
    private String manufacturer;
    private int id, rating;

    public Product(String name, double price, String manufacturer, int rating, int id) {
        this.name = name;
        this.price = price;
        this.manufacturer = manufacturer;
        this.id = id;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getManufacturer() {
        return manufacturer;
    }

    @Override
    public double getPrice() {
        return price;
    }

    public int getRating() {
        return rating;
    }

    public void showInfo() {
        System.out.println("Артикул: " + id);
        System.out.println("Производитель: " + manufacturer);
        System.out.println("Продукт: " + name);
        System.out.println("Цена: " + price + " руб");
        System.out.println("Рейтинг: " + rating + " звёзд");
        System.out.println("------------------------");
    }
}
