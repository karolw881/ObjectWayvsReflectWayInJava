package org.magisterium.Classes.ReflectWay;

import org.magisterium.Annotations.BankInfo;
import org.magisterium.Classes.Banks.Bank;


import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
@BankInfo(description = " Parent class of banks ")
public class ReflectionAccessHandler {
    protected final Bank bank;


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


            // wysswietl annotacje

            Annotation[] annotations = bankClass.getAnnotations();


            for(Annotation annotation : annotations){
                if(annotation instanceof BankInfo){
                    BankInfo myAnnotation = (BankInfo) annotation;
                    System.out.println("name: " + myAnnotation.description());
                    System.out.println("value: " + myAnnotation);

                }
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
        String accessModifier;

        int modifiers = field.getModifiers();
        if (Modifier.isPublic(modifiers)) {
            accessModifier = "Public";
        } else if (Modifier.isProtected(modifiers)) {
            accessModifier = "Protected";
        } else if (Modifier.isPrivate(modifiers)) {
            accessModifier = "Private";
        } else {
            accessModifier = "Package-Private"; // Domyślny dostęp
        }

        System.out.printf(
                "Pole: %-20s Wartość: %-20s Typ: %-10s Dostęp: %s%n",
                field.getName(),
                value != null ? value : "null",
                field.getType().getSimpleName(),
                accessModifier
        );
    }


    private void chooseAllInformationOfAnnotation() {
        Class<?> clazz = bank.getClass();

        // Sprawdzenie, czy metoda ma adnotację @BankInfo
        if (clazz.isAnnotationPresent(BankInfo.class)) {
            try {
                BankInfo annotation = clazz.getAnnotation(BankInfo.class);

                // Wyświetlenie informacji z adnotacji
                System.out.println("=== Informacje o Banku ===");
                System.out.println("Nazwa: " + annotation.name());
                System.out.println("Opis: " + annotation.description());
                System.out.println("Siedziba: " + annotation.headquarters());
                System.out.println("Kapitał: " + annotation.capital());
                System.out.println("O nas: " + annotation.aboutUs());
                System.out.println("==========================");
            } catch (Exception e) {
                System.out.println("Error executing method " + clazz.getName() + ": " + e.getMessage());
            }
        }

    }
}