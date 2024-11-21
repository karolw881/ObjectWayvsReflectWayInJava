package org.magisterium.Classes.LolScanner;

import org.magisterium.Classes.Banks.Bank;
import org.magisterium.Classes.ObjectWay.ObjectAccessHandler;
import org.magisterium.Classes.ReflectWay.ReflectionAccessHandler;

import java.io.InputStream;
import java.util.Scanner;

public class MyScanner {
    private final Scanner scanner;
    private final Bank bank;
    private final ObjectAccessHandler objectAccessHandler;
    private final ReflectionAccessHandler reflectionAccessHandler;

    public MyScanner(InputStream in) {
        this.scanner = new Scanner(in);
        this.bank = new Bank(1000, "janek", "tajneHaslo123");
        this.objectAccessHandler = new ObjectAccessHandler(bank);
        this.reflectionAccessHandler = new ReflectionAccessHandler(bank);
    }

    public void run() {
        while (true) {
            String choice = getNormalizedChoice(displayMainMenu());

            if ("1".equals(choice)) {
                objectAccessHandler.handleAccess();
            } else if ("2".equals(choice)) {
                reflectionAccessHandler.handleAccess();
            } else if ("0".equals(choice)) {
                System.out.println("Zakończono.");
                return;
            } else {
                System.out.println("Nieprawidłowy wybór.");
            }
        }
    }

    private String displayMainMenu() {
        System.out.println("\n==== MENU GŁÓWNE ====");
        System.out.println("1. Obiektowa");
        System.out.println("2. Refleksyjna");
        System.out.println("0. Zakończ");
        System.out.print("Twój wybór: ");
        return scanner.nextLine();
    }

    public String getNormalizedChoice(String input) {
        String normalized = input.strip().replace(".", "").toLowerCase();
        switch (normalized) {
            case "1": case "obiektowa": return "1";
            case "2": case "refleksyjna": return "2";
            case "0": case "zakończ": return "0";
            default: return ""; // W przypadku nieprawidłowego wyboru
        }
    }
}