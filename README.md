

#  Programowanie Obiektowe versus Programowanie Refleksyjne w Javie



## Opis

Projekt porónujący programowania obiektowego (OOP) i refleksji w języku Java. W ramach projektu przeprowadze implementacje kilku aplikacji. 

### Cel projektu

Celem projektu było zrozumienie i porównanie:
- Programowania obiektowego i jego zastosowań w różnych scenariuszach.
- Refleksja - wieksze mozliwosci ale wolniejsze dzialanie 
- Praktycznego wykorzystania zarówno OOP, jak i refleksji w tworzeniu aplikacji oraz testowaniu ich funkcji.

## Struktura Projektu

Projekt składa się z kilku kluczowych części:

### 1. **Implementacja programów w Javie**

W ramach tej części projektu zaimplementowano różne aplikacje w języku Java, które ilustrują działanie zarówno programowania obiektowego, jak i refleksji. Do głównych zadań należy:

- **Zadania z refleksją**: Programy wykorzystujące mechanizmy refleksji w Javie do manipulacji .
- **Zadanie obiektowe **:  typowe podejscie oop
 

### 3. **Testowanie jednostkowe w Javie z JUnit**

Wstepne male testowanie  z assercja  z wieloma przypadkami nastepnie 


- Użycie mocków do symulacji różnych scenariuszy wejściowych i analizowanie działania aplikacji w różnych warunkach.


## Wymagania

### Środowisko 

- **IntelliJ IDEA**  2023.3.3 (Ultimate Edition)
- Runtime version: 17.0.9+7-b1087.11 amd64 VM: OpenJDK 64-Bit Server VM by JetBrains s.r.o.

### Zależności

Poniżej znajduje się lista zależności używanych w projekcie wraz z ich wersjami:

- **jansi** (org.fusesource.jansi): `2.3.2`
  - Biblioteka do kolorowego wyjścia w konsoli.

- **Lombok** (org.projectlombok): `1.18.30`
  - Ułatwia pisanie kodu, automatyzując generowanie metod, takich jak gettery i settery. (Zakres: provided)

- **Mockito** (org.mockito): `5.0.0`
  - Biblioteka do tworzenia mocków w testach jednostkowych. (Zakres: test)

- **JUnit Jupiter API** (org.junit.jupiter): `5.8.2`
  - Framework do pisania testów jednostkowych. (Zakres: test)



### Instrukcje

Aby uruchomić projekt, upewnij się, że masz zainstalowane:
- Java Development Kit (JDK) w wersji 21.
- IntelliJ IDEA (lub inny IDE) skonfigurowany do pracy z projektem Maven.


### Narzędzia

- **Maven**: Narzędzie do zarządzania zależnościami i budowaniem projektów w Javie.
- **Git**: System kontroli wersji do zarządzania kodem.
![obraz](https://github.com/user-attachments/assets/d1c07b39-fb6a-4039-ab4a-b328cc2e28c4)﻿# OBjectvsReflect
![obraz](https://github.com/user-attachments/assets/28f81e08-cad3-4641-ba7f-cf52119ffc44)
![obraz](https://github.com/user-attachments/assets/62dc6ed0-d523-4e93-989b-dcd44ba24b19)
![obraz](https://github.com/user-attachments/assets/c781bbe3-bd2c-4201-9cd9-ff98655d8c4d)



ObjectvsReflect - Dokumentacja Rozwoju Projektu
Nowe Funkcjonalności i Usprawnienia
1. Rozszerzona Obsługa Wprowadzania Danych

    Dodano obsługę różnych formatów wprowadzania opcji:
        Standardowe numery ("1", "2", "0")
        Numery z kropką ("1.", "2.", "0.")
        Numery w nawiasach ("(1)", "(2)", "(0)")
        Kombinacje nawiasów i kropek ("(1.)", "(2.)")
        Nazwy tekstowe ("obiektowa", "refleksyjna", "zakończ")

2. Ulepszona Normalizacja Wejścia

public String getNormalizedChoice(String input) {
    if (input == null) return "";
    
    String trimmed = input.strip();
    String withoutBrackets = trimmed
            .replace("(", "")
            .replace(")", "")
            .toLowerCase();
    // ... reszta implementacji
}

3. Rozszerzone Testy Jednostkowe

Dodano kompleksowe testy pokrywające wszystkie przypadki użycia:

@Test
void testGetNormalizedChoiceWithMocks() {
    MyScanner myScanner = new MyScanner(System.in);
    
    // Podstawowe warianty
    assertEquals("1", myScanner.getNormalizedChoice("1"));
    assertEquals("1", myScanner.getNormalizedChoice("(1)"));
    assertEquals("1", myScanner.getNormalizedChoice("1."));
    // ... więcej testów
}

4. Dokumentacja Javadoc

Dodano szczegółową dokumentację kodu:

/**
 * Normalizuje wybór użytkownika do standardowego formatu
 * @param input String zawierający wybór użytkownika
 * @return znormalizowany string ("1", "2", "0" lub "" dla błędnego wyboru)
 */

Planowane Rozszerzenia
1. Refleksyjna Część Aplikacji

    Ciągła optymalizacja wydajności
    Dodanie nowych funkcjonalności refleksyjnych
    Implementacja zaawansowanych mechanizmów manipulacji obiektami

2. System Testów

    Rozbudowa testów jednostkowych
    Dodanie testów integracyjnych
    Implementacja testów wydajnościowych

Osiągnięcia i Wyzwania
Rozwiązane Problemy

    Poprawiono obsługę różnych formatów wejścia
    Zoptymalizowano logikę normalizacji
    Rozwiązano problem z testami akceptującymi różne formaty

Wyzwania

    Zachowanie kompatybilności wstecznej
    Optymalizacja wydajności przy zachowaniu czytelności kodu
    Balans między elastycznością a prostotą użycia

Nowe Zależności

<dependencies>
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter-api</artifactId>
        <version>5.8.2</version>
        <scope>test</scope>
    </dependency>
    <!-- inne zależności... -->
</dependencies>



BankApp
├── main
│   └── java
│       └── org.magisterium
│           ├── Annotations
│           │   └── BankInfo.java
│           ├── Classes
│           │   ├── Banks
│           │   │   └── Bank.java
│           │   ├── LoLScanner
│           │   │   └── MyScanner.java
│           │   ├── ObjectWay
│           │   │   └── ObjectAccessHandler.java
│           │   └── ReflectWay
│           │       ├── ReflectionAccessHandler.java
│           │       └── ReflectionAccessHandlerChild.java
│           ├── Interfaces
│           │   └── BankAccount.java
│           └── Main
│               └── Main.java
├── resources
└── test
    └── java
        └── MyTest.java


        BankApp
├── main
│   └── java
│       └── org.magisterium
│           ├── Annotations
│           │   └── BankInfo.java
│           ├── Classes
│           │   ├── Banks
│           │   │   └── Bank.java
│           │   ├── LoLScanner
│           │   │   └── MyScanner.java
│           │   ├── ObjectWay
│           │   │   └── ObjectAccessHandler.java
│           │   └── ReflectWay
│           │       ├── ReflectionAccessHandler.java
│           │       └── ReflectionAccessHandlerChild.java
│           ├── Interfaces
│           │   └── BankAccount.java
│           └── Main
│               └── Main.java
├── resources
└── test
    └── java
        └── MyTest.java


Przyszłe Plany

    Implementacja nowych funkcjonalności w części refleksyjnej
    Rozbudowa systemu testów
    Optymalizacja wydajności
    Porownanie dostepow i mozliwosci obu wersji
    A takze ciekawsze opcje ich uzywania






