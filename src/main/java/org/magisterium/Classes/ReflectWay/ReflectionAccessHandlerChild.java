package org.magisterium.Classes.ReflectWay;

import org.fusesource.jansi.Ansi;
import org.magisterium.Annotations.BankInfo;
import org.magisterium.Classes.Banks.Bank;

import java.lang.reflect.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;


public class ReflectionAccessHandlerChild extends ReflectionAccessHandler {

    public ReflectionAccessHandlerChild(Bank bank) {
        super(bank);

    }
    
    private final String[] DATA_ACCESS_QUOTES = {
            "🔐 Dostęp do skarbca danych...",
            "📊 Panel kontrolny aktywowany...",
            "🎯 Wybierz cel swojej operacji...",
            "💫 Przygotuj się do inspekcji..."
    };
    private final Map<String, String> FIELD_ICONS2 = Map.of(
            "wszystkie", "⚡⚡⚡",
            "konstruktory", "⚡⚡",
            "annotacje", "⚡"

    );

    private Map<String, String> createFieldIcons() {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("Saldo [Stan konta]", "1 - 💰");
        map.put("Nazwa użytkownika [Identyfikator]", "2 - 👤");
        map.put("Data utworzenia konta [Historia]", "3 - 📅");
        map.put("Hasło [Poufne]", "4 - ⚡");
        map.put("Status aktywności", "[Monitoring]");
        return map;
    }


    private final Map<String, String> FIELD_ICONS = createFieldIcons();



    public void handleAccess() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String fieldChoice = getNormalizedChoice(displayFieldMenu(scanner));

