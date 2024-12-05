package org.magisterium.Classes.ReflectWay;

import org.fusesource.jansi.Ansi;
import org.magisterium.Classes.Banks.Bank;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Scanner;

public class ReflectionAccessHandlerChild extends ReflectionAccessHandler {

    public ReflectionAccessHandlerChild(Bank bank) {
        super(bank);
    }

    private final String[] DATA_ACCESS_QUOTES = {
            "🔐 Dostęp do skarbca danych...",
            "📊 Panel kontrolny aktywowany...",
            "🎯 Wybierz cel swojej operacji...",
            "💫 Przygotuj się do inspekcji..."
    };


    private final Map<String, String> FIELD_ICONS = Map.of(
            "Saldo [Stan konta]", "💰",
            "Nazwa użytkownika [Identyfikator]", "👤",
            "Data utworzenia konta [Historia]", "📅",
            "Hasło [Poufne]", "⚡",
            "Status aktywności", "[Monitoring]"
    );

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

    private String displayFieldAccessMenu(String fieldChoice, Scanner scanner) {
        String fieldName = getFieldName2(fieldChoice);

        System.out.println(
                Ansi.ansi()
                        .fg(Ansi.Color.MAGENTA)
                        .bold()
                        .a("\n╔══════════════════════════════════════════════════════╗")
                        .reset().toString()
        );


        System.out.println(
                Ansi.ansi()
                        .fg(Ansi.Color.MAGENTA)
                        .bold()
                        .a("║   DOSTĘP DO : " + fieldName + "                              ║")
                        .reset().toString()
        );

        System.out.println(
                Ansi.ansi()
                        .fg(Ansi.Color.MAGENTA)
                        .bold()
                        .a("╚══════════════════════════════════════════════════════╝")
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


    /*    Glowne Menu    */


    private String displayFieldMenu(Scanner scanner) {
        String randomQuote = DATA_ACCESS_QUOTES[(int) (Math.random() * DATA_ACCESS_QUOTES.length)];
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
                        .a("║   DOSTĘP DO DANYCH     ║")
                        .reset().toString()
        );

        // Header z efektem ramki
        System.out.println(
                Ansi.ansi()
                        .fg(Ansi.Color.MAGENTA)
                        .bold()
                        .a("════════════════════════")
                        .reset().toString()
        );


        // Opcje menu z kolorami i ikonami
        System.out.println(
                Ansi.ansi()
                        .fg(Ansi.Color.GREEN)
                        .a(FIELD_ICONS.get("wszystkie") + "1. wszystkie informacje o danych ")
                        .reset().toString() +
                        Ansi.ansi()
                                .fg(Ansi.Color.BLUE)
                                .a(" [wszystko co potrzeba]")
                                .reset().toString()
        );
        System.out.println(
                Ansi.ansi()
                        .fg(Ansi.Color.YELLOW)
                        .a(FIELD_ICONS.get("Konstruktory") + " 2. Konstruktory")
                        .reset().toString() +
                        Ansi.ansi()
                                .fg(Ansi.Color.BLUE)
                                .a(" [Dane o inicjalizacji]")
                                .reset().toString()
        );

        System.out.println(
                Ansi.ansi()
                        .fg(Ansi.Color.CYAN)
                        .a(FIELD_ICONS.get("data") + " 3. Annotacje ")
                        .reset().toString() +
                        Ansi.ansi()
                                .fg(Ansi.Color.BLUE)
                                .a(" [Dane Annotacyjne]")
                                .reset().toString()
        );

        System.out.println(
                Ansi.ansi()
                        .fg(Ansi.Color.RED)
                        .a(FIELD_ICONS.get("Dostep do danych") + "4. Dostep do danych ")
                        .reset().toString() +
                        Ansi.ansi()
                                .fg(Ansi.Color.BLUE)
                                .a(" [Do pola]")
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




    /*    USERNAME  */


    private void handleUsernameAccess(String accessChoice, Scanner scanner) {
        switch (accessChoice) {
            case "1":
                try {
                    // Pobranie klasy obiektu bank
                    Class<?> bankClass = bank.getClass();

                    // Pobranie pola "balance"
                    Field balanceField = bankClass.getDeclaredField("username");

                    // Ustawienie dostępu do prywatnego pola
                    balanceField.setAccessible(true);

                    // Odczytanie wartości pola "balance" z instancji obiektu bank
                    Object balanceValue = balanceField.get(bank);


                    System.out.println("💰 username: " + balanceValue);
                } catch (NoSuchFieldException e) {
                    System.out.println("❌ Pole 'username' nie istnieje.");
                } catch (IllegalAccessException e) {
                    System.out.println("❌ Brak dostępu do pola 'username'.");
                }
                break;
            case "2":
                System.out.println("👤 Nazwa użytkownika: " + bank.getUsername());
                break;
            case "3":
                System.out.print("Podaj nową nazwę użytkownika: ");
                String newUsername = scanner.nextLine();
                // bank.setUsername(newUsername);  // Odkomentować, gdy Bank będzie miał metodę setUsername
                System.out.println("✅ Nazwa użytkownika została zaktualizowana.");
                break;
        }
    }

    /* Data dostepu */
    private void handleAccountCreationDateAccess(String accessChoice) {
        switch (accessChoice) {
            case "1":
                try {
                    // Pobranie klasy obiektu bank
                    Class<?> bankClass = bank.getClass();

                    // Pobranie pola "balance"
                    Field balanceField = bankClass.getDeclaredField("username");

                    // Ustawienie dostępu do prywatnego pola
                    balanceField.setAccessible(true);

                    // Odczytanie wartości pola "balance" z instancji obiektu bank
                    Object balanceValue = balanceField.get(bank);


                    System.out.println("💰 username: " + balanceValue);
                } catch (NoSuchFieldException e) {
                    System.out.println("❌ Pole 'username' nie istnieje.");
                } catch (IllegalAccessException e) {
                    System.out.println("❌ Brak dostępu do pola 'username'.");
                }
                break;
            case "2":

            case "3":

        }
    }


    /* PASSWORD  */
    private void handlePasswordAccess(String accessChoice, Scanner scanner) {
        switch (accessChoice) {
            case "1":
                try {
                    // Pobranie klasy obiektu bank
                    Class<?> bankClass = bank.getClass();

                    // Pobranie pola "balance"
                    Field balanceField = bankClass.getDeclaredField("balance");

                    // Ustawienie dostępu do prywatnego pola
                    balanceField.setAccessible(true);

                    // Odczytanie wartości pola "balance" z instancji obiektu bank
                    Object balanceValue = balanceField.get(bank);


                    System.out.println("💰 Balance: " + balanceValue);
                } catch (NoSuchFieldException e) {
                    System.out.println("❌ Pole 'balance' nie istnieje.");
                } catch (IllegalAccessException e) {
                    System.out.println("❌ Brak dostępu do pola 'balance'.");
                }
                break;
            case "2":
                try {
                    // Pobranie klasy obiektu bank
                    Class<?> bankClass = bank.getClass();

                    // Pobranie pola "balance"
                    Field balanceField = bankClass.getDeclaredField("balance");

                    // Ustawienie dostępu do prywatnego pola
                    balanceField.setAccessible(true);

                    // Odczytanie wartości pola "balance" z instancji obiektu bank
                    Object balanceValue = balanceField.get(bank);
                    balanceField.set(bank, 12);

                    System.out.println("💰 Balance: " + balanceValue);
                } catch (NoSuchFieldException e) {
                    System.out.println("❌ Pole 'balance' nie istnieje.");
                } catch (IllegalAccessException e) {
                    System.out.println("❌ Brak dostępu do pola 'balance'.");
                }
                break;

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


    public String getNormalizedChoice(String input) {
        String normalized = input.strip().replace(".", "").toLowerCase();
        switch (normalized) {
            case "1":
                return "1";
            case "2":
                return "2";
            case "3":
                return "3";
            case "4":
                return "4";
            case "5":
                return "5";
            case "0":
                return "0";
            default:
                return ""; // W przypadku nieprawidłowego wyboru
        }


    }

    private String getFieldName(String choice) {
        switch (choice) {
            case "1":
                return "SALDO";
            case "2":
                return "NAZWA UŻYTKOWNIKA";
            case "3":
                return "DATA UTWORZENIA";
            case "4":
                return "HASŁO";
            case "5":
                return "STATUS AKTYWNOŚCI";
            default:
                return "NIEZNANE";
        }
    }

    private String getFieldName2(String choice) {
        switch (choice) {
            case "1":
                return "informacje o klasie ";
            case "2":
                return "Konstruktory";
            case "3":
                return "Annotacje";
            case "4":
                return "Dostep do danych ";
            case "5":
                return "wszystkie informacje o dabych ";
            default:
                return "NIEZNANE";
        }
    }
}
