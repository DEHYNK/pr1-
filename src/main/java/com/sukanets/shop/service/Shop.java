package com.sukanets.shop.service;

import com.sukanets.shop.model.Cart;
import com.sukanets.shop.model.Category;
import com.sukanets.shop.model.Order;
import com.sukanets.shop.model.Product;

import java.util.ArrayList;
import java.util.List;

public class Shop {
    private final List<Product> catalog = new ArrayList<>();
    private final List<Order> history = new ArrayList<>();
    private final Cart cart = new Cart();

    public Shop() {
        Category bikes = new Category(1, "Велоспорт");
        Category run = new Category(2, "Біг");
        Category fitness = new Category(3, "Фітнес");

        catalog.add(new Product(1, "Велосипед Cross 27.5", 15400.00, "алюмінієва рама, 21 швидкість", bikes));
        catalog.add(new Product(2, "Шолом Kask Rapido", 2350.00, "розмір M, вентиляція", bikes));
        catalog.add(new Product(3, "Кросівки Asics Gel", 4199.00, "для бігу по асфальту", run));
        catalog.add(new Product(4, "Пульсометр Polar H9", 3100.00, "нагрудний датчик", run));
        catalog.add(new Product(5, "Гантелі 2x5 кг", 890.00, "вінілове покриття", fitness));
        catalog.add(new Product(6, "Килимок для йоги", 650.00, "товщина 6 мм", fitness));
    }

    public List<Product> getCatalog() {
        return catalog;
    }

    public Cart getCart() {
        return cart;
    }

    public List<Order> getHistory() {
        return history;
    }

    public Product findById(int id) {
        for (Product p : catalog) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    // пошук без урахування регістру і по назві, і по категорії
    public List<Product> search(String text) {
        String q = text.toLowerCase();
        List<Product> result = new ArrayList<>();
        for (Product p : catalog) {
            if (p.getName().toLowerCase().contains(q)
                    || p.getCategory().getName().toLowerCase().contains(q)) {
                result.add(p);
            }
        }
        return result;
    }

    public Order checkout() {
        if (cart.isEmpty()) {
            return null;
        }
        Order order = new Order(cart.getProducts(), cart.getTotalPrice());
        history.add(order);
        cart.clear();
        return order;
    }
}
