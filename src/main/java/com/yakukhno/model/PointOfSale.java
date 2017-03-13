package com.yakukhno.model;

import java.util.*;

public class PointOfSale {
    private Set<Integer> allowedCoins = new HashSet<>();
    private List<Integer> clientCoins = new ArrayList<>();

    public PointOfSale() {
        initAllowedCoins();
    }

    public boolean addCoin(int coin) {
        boolean isAllowed = false;
        if (allowedCoins.contains(coin)) {
            clientCoins.add(coin);
            isAllowed = true;
        }
        return isAllowed;
    }

    public Sale getProduct(ProductType type) {
        Sale sale;
        if (getClientCoins() < type.getPrice()) {
            sale = new Sale();
            sale.setStatus(Status.NOT_ENOUGH_MONEY);
        } else {
            sale = new Sale.Builder()
                    .setProduct(new Product(type))
                    .setChange(getClientCoins() - type.getPrice())
                    .setStatus(Status.OK)
                    .build();
        }
        return sale;
    }

    public Sale refund() {
        return new Sale.Builder()
                .setChange(getClientCoins())
                .setStatus(Status.OK)
                .build();
    }

    public int getClientCoins() {
        return clientCoins.stream()
                .reduce((a, b) -> (a + b))
                .orElse(0);
    }

    private void initAllowedCoins() {
        Collections.addAll(allowedCoins, 1, 5, 10, 25, 50);
    }
}
