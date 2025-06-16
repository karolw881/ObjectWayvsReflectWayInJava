package org.magisterium.Classes.LolScanner;

import org.fusesource.jansi.Ansi;
import org.magisterium.Classes.Banks.Bank;
import org.magisterium.Classes.ObjectWay.ObjectAccessHandler;
import org.magisterium.Classes.ReflectWay.ReflectionAccessHandlerChild;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class MyScanner implements IMyScanner {
    private final Scanner scanner;
    private String[] EPIC_SLOGANS = {
            "Jakie podejście wybierzesz?",
            "Czy dobra drogę obierzesz?",
            "Zatwierdź, Enterem",
            "Cofniesz sie Zerem",
            "1. .2  ((((0  -- decyzje podejmujesz",
            "((1  1.) (1.))  ()()1 --  sie nie przejmujesz",
            " ...12..1. 22  35....   --  teraz odnowa probujesz" ,
            "Nieprawidłowy wybór.",
            "Teraz popatrz ",
            "Spróbuj ponownie." ,
            "Znowu rozpatrz",
            "Jakiego podejścia szukasz?",
            "Ktorej drogi poszukasz?",
            "Refleksje obierzesz?" ,
            "Obiektowa wybierzesz?",
    };

    private final Bank bank;
    private final ObjectAccessHandler objectAccessHandler;
    private final ReflectionAccessHandlerChild reflectionAccessHandlerChild;

    public MyScanner(InputStream in) {
        this.scanner = new Scanner(in);
        this.bank = new Bank(1000, "janek", "tajneHaslo123");
        this.objectAccessHandler = new ObjectAccessHandler(bank);
        this.reflectionAccessHandlerChild = new ReflectionAccessHandlerChild(bank);
    }

    public void run() {
        try {
            // Sprawdź czy System.in jest dostępny
            if (System.in.available() == -1) {
                return;
            }

            while (true) {
                try {
                    String choice = getNormalizedChoice(displayMainMenu());

                    if ("1".equals(choice)) {
                        objectAccessHandler.handleAccess();
                    } else if ("2".equals(choice)) {
                        reflectionAccessHandlerChild.handleAccess();
                    } else if ("0".equals(choice)) {
                        System.out.println("Zakończono.");
                        return;
                    } else {
                        System.out.println(
                                Ansi.ansi()
                                        .fg(Ansi.Color.RED)
                                        .bold()
                                        .a("Nieprawidłowy wybór. Spróbuj ponownie.")
                                        .reset()
                                        .toString()
                        );
                    }
                } catch (Exception e) {
                    // Cichy exit - po prostu kończymy
                    return;
                }
            }
        } catch (IOException e) {
            System.exit(0);
        } catch (Exception e) {
            System.exit(0);
        } finally {
            // Cicho zamknij scanner
            try {
                if (scanner != null) {
                    scanner.close();
                }
            } catch (Exception e) {
                // Ignoruj błędy przy zamykaniu
            }
        }
    }

    private String displayMainMenu() {
        try {
            // Random epic slogan
            int index = 1; // Numeracja zaczyna się od 1
            for (String epicSlogan : EPIC_SLOGANS) {
                System.out.println(
                        Ansi.ansi()
                                .fg(Ansi.Color.MAGENTA)
                                .bold()
                                .a(index + " " + epicSlogan + " ")
                                .reset().toString()
                );
                index++;
            }
            System.out.println(
                    Ansi.ansi()
                            .fg(Ansi.Color.GREEN)
                            .bold()
                            .a("\nMENU GŁÓWNE")
                            .reset().toString()
            );

            // Colorful, styled menu items
            System.out.println(
                    Ansi.ansi().fg(Ansi.Color.YELLOW).a("1. Obiektowa ").reset().toString() +
                            "  " +
                            Ansi.ansi().fg(Ansi.Color.CYAN).a("[Klasyczny atak!]").reset().toString()
            );
            System.out.println(
                    Ansi.ansi().fg(Ansi.Color.BLUE).a("2. Refleksyjna ").reset().toString() +
                            "  " +
                            Ansi.ansi().fg(Ansi.Color.RED).a("[Przemyśl swój ruch!]").reset().toString()
            );
            System.out.println(
                    Ansi.ansi().fg(Ansi.Color.RED).a("0. Zakończ ").reset().toString() +
                            "  " +
                            Ansi.ansi().fg(Ansi.Color.WHITE).a("[Do zobaczenia, wojowniku kodu!]").reset().toString()
            );

            System.out.print(
                    Ansi.ansi()
                            .fg(Ansi.Color.CYAN)
                            .bold()
                            .a("\nTwój wybór: ")
                            .reset().toString()
            );

            // Sprawdź czy scanner ma dostępne dane przed próbą odczytu
            if (!scanner.hasNextLine()) {
                return "0"; // Cichy exit
            }

            return scanner.nextLine();

        } catch (Exception e) {
            return "0"; // Cichy exit
        }
    }

    public String Normalize(String input) {
        if (input == null) {
            return "";
        }

        try {
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
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * Normalizuje wybór użytkownika do standardowego formatu
     * @param input String zawierający wybór użytkownika (może zawierać kropkę i nawiasy)
     * @return znormalizowany string ("1", "2", "0" lub "" dla błędnego wyboru)
     */
    public String getNormalizedChoice(String input) {
        if (input == null) {
            return "";
        }

        try {
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

            switch (forSwitch) {
                case "1":
                case "obiektowa":
                    return "1";
                case "2":
                case "refleksyjna":
                    return "2";
                case "0":
                case "zakończ":
                case "zakonicz":
                    return "0";
                default:
                    return "";
            }
        } catch (Exception e) {
            return "";
        }
    }

    // Metoda pomocnicza do sprawdzania dostępności Scanner
    public boolean isScannerAvailable() {
        try {
            return scanner != null && scanner.hasNextLine();
        } catch (Exception e) {
            return false;
        }
    }

    // Metoda do bezpiecznego odczytu linii
    public String safeNextLine() {
        try {
            if (scanner != null && scanner.hasNextLine()) {
                return scanner.nextLine();
            } else {
                return "";
            }
        } catch (Exception e) {
            return "";
        }
    }
}