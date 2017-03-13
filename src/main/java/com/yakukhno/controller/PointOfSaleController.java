package com.yakukhno.controller;

import com.yakukhno.model.PointOfSale;
import com.yakukhno.model.ProductType;
import com.yakukhno.model.Sale;
import com.yakukhno.model.Status;
import com.yakukhno.view.ConsoleView;
import com.yakukhno.view.View;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PointOfSaleController implements Controller {
    private PointOfSale pointOfSale;
    private View view;

    private Scanner scanner;
    private Map<Integer, ProductType> codeToProductType;

    public PointOfSaleController(PointOfSale pointOfSale, View view) {
        this.pointOfSale = pointOfSale;
        this.view = view;
        initCodeToProductType();
    }

    public void execute() {
        handleCoinsInsertion();
    }

    private void handleCoinsInsertion() {
        int code;
        do {
            handleCoinInsertion();
            view.showMessage(ConsoleView.MENU_MESSAGE);
            code = readUserInput(2);
        } while (code == 1);
        if (code == 0) {
            handleSale(pointOfSale.refund());
        } else if (code == 2) {
            handleProductSelection();
        }
    }

    private void handleProductSelection() {
        view.showMessage(ConsoleView.SELECT_PRODUCT_MESSAGE);
        int code = readUserInput(3);
        Sale sale = pointOfSale.getProduct(codeToProductType.get(code));
        handleSale(sale);
    }

    private void handleCoinInsertion() {
        view.showMessage(ConsoleView.INSERT_COIN_MESSAGE);
        boolean idAdded = pointOfSale.addCoin(readUserInput(50));
        if (!idAdded) {
            view.showMessage(ConsoleView.INVALID_COIN_MESSAGE);
            handleCoinInsertion();
        }
    }

    private void handleSale(Sale sale) {
        if (sale.getProduct() != null) {
            view.showMessage(ConsoleView.PRODUCT_MESSAGE,
                    sale.getProduct().getType().name());
        }
        if (sale.getChange() != 0) {
            view.showMessage(ConsoleView.RECEIVE_MONEY_MESSAGE,
                    sale.getChange());
        }
        view.showMessage(ConsoleView.STATUS_MESSAGE, sale.getStatus().name());
        if (sale.getStatus().equals(Status.NOT_ENOUGH_MONEY)) {
            handleCoinsInsertion();
        }
    }

    private int readUserInput(int numberOfOptions) {
        int userInput;
        try {
            userInput = Integer.parseInt(scanner.next());
            if (userInput > numberOfOptions || userInput < 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            view.showMessage(ConsoleView.ERROR_INPUT_MESSAGE);
            userInput = readUserInput(numberOfOptions);
        }
        return userInput;
    }

    private void initCodeToProductType() {
        codeToProductType = new HashMap<>();
        int i = 1;
        for (ProductType type : ProductType.values()) {
            codeToProductType.put(i++, type);
        }
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }
}
