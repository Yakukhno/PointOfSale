package com.yakukhno.controller;

import com.yakukhno.model.PointOfSale;
import com.yakukhno.view.ConsoleView;
import com.yakukhno.view.View;

import java.util.Scanner;

public class PointOfSaleController implements Controller {
    private PointOfSale pointOfSale;
    private View view;

    private Scanner scanner;

    public PointOfSaleController(PointOfSale pointOfSale, View view) {
        this.pointOfSale = pointOfSale;
        this.view = view;
    }

    public void execute() {
        handleCoinsInsertion();
        System.out.println(pointOfSale.getClientCoins());
    }

    private void handleCoinsInsertion() {
        do {
            handleCoinInsertion();
            view.showMessage(ConsoleView.MENU_MESSAGE);
        } while (readUserInput(2) == 1);
    }

    private void handleCoinInsertion() {
        view.showMessage(ConsoleView.INSERT_COIN_MESSAGE);
        boolean idAdded = pointOfSale.addCoin(readUserInput(50));
        if (!idAdded) {
            view.showMessage(ConsoleView.INVALID_COIN_MESSAGE);
            handleCoinInsertion();
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

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }
}
