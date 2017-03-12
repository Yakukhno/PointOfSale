package com.yakukhno;

import com.yakukhno.controller.PointOfSaleController;
import com.yakukhno.model.PointOfSale;
import com.yakukhno.view.ConsoleView;
import com.yakukhno.view.View;

import java.util.Scanner;

public class App {
    public static void main( String[] args ) {
        PointOfSale pointOfSale = new PointOfSale();
        View view = new ConsoleView();
        PointOfSaleController controller = new PointOfSaleController(pointOfSale, view);
        controller.setScanner(new Scanner(System.in));
        controller.execute();
    }
}
