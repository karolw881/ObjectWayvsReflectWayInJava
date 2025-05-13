package org.magisterium.Classes.ObjectWay;

import org.magisterium.Classes.Banks.Bank;

import java.util.Scanner;
import java.util.Map;

/**
 * Interfejs do obsługi dostępu do obiektów w systemie bankowym.
 * Definiuje metody do dostępu i manipulacji polami obiektów bankowych
 * poprzez konsolowy interfejs oparty na menu.
 */
 interface IObjectAccessHandler {

    /**
     * Wyświetla główne menu wyboru pól i obsługuje nawigację użytkownika
     * przez system dostępu do obiektów.
     */
    void handleAccess();

    /**
     * Obsługuje dostęp do konkretnego pola na podstawie wyboru użytkownika.
     *
     * @param fieldChoice Wybór pola przez użytkownika
     * @param scanner Scanner do odczytu danych wejściowych użytkownika
     */
    void handleFieldAccess(String fieldChoice, Scanner scanner);

    /**
     * Obsługuje dostęp do pola salda.
     *
     * @param accessChoice Wybór rodzaju dostępu
     * @param scanner      Scanner do odczytu danych wejściowych użytkownika
     */
    private void handleBalanceAccess(String accessChoice, Scanner scanner) {

    }

    /**
     * Wyświetla komunikat w kolorze zielonym.
     *
     * @param string Tekst do wyświetlenia
     */
    private void dispplayAnsiMethodGreen(String string) {

    }

    /**
     * Wyświetla komunikat w kolorze czerwonym.
     *
     * @param string Tekst do wyświetlenia
     */
    private void dispplayAnsiMethodRed(String string) {

    }

    /**
     * Wyświetla menu dostępu do pola.
     *
     * @param fieldChoice Wybór pola
     * @param scanner     Scanner do odczytu danych wejściowych użytkownika
     * @return Wybór użytkownika
     */
    private String displayFieldAccessMenu(String fieldChoice, Scanner scanner) {
        return null;
    }

    /**
     * Wyświetla główne menu pól.
     *
     * @param scanner Scanner do odczytu danych wejściowych użytkownika
     * @return Wybór użytkownika
     */
    private String displayFieldMenu(Scanner scanner) {
        return null;
    }

    /**
     * Normalizuje dane wejściowe użytkownika.
     *
     * @param input Dane wejściowe do normalizacji
     * @return Znormalizowany ciąg znaków
     */
    String Normalize(String input);

    /**
     * Normalizuje wybór użytkownika do standardowego formatu.
     *
     * @param input Wybór użytkownika do normalizacji
     * @return Znormalizowany wybór
     */
    String getNormalizedChoice(String input);

    /**
     * Obsługuje dostęp do pola nazwy użytkownika.
     *
     * @param accessChoice Wybór rodzaju dostępu
     * @param scanner      Scanner do odczytu danych wejściowych użytkownika
     */
    private void handleUsernameAccess(String accessChoice, Scanner scanner) {

    }

    /**
     * Obsługuje dostęp do pola statusu aktywności.
     *
     * @param accessChoice Wybór rodzaju dostępu
     * @param scanner Scanner do odczytu danych wejściowych użytkownika
     */
   private void handleActivityStatusAccess(String accessChoice, Scanner scanner){}

    /**
     * Ustawia status aktywności konta.
     *
     * @param scanner Scanner do odczytu danych wejściowych użytkownika
     */
    private void setIsActive(Scanner scanner){}

    /**
     * Obsługuje dostęp do pola daty utworzenia konta.
     *
     * @param accessChoice Wybór rodzaju dostępu
     */
   private void handleAccountCreationDateAccess(String accessChoice){}

    /**
     * Obsługuje dostęp do pola hasła.
     *
     * @param accessChoice Wybór rodzaju dostępu
     * @param scanner Scanner do odczytu danych wejściowych użytkownika
     */
   private void handlePasswordAccess(String accessChoice, Scanner scanner){}

    /**
     * Pobiera nazwę pola na podstawie wyboru użytkownika.
     *
     * @param choice Wybór użytkownika
     * @return Nazwa wybranego pola
     */
   private  String getFieldName(String choice){
       return choice;
   }
}