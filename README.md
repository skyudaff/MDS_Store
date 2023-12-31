# Комментарий к задаче Магазин

## Краткое описание программы
Программа реализует упрощенный функционал покупки товаров, приближенный к минимальным требованиям задания, с целью демонстрации применения принципов SOLID и чистого кода.
С помощью консольного интерфейса, пользователь имеет возможность:
* Просмотреть список всех доступных товаров
* Отфильтровать товар по названию, цене и производителю
* Работать с потребительской корзиной
* Оформлять заказ с указанием адреса доставки
* Получить рекомендацию по рейтинговому товару

## Правило Magic
Примером избегания "магического числа" является использование константы `MAX_RATING` в классе `RecommendationSystem`:
```java
class RecommendationSystem {
    private static final int MAX_RATING = 5;

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
```

## Правило DRY
Примером избегания дублирования могут служить методы `showMenu()`, `showProducts()` в классе `Store` и многие другие.

## SOLID

### _Single-Responsibility principle_

Классы программы логически разделены, например:
* Класс `Product` представляет товар с его характеристиками
* Класс `ProductRepository` отвечает за хранение и управление товарами
* Класс `Order` представлет и отслеживает заказ
* Класс `OrderManager` управлеят заказом (создание, хранение)

### _Open-Closed principle_

Класс `OrderManager` также соответствует принципу открытости/закрытости, т.к. может быть расширен без изменений существующего кода (например, можно добавить функцию обработки платежей).

### _Liskov substitution principle_

В программе не реализовано наследование, поэтому данный принцип не рассматривается.

### _Interface segregation principle_

* Интерфейс `ICart` содержит методы, связанные с управлением корзиной
* Интерфейс `IProduct` содержит методы, относящиеся к представлению информации о продукте
* Интерфейс `IProductRepository` содержит методы, связанные с доступом и фильтрацией продуктов
* Интерфейс `IStore` содержит метод, который запускает процесс покупок

Представленные интерфейсы соответствуют принципу сегрегации, т.к. имеют узкую специализацию и содержат только те методы, которые связаны с конкретным функционалом.

### _Dependency inversion principle_
Класс `Store` отражает данный принцип, т.к. он зависит от абстракций, а не от конкретных реализаций, что придает гибкость системе.
Его конструктор принимает интерфейсы, что позволяет передавать разные реализации зависимостей в класс без изменения кода.
К примеру, создавать разные способы хранения товаров, системы рекомендаций и т.п.