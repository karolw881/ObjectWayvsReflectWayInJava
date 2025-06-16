package org.magisterium.Classes.ObjectWay;

import lombok.Getter;
import lombok.Setter;
import org.fusesource.jansi.Ansi;
import org.magisterium.Classes.Banks.Bank;
import org.magisterium.Classes.LolScanner.MyScanner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Map;
import java.util.Scanner;
@Getter
@Setter
public class ObjectAccessHandler  {
    private final Bank bank;
    private final String[] DATA_ACCESS_QUOTES = {
            "...",
            "Panel juz aktywowany...",
            "Wybierz cel dzialania...",
            "Przygotuj się do introspekcji..."
    };

    private final Map<String, String> FIELD_ICONS = Map.of(
            "saldo", "Ob",
            "nazwa", "je",
            "data", "ct",
            "hasło", "i",
            "status", "ve"
    );

    public ObjectAccessHandler(Bank bank) {
        this.bank = bank;
    }

    public void handleAccess() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String fieldChoice = getNormalizedChoice(displayFieldMenu(scanner));
            if ("0".equals(fieldChoice)) {
                System.out.println(
                        Ansi.ansi()
                                .fg(Ansi.Color.GREEN)
                                .bold()
                                .a("\"Powrót do menu głównego.\"")
                                .reset().toString()
                );
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
                default:
                    System.out.println(
                            Ansi.ansi()
                                    .fg(Ansi.Color.RED)
                                    .bold()
                                    .a("Nieprawidłowy wybór pola. Spróbuj ponownie.")
                                    .reset()
                                    .toString()
                    );
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
                                .a("  ACCESS DENIED  ")
                                .reset()
                                .toString()
                );
                break;
            case "2":
                System.out.println(
                        Ansi.ansi()
                                .fg(Ansi.Color.GREEN)
                                .bold()
                                .a(bank.getBalance())
                                .reset()
                                .toString()
                );
                break;
            case "3":
                System.out.println(
                        Ansi.ansi()
                                .fg(Ansi.Color.GREEN)
                                .bold()
                                .a("Podaj nowe saldo : ")
                                .reset()
                                .toString()
                );
                try {
                    double newBalance = Double.parseDouble(scanner.nextLine());
                    bank.setBalance(newBalance);
                    System.out.println(
                            Ansi.ansi()
                                    .fg(Ansi.Color.GREEN)
                                    .bold()
                                    .a("Saldo zostało zaktualizowane.")
                                    .reset()
                                    .toString()
                    );
                } catch (NumberFormatException e) {
                    dispplayAnsiMethodRed(" Nieprawidłowy format kwoty.");
                }
                break;
            default:
                System.out.println(
                        Ansi.ansi()
                                .fg(Ansi.Color.RED)
                                .bold()
                                .a(" Nieprawidłowy wybór. Spróbuj ponownie.")
                                .reset()
                                .toString()
                );
                break;
        }
    }


    /**
     * @param string
     */
    private void dispplayAnsiMethodGreen(String string) {
        System.out.println(
                Ansi.ansi()
                        .fg(Ansi.Color.GREEN)
                        .bold()
                        .a(string)
                        .reset().toString());

    }

    private void dispplayAnsiMethodRed(String string) {
        System.out.println(
                Ansi.ansi()
                        .fg(Ansi.Color.RED)
                        .bold()
                        .a(string)
                        .reset().toString());

    }

    private String displayFieldAccessMenu(String fieldChoice, Scanner scanner) {
        String fieldName = getFieldName(fieldChoice);
        if(fieldName.equals("Nieznane")) {


            return "0";
        }else {


            System.out.println(
                    Ansi.ansi()
                            .fg(Ansi.Color.MAGENTA)
                            .bold()
                            .a("\nDOSTĘP DO POLA: " + fieldName + "\n")
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
    }


    private String displayFieldMenu(Scanner scanner) {
        // Losowy cytat
        String randomQuote = DATA_ACCESS_QUOTES[(int) (Math.random() * DATA_ACCESS_QUOTES.length)];

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



    public String Normalize(String input) {
        if (input == null) {
            return "";
        }

        // Przygotuj string do normalizacji
        String trimmed = input.strip();

        // Usuń nawiasy i spacje, zachowując kropkę na końcu jeśli istnieje
        String withoutBrackets = trimmed
                .replace("(", "")
                .replace(")", "")
                .toLowerCase();

        // Usuń kropkę tylko jeśli nie jest ostatnim znakiem
        String normalized = withoutBrackets;
        if (!withoutBrackets.endsWith(".")) {
            normalized = withoutBrackets.replace(".", "");
        }

        // Usuń kropkę do porównania w switch
        String forSwitch = normalized.replace(".", "");

        return forSwitch;
    }


    public String getNormalizedChoice(String input) {
        String normalized = Normalize(input);
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


    private void handleUsernameAccess(String accessChoice, Scanner scanner) {
        switch (accessChoice) {
            case "1":
                // bank.username;
                System.out.println(
                        Ansi.ansi()
                                .fg(Ansi.Color.RED)
                                .bold()
                                .a(" \n  ACCESS DENIED  ")
                                .reset().toString()
                );
                break;
            case "2":
                dispplayAnsiMethodGreen("👤 Nazwa użytkownika: " + bank.getUsername());
                break;
            case "3":
                dispplayAnsiMethodGreen("Podaj nową nazwę użytkownika: ");
                String newUsername = scanner.nextLine();
                bank.setUsername(newUsername);
                dispplayAnsiMethodGreen(" Nazwa użytkownika została zaktualizowana.");
                break;
            default:
                System.out.println(
                        Ansi.ansi()
                                .fg(Ansi.Color.RED)
                                .bold()
                                .a(" Nieprawidłowy wybór. Spróbuj ponownie.")
                                .reset()
                                .toString()
                );
                break;
        }
    }

    private void handleActivityStatusAccess(String accessChoice, Scanner scanner) {
        switch (accessChoice) {
            case "0":
                dispplayAnsiMethodGreen("Powrót do menu głównego.");
                break;
            case "1":
                System.out.println(
                        Ansi.ansi()
                                .fg(Ansi.Color.RED)
                                .bold()
                                .a("  ACCESS DENIED ")
                                .reset().toString()
                );
                break;
            case "2":
                dispplayAnsiMethodGreen("⚡ Status aktywności: " + bank.isActive());
                break;
            case "3":
            setIsActive(scanner);

                break;
            default:
                dispplayAnsiMethodRed("Nieprawidłowy wybór.");
                break;
        }
    }

    private void setIsActive(Scanner scanner) {
        dispplayAnsiMethodGreen("Podaj nowy status aktywności (true/false): ");
        String input = scanner.nextLine();
        // Walidacja – akceptujemy tylko "true" lub "false" (bez względu na wielkość liter)
        if (input.equals("true") || input.equals("false")) {
            boolean newStatus = Boolean.parseBoolean(input);
            //  System.out.println(newStatus);
            // rzutowanie z boolena na stringa !!!!
/// TYKO zmienic tego Sysemouta

            bank.setActive(newStatus);
            System.out.println(
                    Ansi.ansi()
                            .fg(Ansi.Color.GREEN)
                            .bold()
                            .a("Status aktywności został zaktualizowany.")
                            .reset().toString()
            );
        } else {
            System.out.println(
                    Ansi.ansi()
                            .fg(Ansi.Color.RED)
                            .bold()
                            .a(" Nieprawidłowy format statusu. Wprowadź 'true' lub 'false'.")
                            .reset().toString()
            );
        }
    }



    private void handleAccountCreationDateAccess(String accessChoice) {
        switch (accessChoice) {
            case "1":
                System.out.println(
                        Ansi.ansi()
                                .fg(Ansi.Color.RED)
                                .bold()
                                .a("Bezpośredni dostęp do daty utworzenia: ACCESS DENIED  ")
                                .reset().toString()
                );

                break;
            case "2":
                dispplayAnsiMethodGreen(" Data utworzenia konta: " + bank.getAccountCreationDate());
                break;
            case "3":
                Scanner scanner = new Scanner(System.in);
                dispplayAnsiMethodGreen("Podaj nową datę utworzenia konta (format: yyyy-MM-dd HH:mm): ");
                String input = scanner.nextLine();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                try {
                    LocalDateTime newDate = LocalDateTime.parse(input, formatter);
                    // Walidacja – data nie może być z przeszłości
                    if (newDate.isBefore(LocalDateTime.now())) {
                        System.out.println(
                                Ansi.ansi()
                                        .fg(Ansi.Color.RED)
                                        .bold()
                                        .a(" Nie można ustawić daty utworzenia na datę z przeszłości!")
                                        .reset().toString()
                        );
                    } else {
                        bank.setAccountCreationDate(newDate);
                        System.out.println(
                                Ansi.ansi()
                                        .fg(Ansi.Color.GREEN)
                                        .bold()
                                        .a(" Data utworzenia została zaktualizowana na: " + bank.getAccountCreationDate())
                                        .reset().toString()
                        );
                    }
                } catch (DateTimeParseException e) {
                    System.out.println(
                            Ansi.ansi()
                                    .fg(Ansi.Color.RED)
                                    .bold()
                                    .a(" Format daty jest niepoprawny!")
                                    .reset().toString()
                    );
                }
                break;
            default:
                System.out.println(
                        Ansi.ansi()
                                .fg(Ansi.Color.RED)
                                .bold()
                                .a("Nieprawidłowy wybór."));
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
                                .a(" ACCESS DENIED  ")
                                .reset().toString()
                );
                break;
            case "2":
                dispplayAnsiMethodGreen(bank.getPasswordHash());
                break;
            case "3":
                dispplayAnsiMethodGreen("Podaj nowe hasło: ");
                String newPassword = scanner.nextLine();
                bank.setPasswordHash(newPassword);
                System.out.println(
                        Ansi.ansi()
                                .fg(Ansi.Color.GREEN)
                                .bold()
                                .a("Hasło zostało zaktualizowane."));
                break;
            default:
                System.out.println(
                        Ansi.ansi()
                                .fg(Ansi.Color.RED)
                                .bold()
                                .a(" Nieprawidłowy wybór. Spróbuj ponownie.")
                                .reset()
                                .toString()
                );
                break;
        }
    }


    private String getFieldName(String choice) {
        choice = Normalize(choice);
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
                System.out.println(
                        Ansi.ansi()
                                .fg(Ansi.Color.RED)
                                .bold()
                                .a("Nieprawidłowy wybór. Spróbuj ponownie. ")
                                .reset().toString());


                return "Nieznane";
        }
    }
}