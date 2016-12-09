package ui;

import controller.Controller;
import utils.SymbolTable;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

/**
 * Created by glinut on 11/4/2016.
 */
public class UI {
    Controller controller;

    public UI(Controller controller) {
        this.controller = controller;
    }

    public void run() throws FileNotFoundException, UnsupportedEncodingException {
        Scanner input = new Scanner(System.in);
        while (true) {
            printMenu();
            String option;
            System.out.println("Enter your option: ");
            option = input.next();

            switch (option) {
                case "0":
                    return;
                case "1":
                    controller.executeOne();
                    break;
                case "2":
                    controller.executeAll();
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }
    }

    private void printMenu() {
        System.out.print("Menu:\n"
                + "\t0. Exit.\n"
                + "\t1. Execute one step.\n"
                + "\t2. Execute all statements.\n");

    }
}
