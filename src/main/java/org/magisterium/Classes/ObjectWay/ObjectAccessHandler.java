package org.magisterium.Classes.ObjectWay;

import org.fusesource.jansi.Ansi;
import org.magisterium.Classes.Banks.Bank;

import java.util.Map;
import java.util.Scanner;
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

            handleFieldAccess(fieldChoice, scanner);
        }
    }
    public void handleFieldAccess(String fieldChoice, Scanner scanner) {
        while (true) {
            String accessChoice = displayFieldAccessMenu(fieldChoice, scanner);

            if ("0".equals(accessChoice)) {
                break;
            }

            switch (fieldChoice) {
                case "1":
                    handleBalanceAccess(accessChoice, scanner);
                    break;
                case "2":
                    handleUsernameAccess(accessChoice, scanner);
                    break;
                case "3":
                    handleAccountCreationDateAccess(accessChoice);
                    break;
                case "4":
                    handlePasswordAccess(accessChoice, scanner);
                    break;
                case "5":
                    handleActivityStatusAccess(accessChoice, scanner);
                    break;
            }
        }
    }

    private void handleBalanceAccess(String accessChoice, Scanner scanner) {
        switch (accessChoice) {
            case "1":
                System.out.println(
                        Ansi.ansi()
                                .fg(Ansi.Color.RED)
                                .bold()
                                .a(" 🚫  ❌ ACCESS DENIED  ❌  🚫")
                                .reset().toString()
                );

                break;
            case "2":
                System.out.println("💰 Saldo: " + bank.getBalance());
                break;
            case "3":
                System.out.print("Podaj nowe saldo: ");
                try {
                    double newBalance = Double.parseDouble(scanner.nextLine());
                    bank.setBalance(newBalance);  // Odkomentować, gdy Bank będzie miał metodę setBalance
                    System.out.println("✅ Saldo zostało zaktualizowane.");
                } catch (NumberFormatException e) {
                    System.out.println("❌ Nieprawidłowy format kwoty.");
                }
                break;
        }
    }

    private String displayFieldAccessMenu(String fieldChoice, Scanner scanner) {
        String fieldName = getFieldName(fieldChoice);



        System.out.println(
                Ansi.ansi()
                        .fg(Ansi.Color.MAGENTA)
                        .bold()
                        .a("\nDOSTĘP DO POLA: " + fieldName + "\n" )
                        .reset().toString()
        );






        System.out.println(Ansi.ansi().fg(Ansi.Color.RED).a("1. Bezpośredni dostęp").reset());
        System.out.println(Ansi.ansi().fg(Ansi.Color.GREEN).a("2. Wyświetl wartość (Getter)").reset());
        System.out.println(Ansi.ansi().fg(Ansi.Color.BLUE).a("3. Ustaw wartość (Setter)").reset());
        System.out.println(Ansi.ansi().fg(Ansi.Color.YELLOW).a("0. Powrót do menu głównego").reset());

        System.out.print(
                Ansi.ansi()
                        .fg(Ansi.Color.CYAN)
                        .bold()
                        .a("\n=> Twój wybór: ")
                        .reset().toString()
        );

        return getNormalizedChoice(scanner.nextLine());
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


        System.out.println(
                Ansi.ansi()
                        .fg(Ansi.Color.MAGENTA)
                        .bold()
                        .a("Wersja Obiektowa - Menu Główne")
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
                        .a("↩ 0. Powrót do menu głównego")
                        .reset().toString()
        );

        // Prompt z efektem migania
        System.out.print(
                Ansi.ansi()
                        .fg(Ansi.Color.CYAN)
                        .bold()
                        .a("=> Twój wybór: ")
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




    private void handleUsernameAccess(String accessChoice, Scanner scanner) {
        switch (accessChoice) {
            case "1":


                System.out.println(
                        Ansi.ansi()
                                .fg(Ansi.Color.RED)
                                .bold()
                                .a(" \n 🚫  ❌ ACCESS DENIED  ❌  🚫")
                                .reset().toString()
                );


                break;
            case "2":
                System.out.println("👤 Nazwa użytkownika: " + bank.getUsername());
                break;
            case "3":
                System.out.print("Podaj nową nazwę użytkownika: ");
                String newUsername = scanner.nextLine();
                 bank.setUsername(newUsername);
                System.out.println("✅ Nazwa użytkownika została zaktualizowana.");
                break;
        }
    }

    private void handleAccountCreationDateAccess(String accessChoice) {
        switch (accessChoice) {
            case "1":
                System.out.println("🚫 Bezpośredni dostęp do daty utworzenia: Access Denied!");
                break;
            case "2":
                System.out.println("📅 Data utworzenia konta: " + bank.getAccountCreationDate());
                break;
            case "3":
                System.out.println("❌ Nie można modyfikować daty utworzenia konta - ponieważ jest to nie zgodne z prawdą.");
                break;
        }
    }

    private void handlePasswordAccess(String accessChoice, Scanner scanner) {
        switch (accessChoice) {
            case "1":
                System.out.println(
                        Ansi.ansi()
                                .fg(Ansi.Color.RED)
                                .bold()
                                .a(" 🚫  ❌ ACCESS DENIED  ❌  🚫")
                                .reset().toString()
                );
                break;
            case "2":
                System.out.println("🔒 Status hasła: Chronione  ");
                break;
            case "3":
                System.out.print("Podaj nowe hasło: ");
                String newPassword = scanner.nextLine();
                // bank.setPassword(newPassword);  // Odkomentować, gdy Bank będzie miał metodę setPassword
                System.out.println("✅ Hasło zostało zaktualizowane.");
                break;
        }
    }

    private void handleActivityStatusAccess(String accessChoice, Scanner scanner) {
        switch (accessChoice) {
            case "1":
                System.out.println(
                        Ansi.ansi()
                                .fg(Ansi.Color.RED)
                                .bold()
                                .a(" 🚫  ❌ ACCESS DENIED  ❌  🚫")
                                .reset().toString()
                );
                break;
            case "2":
                System.out.println("⚡ Status aktywności: " + bank.isActive());
                break;
            case "3":
                System.out.print("Podaj nowy status aktywności (true/false): ");
                try {
                    boolean newStatus = Boolean.parseBoolean(scanner.nextLine());
                    // bank.setActive(newStatus);  // Odkomentować, gdy Bank będzie miał metodę setActive
                    System.out.println("✅ Status aktywności został zaktualizowany.");
                } catch (Exception e) {
                    System.out.println("❌ Nieprawidłowy format statusu.");
                }
                break;
        }
    }

    private String getFieldName(String choice) {
        switch (choice) {
            case "1": return "SALDO";
            case "2": return "NAZWA UŻYTKOWNIKA";
            case "3": return "DATA UTWORZENIA";
            case "4": return "HASŁO";
            case "5": return "STATUS AKTYWNOŚCI";
            default: return "NIEZNANE";
        }
    }
}