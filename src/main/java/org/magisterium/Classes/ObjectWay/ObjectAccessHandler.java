package org.magisterium.Classes.ObjectWay;

import org.fusesource.jansi.Ansi;
import org.magisterium.Classes.Banks.Bank;

import java.util.Map;
import java.util.Scanner;
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
public class ObjectAccessHandler {
    private final Bank bank;
    private final String[] DATA_ACCESS_QUOTES = {
            "🔐 Dostęp do skarbca danych...",
            "📊 Panel kontrolny aktywowany...",
            "🎯 Wybierz cel swojej operacji...",
            "💫 Przygotuj się do inspekcji..."
    };

    private final Map<String, String> FIELD_ICONS = Map.of(
            "saldo", "💰",
            "nazwa", "👤",
            "data", "📅",
            "hasło", "🔑",
            "status", "⚡"
    );
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
        // Losowy cytat
        String randomQuote = DATA_ACCESS_QUOTES[(int)(Math.random() * DATA_ACCESS_QUOTES.length)];

        System.out.println(
                Ansi.ansi()
                        .fg(Ansi.Color.CYAN)
                        .bold()
                        .a("\n" + randomQuote)
                        .reset().toString()
        );

        // Header z efektem ramki
        System.out.println(
                Ansi.ansi()
                        .fg(Ansi.Color.MAGENTA)
                        .bold()
                        .a("\n╔════════════════════════╗")
                        .reset().toString()
        );

        System.out.println(
                Ansi.ansi()
                        .fg(Ansi.Color.MAGENTA)
                        .bold()
                        .a("║   DOSTĘP DO DANYCH    ║")
                        .reset().toString()
        );

        System.out.println(
                Ansi.ansi()
                        .fg(Ansi.Color.MAGENTA)
                        .bold()
                        .a("╚════════════════════════╝")
                        .reset().toString()
        );

        // Opcje menu z kolorami i ikonami
        System.out.println(
                Ansi.ansi()
                        .fg(Ansi.Color.GREEN)
                        .a("\n" + FIELD_ICONS.get("saldo") + " 1. Saldo")
                        .reset().toString() +
                        Ansi.ansi()
                                .fg(Ansi.Color.BLUE)
                                .a(" [Stan konta]")
                                .reset().toString()
        );

        System.out.println(
                Ansi.ansi()
                        .fg(Ansi.Color.YELLOW)
                        .a(FIELD_ICONS.get("nazwa") + " 2. Nazwa użytkownika")
                        .reset().toString() +
                        Ansi.ansi()
                                .fg(Ansi.Color.BLUE)
                                .a(" [Identyfikator]")
                                .reset().toString()
        );

        System.out.println(
                Ansi.ansi()
                        .fg(Ansi.Color.CYAN)
                        .a(FIELD_ICONS.get("data") + " 3. Data utworzenia konta")
                        .reset().toString() +
                        Ansi.ansi()
                                .fg(Ansi.Color.BLUE)
                                .a(" [Historia]")
                                .reset().toString()
        );

        System.out.println(
                Ansi.ansi()
                        .fg(Ansi.Color.RED)
                        .a(FIELD_ICONS.get("hasło") + " 4. Hasło")
                        .reset().toString() +
                        Ansi.ansi()
                                .fg(Ansi.Color.BLUE)
                                .a(" [Poufne]")
                                .reset().toString()
        );

        System.out.println(
                Ansi.ansi()
                        .fg(Ansi.Color.GREEN)
                        .a(FIELD_ICONS.get("status") + " 5. Status aktywności")
                        .reset().toString() +
                        Ansi.ansi()
                                .fg(Ansi.Color.BLUE)
                                .a(" [Monitoring]")
                                .reset().toString()
        );

        // Opcja powrotu z efektem
        System.out.println(
                Ansi.ansi()
                        .fg(Ansi.Color.RED)
                        .bold()
                        .a("\n↩ 0. Powrót do menu głównego")
                        .reset().toString()
        );

        // Prompt z efektem migania
        System.out.print(
                Ansi.ansi()
                        .fg(Ansi.Color.CYAN)
                        .bold()
                        .a("\n=> Twój wybór: ")
                        .reset().toString()
        );

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