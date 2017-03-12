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

    public int getClientCoins() {
        return clientCoins.stream()
                .reduce((a, b) -> (a + b))
                .orElse(0);
    }

    private void initAllowedCoins() {
        Collections.addAll(allowedCoins, 1, 5, 10, 25, 50);
    }
}
