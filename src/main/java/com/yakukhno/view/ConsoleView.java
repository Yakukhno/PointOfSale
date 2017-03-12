package com.yakukhno.view;

public class ConsoleView implements View {
    public static final String MENU_MESSAGE = "Proceed coins insertion - 1, " +
            "choose drink - 2, return coins - 0.";
    public static final String INSERT_COIN_MESSAGE
            = "Insert your coin (allowed : 1, 5, 10, 25, 50)";
    public static final String INVALID_COIN_MESSAGE = "Invalid coin!";
    public static final String ERROR_INPUT_MESSAGE = "Wrong data input! " +
            "Try again!";

    public void showMessage(String message) {
        System.out.println(message);
    }
}