            switch (fieldChoice) {
                case "1":
                    ChooseAllDataFields(scanner);
                    break;
                case "3":
                    chooseAllInformationOfDeclaredAnnotation(); // Wyświetla informacje z adnotacji
                    break;
                case "0":
                    System.out.println("Powrót do menu głównego.");
                    return;
                default:
                    System.out.println("Nieprawidłowy wybór. Spróbuj ponownie.");
            }
        }
    }



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


    public String getNormalizedChoice(String input) {
        String normalized = input.strip().replace(".", "").toLowerCase();
        switch (normalized) {
            case "1":
                return "1";
            case "2":
                return "2";
            case "3":
                return "3";
            case "4":
                return "4";
            case "5":
                return "5";
            case "0":
                return "0";
            default:
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
                    handleUsernameAccess(specificChoice, scanner);
                    break;
                case "3": // Obsługa Data utworzenia konta
                    handleAccountCreationDateAccess(specificChoice);
                    break;
                case "0": // Powrót do poprzedniego poziomu
                    System.out.println("Powrót do wcześniejszego menu.");
                    return;
                default:
                    System.out.println("Nieprawidłowy wybór. Spróbuj ponownie.");
            }
        }
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


    //

    /*

    private void displayFieldAccessMenu(String fieldChoice, Scanner scanner) {
        String fieldName = getFieldName2(fieldChoice);

        System.out.println(
                Ansi.ansi()
                        .fg(Ansi.Color.MAGENTA)
                        .bold()
                        .a("\n══════════════════════════════════════════════════════")
                        .reset().toString()
        );


        System.out.println(
                Ansi.ansi()
                        .fg(Ansi.Color.MAGENTA)
                        .bold()
                        .a("   DOSTĘP DO : " + fieldName + "                              ")
                        .reset().toString()
        );

        System.out.println(
                Ansi.ansi()
                        .fg(Ansi.Color.MAGENTA)
                        .bold()
                        .a("════════════════════════════════════════════════")
                        .reset().toString()
        );

        System.out.print(
                Ansi.ansi()
                        .fg(Ansi.Color.CYAN)
                        .bold()
                        .a("\n=> Twój wybór: ")
                        .reset().toString()
        );


        if (fieldChoice == "0"){
            DisplayDataFields();
            handleFieldAccess(fieldChoice,scanner);
        }


        if (fieldChoice == "1"){
            handleBalanceAccess( scanner);
        }
        //return getNormalizedChoice(scanner.nextLine());


    }


     */








    public void handleFieldAccess(String fieldChoice, Scanner scanner) {


        switch (fieldChoice) {
            case "1" -> handleBalanceAccess(scanner);
            case "2", "5" -> handleUsernameAccess(fieldChoice, scanner);
            case "3" -> handleAccountCreationDateAccess(fieldChoice);
            case "4" -> handlePasswordAccess(fieldChoice);
            case "0" -> {
                ChooseAllDataFields(scanner);  }
            default -> System.out.println("Nieprawidłowy wybór. Spróbuj ponownie.");
        }


    }

    //

    private void handleBalanceAccess(Scanner scanner) {
        while (true) {
            System.out.println("1. Odczytaj 'balance'");
            System.out.println("2. Ustaw 'balance'");
            System.out.println("0. Powrót");

            System.out.print("Wybierz opcję: ");
            String choice = getNormalizedChoice(scanner.nextLine());

            switch (choice) {
                case "1": // Odczytaj saldo
                    handleBalaneAccessGet(scanner);
                    break;
                case "2": // Ustaw saldo
                    handleBalanceAccessSet(scanner);
                    break;
                case "0": // Powrót do menu danych
                    return;
                default:
                    System.out.println("Nieprawidłowy wybór. Spróbuj ponownie.");
            }
        }
    }



    // Metoda do ustawienia wartości pola "balance" za pomocą metody settera

    private void handleBalanceAccessSet(Scanner scanner) {
        try {
            System.out.print("🔄 Wprowadź nową wartość pola 'balance': ");
            double newBalanceValue = scanner.nextDouble();
            scanner.nextLine(); // Pobierz pozostały znak nowej linii po `nextDouble`

            // Pobranie klasy obiektu bank
            Class<?> bankClass = bank.getClass();

            // Pobranie metody setBalance
            Method setBalanceMethod = bankClass.getMethod("setBalance", double.class);

            // Wywołanie metody setBalance na instancji obiektu bank
            setBalanceMethod.invoke(bank, newBalanceValue);

            System.out.println("🔄 Wartość pola 'balance' została ustawiona na: " + newBalanceValue);
        } catch (NoSuchMethodException e) {
            System.out.println("❌ Metoda 'setBalance' nie istnieje.");
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            System.out.println("❌ Błąd podczas wywołania metody 'setBalance'.");
            e.printStackTrace();
        }}


    private void handleBalaneAccessGet(Scanner s) {
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
            //ChooseAllDataFields(s);
        } catch (NoSuchFieldException e) {
            System.out.println("❌ Pole 'username' nie istnieje.");
        } catch (IllegalAccessException e) {
            System.out.println("❌ Brak dostępu do pola 'username'.");
        }

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

        /*
        System.out.println(
                Ansi.ansi()
                        .fg(Ansi.Color.RED)
                        .a(FIELD_ICONS.get("Dostep do danych") + "4. Dostep do danych ")
                        .reset().toString() +
                        Ansi.ansi()
                                .fg(Ansi.Color.BLUE)
                                .a(" [Do pola]")
                                .reset().toString()
        );

         */


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




    /*    USERNAME  */


    private void handleUsernameAccess(String accessChoice, Scanner scanner) {
        switch (accessChoice) {
            case "1":
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
                break;
            case "2":
                System.out.println("👤 Nazwa użytkownika: " + bank.getUsername());
                break;
            case "3":
                System.out.print("Podaj nową nazwę użytkownika: ");
                String newUsername = scanner.nextLine();
                // bank.setUsername(newUsername);  // Odkomentować, gdy Bank będzie miał metodę setUsername
                System.out.println("✅ Nazwa użytkownika została zaktualizowana.");
                break;
        }
    }

    /* Data dostepu */
    private void handleAccountCreationDateAccess(String accessChoice) {
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





        }
    }


    /* PASSWORD  */
    private void handlePasswordAccess(String accessChoice) {

            switch (accessChoice) {
                case "1":
                    try {
                        // Pobranie klasy obiektu bank
                        Class<?> bankClass = bank.getClass();

                        // Pobranie pola "balance"
                        Field passwordHashField = bankClass.getDeclaredField("passwordHash");

                        // Ustawienie dostępu do prywatnego pola
                        passwordHashField.setAccessible(true);

                        // Odczytanie wartości pola "balance" z instancji obiektu bank
                        Object balanceValue = passwordHashField.get(bank);


                        System.out.println("💰 password: " + balanceValue);
                    } catch (NoSuchFieldException e) {
                        System.out.println("❌ Pole 'balance' nie istnieje.");
                    } catch (IllegalAccessException e) {
                        System.out.println("❌ Brak dostępu do pola 'balance'.");
                    }
                    break;
                case "2":
                    try {
                        // Pobranie klasy obiektu bank
                        Class<?> bankClass = bank.getClass();

                        // Pobranie pola passwordHash
                        Field passwordHash = bankClass.getDeclaredField("passwordHash");

                        // Ustawienie dostępu do prywatnego pola
                        passwordHash.setAccessible(true);

                        // Odczytanie wartości pola "balance" z instancji obiektu bank
                        Object balanceValue = passwordHash.get(bank);
                        // balanceValue.set(bank, 12);

                        System.out.println("💰 Balance: " + balanceValue);
                    } catch (NoSuchFieldException e) {
                        System.out.println("❌ Pole 'balance' nie istnieje.");
                    } catch (IllegalAccessException e) {
                        System.out.println("❌ Brak dostępu do pola 'balance'.");
                    }


            }
        }






}