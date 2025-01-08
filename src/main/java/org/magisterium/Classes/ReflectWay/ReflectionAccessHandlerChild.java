package org.magisterium.Classes.ReflectWay;

import org.fusesource.jansi.Ansi;
import org.magisterium.Annotations.BankInfo;
import org.magisterium.Classes.Banks.Bank;

import java.lang.reflect.*;
import java.util.*;

import static org.magisterium.Classes.ReflectWay.MenuConstants.*;


public class ReflectionAccessHandlerChild extends ReflectionAccessHandler {

    public ReflectionAccessHandlerChild(Bank bank) {
        super(bank);

    }

    private final Map<String, String> FIELD_ICONS = createFieldIcons();



    public void handleAccess() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String fieldChoice = getNormalizedChoice(displayFieldMenu(scanner));
            switch (fieldChoice) {
                case "1" -> ChooseAllDataFields(scanner);
                case "2" -> showConstructorsInfo();
                case "3" -> handleAnnotationChoice(scanner); // Nowa metoda obsługująca wybór typu annotacji
                case "0" -> {
                    System.out.println("Powrót do menu głównego.");
                    return;
                }
                default -> System.out.println("Nieprawidłowy wybór. Spróbuj ponownie.");
            }
        }
    }

    // opraw bez declared


    /**
     * Dodaj opcje bez declared
     *
     * **/
    public void handleAnnotationChoice(Scanner scanner) {
        while (true) {
          showMenu();
            String choice = getNormalizedChoice(scanner.nextLine());

            if (choice.equals("1")) {
                // chooseAllInformationOfAnnotation();
            } else if (choice.equals("2")) {
                chooseAllInformationOfDeclaredAnnotation();
            } else if (choice.equals("0")) {
                return;
            } else {
                System.out.println("Nieprawidłowy wybór. Spróbuj ponownie.");
            }
        }
    }


    public void showMenu(){
        System.out.println("\nWybierz typ annotacji:");
        System.out.println("1. Bez declared");
        System.out.println("2. Z declared");
        System.out.println("0. Powrót");
    }




    /**poddziel na mniejsze funkcje */

    public void showConstructorsInfo() {
        Class<?> clazz = bank.getClass();
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();

        System.out.println("\n=== Informacje o Konstruktorach ===");

        if (constructors.length == 0) {
            System.out.println("Klasa nie ma zadeklarowanych konstruktorów.");
            return;
        }

        for (Constructor<?> constructor : constructors) {
            System.out.println("\nKonstruktor:");

            // Modyfikatory dostępu
            int modifiers = constructor.getModifiers();
            System.out.println("Modyfikator dostępu: " + Modifier.toString(modifiers));

            // Parametry
            Parameter[] parameters = constructor.getParameters();
            if (parameters.length == 0) {
                System.out.println("Parametry: brak (konstruktor bezargumentowy)");
            } else {
                System.out.println("Parametry:");
                for (Parameter param : parameters) {
                    System.out.println("  - " + param.getType().getSimpleName() + " " + param.getName());
                }
            }

            // Wyjątki
            Class<?>[] exceptions = constructor.getExceptionTypes();
            if (exceptions.length > 0) {
                System.out.println("Deklarowane wyjątki:");
                for (Class<?> exception : exceptions) {
                    System.out.println("  - " + exception.getSimpleName());
                }
            }
        }
        System.out.println("=================================");
    }




    public String getNormalizedChoice(String input) {
        String normalized = input.strip().replace(".", "").toLowerCase();
        if (normalized.equals("1")
                || normalized.equals("2")
                || normalized.equals("3")
                || normalized.equals("4")
                || normalized.equals("5")
                || normalized.equals("0")) {
            return normalized;
        } else {
            return ""; // W przypadku nieprawidłowego wyboru
        }
    }







    private void ChooseAllDataFields(Scanner scanner) {
        while (true) {
            DisplayDataFields(); // Wyświetlenie menu pól danych
            String specificChoice = getNormalizedChoice(scanner.nextLine());

            switch (specificChoice) {
                case "1": // Obsługa Saldo
                    handleBalanceAccess(scanner);
                    break;
                case "2": // Obsługa Nazwa użytkownika
                    handleUsernameAccess(scanner);
                    break;
                case "3": // Obsługa Data utworzenia konta
                    handleAccountCreationDateAccess(scanner);
                    break;
                case "4":
                    handlePasswordAccess(scanner);
                    break;
                case "5":
                    handleisActive(scanner);
                    break;
                case "0": // Powrót do poprzedniego poziomu
                    System.out.println("Powrót do wcześniejszego menu.");
                    return;
                default:
                    System.out.println("Nieprawidłowy wybór. Spróbuj ponownie.");
            }
        }
    }



    // IS ACTIVE  dokoncz

    private void handleisActive(Scanner scanner){
        while (true) {
            System.out.println("1. Odczytaj 'czy aktywny'");
            System.out.println("2. Ustaw 'czy aktywny '");
            System.out.println("0. Powrót");

            System.out.print("Wybierz opcję: ");
            String choice = getNormalizedChoice(scanner.nextLine());

            switch (choice) {
                case "1": // Odczytaj  czy aktywny 
                    handleisActiveAccessGet(scanner);
                    break;
                case "2": // Ustaw  czy atywny 
                     handleisACtiveAccessSet(scanner);
                    break;
                case "0": // Powrót do menu danych
                    return;
                default:
                    System.out.println("Nieprawidłowy wybór. Spróbuj ponownie.");
            }
        }
    }

    private void handleisACtiveAccessSet(Scanner scanner) {

        try{
            System.out.println("Na jaką wartosc ustawic pole czy aktywy true/false ? ");
            Boolean nextedBoolean = scanner.nextBoolean();
            Class<?>  classToIsActive = bank.getClass();
            Field field = classToIsActive.getDeclaredField("isActive");
            field.setAccessible(true);
            field.setBoolean(field,nextedBoolean);


            System.out.println(field);



        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private void handleisActiveAccessGet(Scanner scanner) {

    }

    private void DisplayDataFields() {
        System.out.println("\n⚡⚡⚡ Wybrano: Dostęp do danych ⚡⚡⚡\n");

        // Jeśli FIELD_ICONS to LinkedHashMap, kolejność wstawiania jest zachowana
        FIELD_ICONS.forEach((key, value) ->
                System.out.println(value + " " + key)
        );

        System.out.print(
                Ansi.ansi()
                        .fg(Ansi.Color.CYAN)
                        .bold()
                        .a("\nWybierz pole z powyższej listy: ")
                        .reset().toString()
        );
    }

    private String displayFieldMenu(Scanner scanner) {
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
                        .a("Wersja Refleksyjna - Menu Główne")
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
                        .a(FIELD_ICONS2.get("konstruktory") + " 2. Konstruktory")
                        .reset().toString() +
                        Ansi.ansi()
                                .fg(Ansi.Color.BLUE)
                                .a(" [Dane o inicjalizacjach]")
                                .reset().toString()
        );

        System.out.println(
                Ansi.ansi()
                        .fg(Ansi.Color.CYAN)
                        .a(FIELD_ICONS2.get("annotacje") + "  3. Annotacje ")
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
                        .a("↩  0. Powrót do menu głównego")
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

  /**
   *  B A L A N C E
   * **/


  public void handleBalanceAccess(Scanner scanner) {
      while (true) {
          System.out.println("1. Odczytaj 'balance'");
          System.out.println("2. Ustaw 'balance'");
          System.out.println("0. Powrót");

          System.out.print("Wybierz opcję: ");
          String choice = getNormalizedChoice(scanner.nextLine());
          switch (choice) {
              case "1": // Odczytaj
                  handleBalaneAccessGet();
                  break; // Pozwól na kolejne operacje
              case "2": // Ustaw
                  handleBalanceAccessSet(scanner);
                  break; // Pozwól na kolejne operacje
              case "0": // Powrót do menu danych
                  return;
              default:
                  System.out.println("Nieprawidłowy wybór. Spróbuj ponownie.");
          }
      }
  }




    /**
     * balance
     * get
     *
     * **/


    private void handleBalaneAccessGet() {
        try {
            // Pobranie klasy obiektu bank
            Class<?> bankClass = bank.getClass();

            // Pobranie pola "username"
            Field balance = bankClass.getDeclaredField("balance");

            // Ustawienie dostępu do prywatnego pola
            balance.setAccessible(true);

            // Odczytanie wartości pola "username" z instancji obiektu bank
            Object balanceValue = balance.get(bank);

            System.out.println("💰 balance/saldo : " + balanceValue);

        } catch (NoSuchFieldException e) {
            System.out.println("❌ Pole 'balance' nie istnieje.");
        } catch (IllegalAccessException e) {
            System.out.println("❌ Brak dostępu do pola 'balance'.");
        }

    }


    /**
     *  balance set
     *
     */



    /*
    * System.out.print("🔄 Wprowadź nową wartość pola 'username': ");
            String newUsernameValue = scanner.nextLine();

            // Pobranie pola 'username' z klasy Bank
            Field usernameField = bank.getClass().getDeclaredField("username");

            // Ustawienie dostępu do prywatnego pola
            usernameField.setAccessible(true);

            // Zmiana wartości pola 'username' na instancji obiektu bank
            usernameField.set(bank, newUsernameValue);

            System.out.println("🔄 Wartość pola 'username' została ustawiona na: " + newUsernameValue);
    * */

    private void handleBalanceAccessSet(Scanner scanner) {
        try {
            System.out.print("🔄 Wprowadź nową wartość pola 'balance': ");
            double newBalanceValue2 = Double.parseDouble(scanner.nextLine());
          // Oczyszczenie bufora po wprowadzeniu liczby

            // Ustawienie salda
            Class<?> bankClass = bank.getClass();
            Field balanceField = bankClass.getDeclaredField("balance");
            balanceField.setAccessible(true);
            balanceField.set(bank, newBalanceValue2);

            System.out.println("🔄 Wartość pola 'balance' została ustawiona na: " + balanceField);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InputMismatchException e) {
            System.out.println("Wprowadzono nieprawidłową wartość. Proszę wprowadzić liczbę.");
            scanner.next(); // Oczyszczenie błędnego wejścia
        }
    }


    /*    USERNAME  */






    public void handleUsernameAccess(Scanner scanner) {
        while (true) {
            System.out.println("1. Odczytaj 'username'");
            System.out.println("2. Ustaw 'username'");
            System.out.println("0. Powrót");

            System.out.print("Wybierz opcję: ");
            String choice = getNormalizedChoice(scanner.nextLine());

            switch (choice) {
                case "1": // Odczytaj username
                    handleUsernameAccessGet(scanner);
                    break;
                case "2": // Ustaw username
                    handleUsernameAccessSet(scanner);
                    break;
                case "0": // Powrót do menu danych
                    return;
                default:
                    System.out.println("Nieprawidłowy wybór. Spróbuj ponownie.");
            }
        }
    }



    /**
     *  Username Get
     * **/

    private void handleUsernameAccessGet(Scanner scanner) {
        try {
            // Pobranie klasy obiektu bank
            Class<?> bankClass = bank.getClass();

            // Pobranie pola "username"
            Field usernameField = bankClass.getDeclaredField("username");

            // Ustawienie dostępu do prywatnego pola
            usernameField.setAccessible(true);

            // Odczytanie wartości pola "username" z instancji obiektu bank
            Object usernameValue = usernameField.get(bank);

            System.out.println("💰 username: " + usernameValue);
        } catch (NoSuchFieldException e) {
            System.out.println("❌ Pole 'username' nie istnieje.");
        } catch (IllegalAccessException e) {
            System.out.println("❌ Brak dostępu do pola 'username'.");
        }
    }



    /***
     * Username SEt
     * **/

    private void handleUsernameAccessSet(Scanner scanner) {
        try {
            System.out.print("🔄 Wprowadź nową wartość pola 'username': ");
            String newUsernameValue = scanner.nextLine();

            // Pobranie pola 'username' z klasy Bank
            Field usernameField = bank.getClass().getDeclaredField("username");

            // Ustawienie dostępu do prywatnego pola
            usernameField.setAccessible(true);

            // Zmiana wartości pola 'username' na instancji obiektu bank
            usernameField.set(bank, newUsernameValue);

            System.out.println("🔄 Wartość pola 'username' została ustawiona na: " + newUsernameValue);
        } catch (NoSuchFieldException e) {
            System.out.println("❌ Pole 'username' nie istnieje.");
        } catch (IllegalAccessException e) {
            System.out.println("❌ Błąd podczas zmiany wartości pola 'username'.");
            e.printStackTrace();
        }
    }



    /** PASSWORD  */


    public void handlePasswordAccess(Scanner scanner) {
        while (true) {
            System.out.println("1. Odczytaj 'password'");
            System.out.println("2. Ustaw 'password'");
            System.out.println("0. Powrót");

            System.out.print("Wybierz opcję: ");
            String choice = getNormalizedChoice(scanner.nextLine());

            switch (choice) {
                case "1": // Odczytaj username
                    handlePasswordAccessGet(scanner);
                    break;
                case "2": // Ustaw username
                    handlePasswordAccessSet(scanner);
                    break;
                case "0": // Powrót do menu danych
                    return;
                default:
                    System.out.println("Nieprawidłowy wybór. Spróbuj ponownie.");
            }
        }
    }

    private void handlePasswordAccessSet(Scanner scanner) {
        try {
            System.out.print("🔄 Wprowadź nową wartość pola 'passwordHash': ");
            String newPasswordValue = scanner.nextLine(); // Odczytanie ciągu znaków od użytkownika

            // Pobranie klasy obiektu bank
            Class<?> bankClass = bank.getClass();

            // Pobranie metody setUsername (oczekuje String jako argumentu)
            Method setPasswordMethod = bankClass.getMethod("setPasswordHash", String.class);

            // Wywołanie metody setUsername na instancji obiektu bank
            setPasswordMethod.invoke(bank, newPasswordValue);

            System.out.println("🔄 Wartość pola 'password' została ustawiona na: " + newPasswordValue);
        } catch (NoSuchMethodException e) {
            System.out.println("❌ Metoda 'setUsername' nie istnieje.");
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            System.out.println("❌ Błąd podczas wywołania metody 'setUsername'.");
            e.printStackTrace();
        }
    }

    private void handlePasswordAccessGet(Scanner scanner) {
        try {
            // Pobranie klasy obiektu bank
            Class<?> bankClass = bank.getClass();

            // Pobranie pola "balance"
            Field passwordHashField = bankClass.getDeclaredField("passwordHash");

            // Ustawienie dostępu do prywatnego pola
            passwordHashField.setAccessible(true);

            // Odczytanie wartości pola "password" z instancji obiektu bank
            Object passwordValue = passwordHashField.get(bank);


            System.out.println("💰 password: " + passwordValue);
        } catch (NoSuchFieldException e) {
            System.out.println("❌ Pole 'balance' nie istnieje.");
        } catch (IllegalAccessException e) {
            System.out.println("❌ Brak dostępu do pola 'balance'.");
        }
    }











    /* Data dostepu */
    public void handleAccountCreationDateAccess(Scanner scanner) {
        while (true) {
            System.out.println("1. Odczytaj 'date utworzenie '");
            System.out.println("2. Ustaw 'date utworzenia '");
            System.out.println("0. Powrót");

            System.out.print("Wybierz opcję: ");
            String choice = getNormalizedChoice(scanner.nextLine());

            switch (choice) {
                case "1": // Odczytaj username
                    handleDataAccessGet(scanner);
                    break;
                case "2": // Ustaw username
                    handleDateAccessSet(scanner);
                    break;
                case "0": // Powrót do menu danych
                    return;
                default:
                    System.out.println("Nieprawidłowy wybór. Spróbuj ponownie.");
            }
        }


    }



    /*
    *

        switch (accessChoice) {
            case "1":
                try {
                    // Pobranie klasy obiektu bank
                    Class<?> bankClass = bank.getClass();

                    // popraw na dtae

                    // Pobranie pola "balance"
                    Field balanceField = bankClass.getDeclaredField("username");

                    // Ustawienie dostępu do prywatnego pola
                    balanceField.setAccessible(true);

                    // Odczytanie wartości pola "balance" z instancji obiektu bank
                    Object balanceValue = balanceField.get(bank);


                    System.out.println("💰 Saldo wynosi : " + balanceValue);
                } catch (NoSuchFieldException e) {
                    System.out.println("❌ Pole 'saldo' nie istnieje.");
                } catch (IllegalAccessException e) {
                    System.out.println("❌ Brak dostępu do pola 'saldo'.");
                }
                break;
            case "2":





        }*/



    // dokoncz po rozmowie

    private void handleDateAccessSet(Scanner scanner) {



    }

    private void handleDataAccessGet(Scanner scanner) {
        try {
            // Pobranie klasy obiektu bank
            Class<?> bankClass = bank.getClass();

            // Pobranie pola "balance"
            Field accountCreationDate = bankClass.getDeclaredField("accountCreationDate");

            // Ustawienie dostępu do prywatnego pola
            accountCreationDate.setAccessible(true);

            // Odczytanie wartości pola "password" z instancji obiektu bank
            Object ac = accountCreationDate.get(bank);


            System.out.println("💰 Data utworzenia: " + ac);
        } catch (NoSuchFieldException e) {
            System.out.println("❌ Pole 'utworzenia nie istnieje' nie istnieje.");
        } catch (IllegalAccessException e) {
            System.out.println("❌ Brak dostępu do pola 'data utworzenia'.");
        }

    }


    /**
         * ANNOTACJE
         *
         *
         * **/

        public void chooseAllInformationOfDeclaredAnnotation() {
            Class<?> clazz = bank.getClass();

            // Sprawdzenie, czy metoda ma adnotację @BankInfo
            if (clazz.isAnnotationPresent(BankInfo.class)) {
                try {
                    BankInfo annotation = clazz.getDeclaredAnnotation(BankInfo.class);

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