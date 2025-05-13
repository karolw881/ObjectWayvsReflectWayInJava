package org.magisterium.Classes.ReflectWay;

import java.util.Scanner;

/**
 * Interfejs definiujący metody do wyświetlania menu i komunikatów
 * w aplikacji konsolowej, używając kolorów ANSI.
 */
public interface IMenuPrint {

    /**
     * Wyświetla główne menu wyboru pól i zwraca wybór użytkownika.
     *
     * @param scanner Scanner do odczytu danych wejściowych użytkownika
     * @return Wybór użytkownika jako ciąg znaków
     */
    String displayFieldMenu(Scanner scanner);

    /**
     * Wyświetla tekst w kolorze zielonym.
     *
     * @param string Tekst do wyświetlenia
     */
    void dispplayAnsiMethodGreen(String string);

    /**
     * Wyświetla tekst w kolorze żółtym.
     *
     * @param string Tekst do wyświetlenia
     */
    void dispplayAnsiMethodYellow(String string);

    /**
     * Wyświetla tekst w kolorze niebieskim.
     *
     * @param string Tekst do wyświetlenia
     */
    void dispplayAnsiMethodBlue(String string);

    /**
     * Wyświetla tekst w kolorze magenta.
     *
     * @param string Tekst do wyświetlenia
     */
    void dispplayAnsiMethodMagenta(String string);

    /**
     * Wyświetla tekst w kolorze białym.
     *
     * @param string Tekst do wyświetlenia
     */
    void dispplayAnsiMethodWhite(String string);

    /**
     * Wyświetla tekst w kolorze czerwonym.
     *
     * @param string Tekst do wyświetlenia
     */
    void dispplayAnsiMethodRed(String string);

    /**
     * Wyświetla menu wyboru typów metod.
     */
    void showMenuMethods2();

    /**
     * Wyświetla menu wyboru typów pól.
     */
    void showMenuFields();

    /**
     * Wyświetla menu wyboru typów konstruktorów.
     */
    void showMenuConstructor();

    /**
     * Wyświetla menu wyboru typów adnotacji.
     */
    void showMenu();

    /**
     * Wyświetla menu zarządzania aktywacją.
     */
    void showMenuActive();

    /**
     * Wyświetla menu zarządzania saldem.
     */
    void showMenuBalance();

    /**
     * Wyświetla menu zarządzania hasłem.
     */
    void showMenuPassword();

    /**
     * Wyświetla menu zarządzania nazwą użytkownika.
     */
    void showMenuUsername();

    /**
     * Wyświetla menu zarządzania dostępem do daty.
     */
    void showMenuDataAcces();

    /**
     * Wyświetla komunikat o powrocie do menu głównego.
     */
    void menuMainReturn();

    /**
     * Wyświetla komunikat o powrocie do poprzedniego menu.
     */
    void menuBackReturn();

    /**
     * Wyświetla komunikat o błędnym wyborze.
     */
    void wrongChoose();

    /**
     * Wyświetla dostępne pola danych z odpowiednim formatowaniem.
     */
    void DisplayDataFields();
}