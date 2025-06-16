package org.magisterium.Classes;


import org.magisterium.Classes.LolScanner.MyScanner;
import java.io.*;


public class Main {


    public static void main(String[] args) {
        try {
            // Sprawdź czy System.in jest dostępny
            if (System.in.available() < 0) {
                System.out.println("Błąd: System.in nie jest dostępny!");
                System.exit(1);
            }

            // Uruchomienie skanera z obsługą błędów
            MyScanner scanner = new MyScanner(System.in);
            scanner.run();

        } catch (IOException e) {
            System.err.println("Błąd I/O podczas sprawdzania System.in: " + e.getMessage());
            System.exit(1);
        } catch (Exception e) {
            System.err.println("Nieoczekiwany błąd: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        } finally {
            // Zapewnienie, że program się zakończy prawidłowo
            System.out.println("Program zakończony.");
        }
    }
}


