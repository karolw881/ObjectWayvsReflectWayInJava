package org.magisterium.Classes;

import org.magisterium.Interfaces.BankAccount;

import java.lang.reflect.Field;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Bank bank = new Bank(1000, "janek", "tajneHaslo123"); // Utworzenie instancji banku

        int choice;
        do {
            displayMainMenu();
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    accessBankDataObjectively(scanner, bank);
                    break;
                case 2:
                    accessBankDataReflectively(bank);
                    break;
                case 0:
                    System.out.println("Zakończono.");
                    break;
                default:
                    System.out.println("Nieprawidłowy wybór.");
            }
        } while (choice != 0);

        scanner.close();
    }

    static void displayMainMenu() {
        System.out.println("\nMenu:");
        System.out.println("1. Obiektowa");
        System.out.println("2. Refleksyjna");
        System.out.println("0. Zakończ");
        System.out.print("Twój wybór: ");
    }

    static void displayFieldMenu() {
        System.out.println("\nWybierz pole do dostępu:");
        System.out.println("1. Saldo ");
        System.out.println("2. Nazwa użytkownika ");
        System.out.println("3. Data utworzenia konta ");
        System.out.println("4. Hasło ");
        System.out.println("5. Status aktywności ");
        System.out.println("0. Powrót");
        System.out.print("Twój wybór: ");
    }

    static void accessBankDataObjectively(Scanner scanner, Bank bank) {
        int fieldChoice;
        do {
            displayFieldMenu();
            fieldChoice = scanner.nextInt();
            scanner.nextLine();

            switch (fieldChoice) {
                case 1:
                    // Próba dostępu do pola prywatnego
                    try {
                        System.out.println("Saldo: " + bank.getBalance()); // Pole chronione - dostępne
                    } catch (Exception e) {
                        System.out.println("Dostęp zabroniony: Pole prywatne!");
                    }
                    break;
                case 2:
                    System.out.println("Nazwa użytkownika: " + bank.username); // Pole chronione
                    break;
                case 3:
                    System.out.println("Data utworzenia konta: " + bank.accountCreationDate); // Pole chronione
                    break;
                case 4:
                    System.out.println("Dostęp do pola 'hasło' (private) zabroniony!");
                    break;
                case 5:
                    System.out.println("Status aktywności: " + bank.isActive()); // Publiczna metoda
                    break;
                case 0:
                    System.out.println("Powrót do menu głównego.");
                    break;
                default:
                    System.out.println("Nieprawidłowy wybór.");
            }
        } while (fieldChoice != 0);
    }


    static void accessBankDataReflectively(BankAccount bank) {
        try {
            Class<?> bankClass = bank.getClass();
            System.out.println("\nInformacje o klasie:");
            System.out.println("Nazwa klasy: " + bankClass.getName());

            Field[] fields = bankClass.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                Object value = field.get(bank);
                // Unikamy wyświetlania hasła
                if (!field.getName().equals("passwordHash")) {
                    System.out.printf("%-30s %s%n", field.getName(), value);
                }
            }

        } catch (Exception e) {
            System.err.println("Błąd refleksji: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
