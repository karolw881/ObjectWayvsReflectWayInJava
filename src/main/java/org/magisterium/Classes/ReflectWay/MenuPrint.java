package org.magisterium.Classes.ReflectWay;

import org.fusesource.jansi.Ansi;

import java.util.Map;
import java.util.Scanner;

import static org.magisterium.Classes.ReflectWay.MenuConstants.*;

public class MenuPrint {
    private static final Map<String, String> FIELD_ICONS = createFieldIcons();
    static String displayFieldMenu(Scanner scanner) {
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
                        .a("Wersja Refleksyjna - Menu Główne\n")
                        .reset().toString()
        );



        // Opcje menu z kolorami i ikonami
        System.out.println(
                Ansi.ansi()
                        .fg(Ansi.Color.GREEN)
                        .a(FIELD_ICONS2.get("wszystkie") + "1.Dostep do danych ")
                        .reset().toString() +
                        Ansi.ansi()
                                .fg(Ansi.Color.BLUE)
                                .a(" [wszystko co potrzeba]")
                                .reset().toString()
        );
        System.out.println(
                Ansi.ansi()
                        .fg(Ansi.Color.YELLOW)
                        .a(FIELD_ICONS2.get("konstruktory") + "2. Konstruktory")
                        .reset().toString() +
                        Ansi.ansi()
                                .fg(Ansi.Color.BLUE)
                                .a(" [Dane o inicjalizacjach]")
                                .reset().toString()
        );

        System.out.println(
                Ansi.ansi()
                        .fg(Ansi.Color.CYAN)
                        .a(FIELD_ICONS2.get("annotacje") + "3. Annotacje ")
                        .reset().toString() +
                        Ansi.ansi()
                                .fg(Ansi.Color.BLUE)
                                .a(" [Dane Annotacyjne]")
                                .reset().toString()
        );




        // Opcja powrotu z efektem
        System.out.println(
                Ansi.ansi()
                        .fg(Ansi.Color.RED)
                        .bold()
                        .a(" ↩ 0. Powrót do menu głównego")
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

    static void showMenu(){
        System.out.println("\nWybierz typ annotacji:");
        System.out.println("1. Bez declared");
        System.out.println("2. Z declared");
        System.out.println("0. Powrót");
    }


    static void showMenuActive(){
        System.out.println("1. Odczytaj 'czy aktywny'");
        System.out.println("2. Ustaw 'czy aktywny '");
        System.out.println("0. Powrót");

        System.out.print("Wybierz opcję: ");
    }


    static void showMenuBalance(){
        System.out.println("1. Odczytaj 'balance'");
        System.out.println("2. Ustaw 'balance'");
        System.out.println("0. Powrót");

        System.out.print("Wybierz opcję: ");
    }


    static void showMenuPassword(){
        System.out.println("1. Odczytaj 'password'");
        System.out.println("2. Ustaw 'password'");
        System.out.println("0. Powrót");

        System.out.print("Wybierz opcję: ");
    }


    static void showMenuUsername(){
        System.out.println("1. Odczytaj 'username'");
        System.out.println("2. Ustaw 'username'");
        System.out.println("0. Powrót");

        System.out.print("Wybierz opcję: ");
    }

    static void showMenuDataAcces(){
        System.out.println("1. Odczytaj 'date utworzenie '");
        System.out.println("2. Ustaw 'date utworzenia '");
        System.out.println("0. Powrót");

        System.out.print("Wybierz opcję: ");
    }

    static void menuMainReturn(){
        System.out.println(
                Ansi.ansi()
                        .fg(Ansi.Color.WHITE.value())
                        .bold()
                        .a("Powrót do menu głównego.")
                        .reset().toString()
        );

    }

    static void menuBackReturn(){
        System.out.println(
                Ansi.ansi()
                        .fg(Ansi.Color.WHITE.value())
                        .bold()
                        .a(  "\nPowrót do wcześniejszego menu.")
                        .reset().toString()
        );

    }


    static void wrongChoose(){
        System.out.println(
                Ansi.ansi()
                        .fg(Ansi.Color.RED.value())
                        .bold()
                        .a(  "\nNieprawidłowy wybór. Spróbuj ponownie.")
                        .reset().toString()
        );

    }


    static void DisplayDataFields() {
        System.out.println(Ansi.ansi()
                .fg(Ansi.Color.CYAN)
                .a("\n⚡⚡⚡ Wybrano: Dostęp do danych ⚡⚡⚡\n"));

        // Tablica kolorów do wyświetlania
        Ansi.Color[] colors = {
                Ansi.Color.RED,
                Ansi.Color.GREEN,
                Ansi.Color.YELLOW,
                Ansi.Color.BLUE,
                Ansi.Color.MAGENTA,
                Ansi.Color.CYAN,
                Ansi.Color.WHITE
        };

        // Jeśli FIELD_ICONS to LinkedHashMap, kolejność wstawiania jest zachowana
        final int[] colorIndex = {0}; // Indeks koloru do użycia
        FIELD_ICONS.forEach((key, value) -> {
            System.out.println(
                    Ansi.ansi()
                            .fg(colors[colorIndex[0]]) // Ustaw kolor
                            .a(value + " " + key)
                            .reset() // Przywróć domyślne ustawienia koloru
                            .toString()
            );
            colorIndex[0] = (colorIndex[0] + 1) % colors.length; // Przejdź do następnego koloru, zwróć się na początek, jeśli koniec
        });

        System.out.print(
                Ansi.ansi()
                        .fg(Ansi.Color.CYAN)
                        .bold()
                        .a("\nWybierz pole z powyższej listy: ")
                        .reset().toString()
        );
    }


}
