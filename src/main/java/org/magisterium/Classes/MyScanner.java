package org.magisterium.Classes;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Scanner;

public class MyScanner {
    private final Scanner scanner;
    private final Bank bank;

    public MyScanner(InputStream in) {
        this.scanner = new Scanner(System.in);
        this.bank = new Bank(1000, "janek", "tajneHaslo123");
    }

    public void run() {
        while (true) {
            String choice = getNormalizedChoice(displayMainMenu());

            if ("1".equals(choice)) {
                handleObjectAccess();
            } else if ("2".equals(choice)) {
                handleReflectionAccess();
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

    private String displayFieldMenu() {
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

    /**
     * Ciągle sie zastanawiam gdzie jest przewaga refleksji nad obiektowoscią. Przeciez w obiektowej opcji tez mamy
     * dostep do pol private przez gettery
     */

    // TODO: Albo refleksyjnie podczytac typ - property -  field  i wtedy na tej  podstawie  okreslac  dostęp (enkapsulacje)
    //  i jak wysietli private to wyswietlic acces deniad
    // TODO: Albo wszedzie sout-tem  po hamsku wysietlac acces deniad
    //  TODO3: Albo mozna tez z getterrami prywatne pola wysiwetlac i roznica w refleksji i obiektowosci bedzie to ze w
    //  refleksj mozna modyfikowac private i wysietlac cale informacje o klasach, metodach itd



    /**
     * Wiec trzeba jeszcze poprwic funkcje
     */


    public String getNormalizedChoice(String input) {
        // Usunięcie kropek, spacji i zamiana na niższe litery
        String normalized = input.strip().replace(".", "").toLowerCase();
        if ("1".equals(normalized) || "obiektowa".equals(normalized)) {
            return "1";
        } else if ("2".equals(normalized) || "refleksyjna".equals(normalized)) {
            return "2";
        } else if ("0".equals(normalized) || "zakończ".equals(normalized)) {
            return "0";
        } else if ("3".equals(normalized) || "data utworzenia konta".equals(normalized)) {
            return "3";
        } else if ("4".equals(normalized) || "hasło".equals(normalized)) {
            return "4";
        } else if ("5".equals(normalized) || "status aktywności".equals(normalized)) {
            return "5";
        }
        return ""; // W przypadku nieprawidłowego wyboru
    }


    private void handleObjectAccess() {
        while (true) {
            String fieldChoice = getNormalizedChoice(displayFieldMenu());

            if ("0".equals(fieldChoice)) {
                System.out.println("Powrót do menu głównego.");
                return;
            }

            System.out.println(getFieldValueObjectively(fieldChoice));
        }
    }

    private String getFieldValueObjectively(String choice) {
        if ("1".equals(choice)) {
            return safelyGetBalance();
        } else if ("2".equals(choice)) {
            return "Nazwa użytkownika: " + bank.getUsername();
        } else if ("3".equals(choice)) {
            return "Data utworzenia konta: " + bank.getAccountCreationDate();
        } else if ("4".equals(choice)) {
            return "Dostęp do pola 'Hasło': Access Denied!";
        } else if ("5".equals(choice)) {
            return "Status aktywności: " + bank.isActive();
        }
        return "Nieprawidłowy wybór.";
    }

    private String safelyGetBalance() {
        try {
            return "Saldo: " + bank.getBalance();
        } catch (Exception e) {
            return "Access Denied: Pole prywatne!";
        }
    }

    private void handleReflectionAccess() {
        System.out.println("\nDostęp refleksyjny do klasy Bank:");

        try {
            Class<?> bankClass = bank.getClass();
            for (Field field : bankClass.getDeclaredFields()) {
                field.setAccessible(true); // Obejście ograniczenia dostępu
                Object value = field.get(bank);
                printFieldDetails(field, value);
            }
        } catch (IllegalAccessException e) {
            System.err.println("Błąd podczas dostępu do pól: " + e.getMessage());
        }
    }

    private void printFieldDetails(Field field, Object value) {
        boolean isPrivate = !field.canAccess(bank);
        System.out.printf(
                "Pole: %-20s Wartość: %-20s Typ: %-10s Dostęp: %s%n",
                field.getName(),
                value != null ? value : "null",
                field.getType().getSimpleName(),
                isPrivate ? "Access Denied" : "Public"
        );
    }
}
