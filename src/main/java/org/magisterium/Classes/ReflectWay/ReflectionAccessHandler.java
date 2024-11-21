package org.magisterium.Classes.ReflectWay;

import org.magisterium.Classes.Banks.Bank;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class ReflectionAccessHandler {
    private final Bank bank;

    public ReflectionAccessHandler(Bank bank) {
        this.bank = bank;
    }

    public void handleAccess() {
        System.out.println("\nDostęp refleksyjny do klasy Bank:");

        try {
            Class<?> bankClass = bank.getClass();

            // Wyświetlanie informacji o klasie
            System.out.println("Nazwa klasy: " + bankClass.getName());
            System.out.println("Modyfikatory dostępu: " + Modifier.toString(bankClass.getModifiers()));

            // Uzyskanie wszystkich konstruktorów
            Constructor<?>[] constructors = bankClass.getDeclaredConstructors();
            System.out.println("\nKonstruktory:");
            for (Constructor<?> constructor : constructors) {
                System.out.println(constructor);
            }

            // Dostęp do pól za pomocą refleksji
            Field[] fields = bankClass.getDeclaredFields();
            System.out.println("\nPola:");
            for (Field field : fields) {
                field.setAccessible(true); // Umożliwia dostęp do prywatnego pola
                Object value = field.get(bank);
                printFieldDetails(field, value);
            }

        } catch (Exception e) {
            System.err.println("Błąd podczas dostępu do pól: " + e.getMessage());
        }
    }

    private void printFieldDetails(Field field, Object value) {
        boolean isPrivate = !field.canAccess(bank);
        System.out.printf(
                "Pole: %-20s Wartość: %-20s Typ: %-10s Dostęp: %s%n",
                field.getName(),
                value != null ? value : "null",
                field.getType().getSimpleName(),
                isPrivate ? "Access Denied" : "Public"
        );
    }
}