package com.yakukhno.model;

public enum ProductType {
    TEA(25), COFFEE(35), JUICE(45);

    private int price;

    ProductType(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
