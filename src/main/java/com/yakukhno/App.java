package com.yakukhno;

import com.yakukhno.controller.PointOfSaleController;
import com.yakukhno.model.PointOfSale;
import com.yakukhno.model.Product;
import com.yakukhno.view.ConsoleView;
import com.yakukhno.view.View;

import java.util.Scanner;

public class App {
    public static void main( String[] args ) {
        PointOfSale pointOfSale = new PointOfSale();
        pointOfSale.addProductToContainer(new Product("Tea", 25));
        pointOfSale.addProductToContainer(new Product("Coffee", 35));
        pointOfSale.addProductToContainer(new Product("Juice", 45));
        View view = new ConsoleView();
        PointOfSaleController controller = new PointOfSaleController(pointOfSale, view);
        controller.setScanner(new Scanner(System.in));
        controller.execute();
    }
}
