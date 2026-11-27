package com.sukanets.shop.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Cart {
    private final List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        products.add(product);
    }

    // видаляє один екземпляр товару з потрібним id
    public boolean removeProduct(int productId) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == productId) {
                products.remove(i);
                return true;
            }
        }
        return false;
    }

    public double getTotalPrice() {
        double sum = 0;
        for (Product p : products) {
            sum += p.getPrice();
        }
        return sum;
    }

    public boolean isEmpty() {
        return products.isEmpty();
    }

    public void clear() {
        products.clear();
    }

    @Override
    public String toString() {
        if (products.isEmpty()) {
            return "Кошик порожній";
        }
        StringBuilder sb = new StringBuilder("У кошику " + products.size() + " шт.:\n");
        for (Product p : products) {
            sb.append("  - ").append(p.getName()).append(" (").append(p.getPrice()).append(" грн)\n");
        }
        sb.append(String.format("Разом до сплати: %.2f грн", getTotalPrice()));
        return sb.toString();
    }
}
