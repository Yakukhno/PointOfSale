package com.yakukhno.controller;

import com.yakukhno.model.PointOfSale;
import com.yakukhno.model.Product;
import com.yakukhno.model.Sale;
import com.yakukhno.model.Status;
import com.yakukhno.view.ConsoleView;
import com.yakukhno.view.View;

import java.util.List;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

public class PointOfSaleController implements Controller {
    private PointOfSale pointOfSale;
    private View view;

    private Scanner scanner;

    public PointOfSaleController(PointOfSale pointOfSale, View view) {
        this.pointOfSale = pointOfSale;
        this.view = view;
    }

    public void execute() {
        handleMenuSelection();
    }

    private void handleMenuSelection() {
        view.showMessage(ConsoleView.MENU_MESSAGE);
        int code = readUserInput(2);
        switch (code) {
            case 1:
                handleCoinInsertion();
                break;
            case 2:
                handleProductSelection();
                break;
            case 0:
                handleSale(pointOfSale.refund());
                break;
            default:
                throw new IllegalArgumentException();
        }
    }

    private void handleCoinInsertion() {
        view.showMessage(ConsoleView.INSERT_COIN_MESSAGE, getAllowedCoinsString());
        Sale sale = pointOfSale.addCoin(readUserInput());
        handleSale(sale);
        handleMenuSelection();
    }

    private void handleProductSelection() {
        view.showMessage(ConsoleView.SELECT_PRODUCT_MESSAGE, getProductsString());
        int code = readUserInput(pointOfSale.getProductContainer().size() - 1);
        Sale sale = pointOfSale.getProduct(code);
        handleSale(sale);
    }

    private String getProductsString() {
        List<Product> products = pointOfSale.getProductContainer();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < products.size(); i++) {
            builder.append(i)
                    .append("-")
                    .append(products.get(i).getName())
                    .append(" ");
        }
        return builder.toString();
    }

    private String getAllowedCoinsString() {
        SortedSet<Integer> allowedCoins = new TreeSet<>(pointOfSale.getAllowedCoins());
        StringBuilder builder = new StringBuilder();
        for (int coin : allowedCoins) {
            builder.append(coin).append(" ");
        }
        return builder.toString();
    }

    private void handleSale(Sale sale) {
        if (sale.getProduct() != null) {
            view.showMessage(ConsoleView.PRODUCT_MESSAGE,
                    sale.getProduct().getName());
        }
        if (sale.getChange() != 0) {
            view.showMessage(ConsoleView.RECEIVE_MONEY_MESSAGE,
                    sale.getChange());
        }
        view.showMessage(ConsoleView.STATUS_MESSAGE, sale.getStatus().name());
        if (sale.getStatus().equals(Status.NOT_ENOUGH_MONEY)) {
            handleMenuSelection();
        }
    }

    private int readUserInput(int max) {
        int userInput;
        try {
            userInput = Integer.parseInt(scanner.next());
            if ((userInput < 0) || (userInput > max)) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            view.showMessage(ConsoleView.ERROR_INPUT_MESSAGE);
            userInput = readUserInput(max);
        }
        return userInput;
    }

    private int readUserInput() {
        int userInput;
        try {
            userInput = Integer.parseInt(scanner.next());
        } catch (NumberFormatException e) {
            view.showMessage(ConsoleView.ERROR_INPUT_MESSAGE);
            userInput = readUserInput();
        }
        return userInput;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }
}
