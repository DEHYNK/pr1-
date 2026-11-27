package com.sukanets.shop.model;

public enum OrderStatus {
    NEW("Нове"),
    PAID("Оплачене"),
    SENT("Відправлене");

    private final String title;

    OrderStatus(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
