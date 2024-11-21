package org.magisterium.Classes.ObjectWay;

import org.magisterium.Classes.Banks.Bank;

import java.util.Scanner;

public class ObjectAccessHandler {
    private final Bank bank;

    public ObjectAccessHandler(Bank bank) {
        this.bank = bank;
    }

    public void handleAccess() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String fieldChoice = getNormalizedChoice(displayFieldMenu(scanner));

            if ("0".equals(fieldChoice)) {
                System.out.println("Powrót do menu głównego.");
                return;
            }

            System.out.println(getFieldValueObjectively(fieldChoice));
        }
    }

    private String displayFieldMenu(Scanner scanner) {
        System.out.println("\n=== DOSTĘP DO DANYCH ===");
        System.out.println("1. Saldo");
        System.out.println("2. Nazwa użytkownika");
        System.out.println("3. Data utworzenia konta");
        System.out.println("4. Hasło");
        System.out.println("5. Status aktywności");
        System.out.println("0. Powrót");
        System.out.print("Twój wybór: ");
        return scanner.nextLine();
    }

    public String getNormalizedChoice(String input) {
        String normalized = input.strip().replace(".", "").toLowerCase();
        switch (normalized) {
            case "1": return "1";
            case "2": return "2";
            case "3": return "3";
            case "4": return "4";
            case "5": return "5";
            case "0": return "0";
            default: return ""; // W przypadku nieprawidłowego wyboru
        }
    }

    private String getFieldValueObjectively(String choice) {
        switch (choice) {
            case "1": return safelyGetBalance();
            case "2": return "Nazwa użytkownika: " + bank.getUsername();
            case "3": return "Data utworzenia konta: " + bank.getAccountCreationDate();
            case "4": return "Dostęp do pola 'Hasło': Access Denied!";
            case "5": return "Status aktywności: " + bank.isActive();
            default: return "Nieprawidłowy wybór.";
        }
    }

    private String safelyGetBalance() {
        try {
            return "Saldo: " + bank.getBalance();
        } catch (Exception e) {
            return "Access Denied: Pole prywatne!";
        }
    }
}