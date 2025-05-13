package org.magisterium.Classes.ReflectWay;

import java.util.Scanner;

/**
 * Interfejs definiujący operacje dla klasy ReflectionAccessHandlerChild
 * Umożliwia obsługę mechanizmów refleksji dla obiektów typu Bank
 */
interface IReflectionAccesHandler {

    /**
     * Normalizuje wybór użytkownika, usuwając zbędne znaki i konwertując na formę standardową
     * @param input Wprowadzony wybór przez użytkownika
     * @return Znormalizowany wybór
     */
    String getNormalizedChoice(String input);

    /**
     * Główna metoda obsługująca dostęp refleksyjny do komponentów obiektu
     */
    void handleAccess();

    /**
     * Wyświetla informacje o publicznych konstruktorach
     */
    void showPublicConstructorsInfo();

    /**
     * Wyświetla informacje o wszystkich konstruktorach zadeklarowanych w klasie
     */
    void showDeclaredConstructorsInfo();

    /**
     * Obsługuje dostęp do salda konta
     * @param scanner Obiekt Scanner do odczytu danych z konsoli
     */
    void handleBalanceAccess(Scanner scanner);

    /**
     * Obsługuje dostęp do nazwy użytkownika
     * @param scanner Obiekt Scanner do odczytu danych z konsoli
     */
    void handleUsernameAccess(Scanner scanner);

    /**
     * Obsługuje dostęp do hasła konta
     * @param scanner Obiekt Scanner do odczytu danych z konsoli
     */
    void handlePasswordAccess(Scanner scanner);

    /**
     * Obsługuje dostęp do daty utworzenia konta
     * @param scanner Obiekt Scanner do odczytu danych z konsoli
     */
    void handleAccountCreationDateAccess(Scanner scanner);

    /**
     * Modyfikuje datę utworzenia konta
     * @param scanner Obiekt Scanner do odczytu danych z konsoli
     */
    void handleDateAccessSet(Scanner scanner);

    /**
     * Obsługuje wybór adnotacji
     * @param scanner Obiekt Scanner do odczytu danych z konsoli
     */
    void handleAnnotationChoice(Scanner scanner);

    /**
     * Obsługuje wybór metod
     * @param scanner Obiekt Scanner do odczytu danych z konsoli
     */
    void handleMethodsChoice2(Scanner scanner);

    /**
     * Obsługuje wybór pól
     * @param scanner Obiekt Scanner do odczytu danych z konsoli
     */
    void handleFieldChoice(Scanner scanner);

    /**
     * Wyświetla wszystkie pola
     */
    void showAllFields();

    /**
     * Wyświetla pola zadeklarowane w klasie
     */
    void showDeclaredFields();

    /**
     * Wyświetla wszystkie metody
     */
    void showAllMethods();

    /**
     * Wyświetla metody zadeklarowane w klasie
     */
    void showDeclaredMethods();

    /**
     * Wyświetla informacje o wszystkich adnotacjach
     */
    void displayAllAnnotationsInfo();

    /**
     * Wyświetla informacje o adnotacjach zadeklarowanych w klasie
     */
    void displayDeclaredAnnotationsInfo();
}