package org.magisterium.Classes.LolScanner;

import org.fusesource.jansi.Ansi;
import org.magisterium.Classes.Banks.Bank;
import org.magisterium.Classes.ObjectWay.ObjectAccessHandler;
import org.magisterium.Classes.ReflectWay.ReflectionAccessHandler;
import org.magisterium.Classes.ReflectWay.ReflectionAccessHandlerChild;

import java.io.InputStream;
import java.util.Scanner;

public class MyScanner {
    private final Scanner scanner;
    private String[] EPIC_SLOGANS = {
            "Hakuj jak prawdziwy władca kodu!",
            "Programowanie to Twoje królestwo!",
            "Zatwierdz Enterem",
            "Cofnij Zerem",
            "1 , 1. , 2. , 2.  , (1) , (1.)   Dozwolone",
            "Złam system, nie zasady!",

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
        while (true) {


            for (int i = 0; i < EPIC_SLOGANS.length; i++) {
                System.out.println(EPIC_SLOGANS[i]);
            }


            String choice = getNormalizedChoice(displayMainMenu());


            if ("1".equals(choice)) {
                objectAccessHandler.handleAccess();
            } else if ("2".equals(choice)) {
                reflectionAccessHandlerChild.handleAccess();
            } else if ("0".equals(choice)) {
                System.out.println("Zakończono.");
                return;
            } else {
                System.out.println("Nieprawidłowy wybór.");
            }
        }
    }




        private String displayMainMenu() {
            // Random epic slogan
            String epicSlogan = EPIC_SLOGANS[(int)(Math.random() * EPIC_SLOGANS.length)];

            // ANSI color and styling
            System.out.println(
                    Ansi.ansi()
                            .fg(Ansi.Color.MAGENTA)
                            .bold()
                            .a("🔥 " + epicSlogan + " 🔥")
                            .reset().toString()
            );

            System.out.println(
                    Ansi.ansi()
                            .fg(Ansi.Color.GREEN)
                            .bold()
                            .a("\n==== MENU GŁÓWNE ====")
                            .reset().toString()
            );

            // Colorful, styled menu items
            System.out.println(
                    Ansi.ansi().fg(Ansi.Color.YELLOW).a("1. Obiektowa 💻").reset().toString() +
                            "  " +
                            Ansi.ansi().fg(Ansi.Color.CYAN).a("[Klasyczny atak!]").reset().toString()
            );
            System.out.println(
                    Ansi.ansi().fg(Ansi.Color.BLUE).a("2. Refleksyjna 🕵️").reset().toString() +
                            "  " +
                            Ansi.ansi().fg(Ansi.Color.RED).a("[Przemyśl swój ruch!]").reset().toString()
            );
            System.out.println(
                    Ansi.ansi().fg(Ansi.Color.RED).a("0. Zakończ ❌").reset().toString() +
                            "  " +
                            Ansi.ansi().fg(Ansi.Color.WHITE).a("[Do zobaczenia, wojowniku kodu!]").reset().toString()
            );

            System.out.print(
                    Ansi.ansi()
                            .fg(Ansi.Color.CYAN)
                            .bold()
                            .a("\n🎯 Twój wybór: ")
                            .reset().toString()
            );

            return scanner.nextLine();
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
    }}