package com.sukanets.shop.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Order {
    private static int counter = 0;

    private final int number;
    private final List<Product> products;
    private final double totalPrice;
    private final LocalDateTime createdAt;
    private OrderStatus status;

    public Order(List<Product> products, double totalPrice) {
        this.number = ++counter;
        this.products = new ArrayList<>(products);
        this.totalPrice = totalPrice;
        this.createdAt = LocalDateTime.now();
        this.status = OrderStatus.NEW;
    }

    @Override
    public String toString() {
        String time = createdAt.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
        return "Замовлення #" + number + " (" + time + "), товарів: " + products.size()
                + ", сума: " + String.format("%.2f", totalPrice) + " грн, статус: " + status.getTitle();
    }
}
