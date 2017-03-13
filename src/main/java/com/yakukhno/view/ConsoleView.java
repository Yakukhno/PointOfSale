package com.yakukhno.view;

public class ConsoleView implements View {
    public static final String MENU_MESSAGE = "Proceed coins insertion - 1, " +
            "choose product - 2, refund - 0.";
    public static final String INSERT_COIN_MESSAGE
            = "Insert your coin (allowed : 1, 5, 10, 25, 50)";
    public static final String SELECT_PRODUCT_MESSAGE
            = "Select product tea - 1, coffee - 2, juice - 3.";
    public static final String RECEIVE_MONEY_MESSAGE
            = "Please, receive your money : %d";
    public static final String PRODUCT_MESSAGE = "Your product : %s";
    public static final String STATUS_MESSAGE = "Status : %s";
    public static final String INVALID_COIN_MESSAGE = "Invalid coin!";
    public static final String ERROR_INPUT_MESSAGE = "Wrong data input! " +
            "Try again!";

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void showMessage(String message, int number) {
        System.out.println(String.format(message, number));
    }

    public void showMessage(String message, String param){
        System.out.println(String.format(message, param));
    }

}
