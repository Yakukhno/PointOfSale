package com.yakukhno.model;

import java.util.*;

public class PointOfSale {
    private List<Product> productContainer = new ArrayList<>();
    private Set<Integer> allowedCoins = new HashSet<>();
    private List<Integer> clientCoins = new ArrayList<>();

    public PointOfSale() {
        initAllowedCoins();
    }

    public Sale insertCoin(int coin) {
        Sale sale = new Sale();
        sale.setStatus(Status.NOT_ALLOWED_COIN);
        if (allowedCoins.contains(coin)) {
            clientCoins.add(coin);
            sale.setStatus(Status.OK);
        }
        return sale;
    }

    public Sale getProduct(int productNumber) {
        Product product = productContainer.get(productNumber);
        int diff = getClientMoney() - product.getPrice();
        Sale sale = new Sale();
        if (diff < 0) {
            sale.setStatus(Status.NOT_ENOUGH_MONEY);
        } else {
            sale.setProduct(product);
            sale.setChange(diff);
            sale.setStatus(Status.OK);
        }
        return sale;
    }

    public Sale refund() {
        return new Sale.Builder()
                .setChange(getClientMoney())
                .setStatus(Status.OK)
                .build();
    }

    public void addProductToContainer(Product product) {
        productContainer.add(product);
    }

    public List<Product> getProductContainer() {
        return productContainer;
    }

    public Set<Integer> getAllowedCoins() {
        return allowedCoins;
    }

    public int getClientMoney() {
        return clientCoins.stream()
                .reduce((a, b) -> (a + b))
                .orElse(0);
    }

    private void initAllowedCoins() {
        Collections.addAll(allowedCoins, 1, 5, 10, 25, 50);
    }
}
