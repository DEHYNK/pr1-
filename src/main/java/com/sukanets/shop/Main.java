package com.sukanets.shop;

import com.sukanets.shop.model.Order;
import com.sukanets.shop.model.Product;
import com.sukanets.shop.service.Shop;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Shop shop = new Shop();
        Scanner in = new Scanner(System.in);
        System.out.println("=== Спортивний магазин SportLine ===");

        while (true) {
            printMenu();
            String choice = in.nextLine().trim();
            switch (choice) {
                case "1" -> shop.getCatalog().forEach(System.out::println);
                case "2" -> {
                    System.out.print("Номер товару: ");
                    Product p = shop.findById(readInt(in));
                    if (p == null) {
                        System.out.println("Такого товару немає");
                    } else {
                        shop.getCart().addProduct(p);
                        System.out.println("У кошик додано: " + p.getName());
                    }
                }
                case "3" -> System.out.println(shop.getCart());
                case "4" -> {
                    Order order = shop.checkout();
                    System.out.println(order == null ? "Кошик порожній, нічого оформлювати" : "Оформлено. " + order);
                }
                case "5" -> {
                    System.out.print("Номер товару для видалення: ");
                    boolean ok = shop.getCart().removeProduct(readInt(in));
                    System.out.println(ok ? "Видалено з кошика" : "У кошику такого товару немає");
                }
                case "6" -> {
                    if (shop.getHistory().isEmpty()) {
                        System.out.println("Замовлень ще не було");
                    }
                    shop.getHistory().forEach(System.out::println);
                }
                case "7" -> {
                    System.out.print("Що шукаємо (назва або категорія): ");
                    List<Product> found = shop.search(in.nextLine());
                    if (found.isEmpty()) {
                        System.out.println("Нічого не знайдено");
                    }
                    found.forEach(System.out::println);
                }
                case "0" -> {
                    System.out.println("До побачення!");
                    return;
                }
                default -> System.out.println("Невідома команда");
            }
        }
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("[1] каталог  [2] в кошик  [3] кошик  [4] оформити  [5] прибрати з кошика  [6] замовлення  [7] пошук  [0] вихід");
        System.out.print("> ");
    }

    private static int readInt(Scanner in) {
        try {
            return Integer.parseInt(in.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
