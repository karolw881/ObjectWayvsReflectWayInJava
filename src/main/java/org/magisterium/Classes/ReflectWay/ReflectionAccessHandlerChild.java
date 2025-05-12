package org.magisterium.Classes.ReflectWay;

import org.fusesource.jansi.Ansi;
import org.magisterium.Annotations.BankInfo;
import org.magisterium.Classes.Banks.Bank;
import org.magisterium.Classes.Banks.SubBank;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.function.Consumer;

import static org.magisterium.Classes.ReflectWay.MenuPrint.*;


public class    ReflectionAccessHandlerChild extends ReflectionAccessHandler {
SubBank subBank;
    Scanner scanner ;
    public ReflectionAccessHandlerChild(Bank bank) {
        super(bank);
        try {
            // Uzyskiwanie dostępu do konstruktora SubBank
            Constructor<?> constructor = SubBank.class.getConstructor(double.class, String.class, String.class);

            // Tworzenie instancji SubBank za pomocą refleksji
            subBank = (SubBank) constructor.newInstance(2000.0, "Karol", "tatamamam123#");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String getNormalizedChoice(String input) {
        // Usunięcie zbędnych znaków, takich jak nawiasy, kropki i spacje
        String normalized = input.strip()
                .replace(".", "")    // Usuwa kropki
                .replace("(", "")    // Usuwa nawiasy otwierające
                .replace(")", "")    // Usuwa nawiasy zamykające
                .toLowerCase();      // Zamienia na małe litery

        // Sprawdzenie, czy wynik pasuje do dozwolonych opcji
        if (normalized.equals("1")
                || normalized.equals("2")
                || normalized.equals("3")
                || normalized.equals("4")
                || normalized.equals("5")
                || normalized.equals("6")
                || normalized.equals("7")
                || normalized.equals("8")
                || normalized.equals("0")) {
            return normalized; // Zwrócenie znormalizowanego wyboru
        } else {
            return ""; // W przypadku nieprawidłowego wyboru
        }
    }

    public void handleAccess() {
       scanner = new Scanner(System.in);
        while (true) {
            String fieldChoice = getNormalizedChoice(MenuPrint.displayFieldMenu(scanner));

            System.out.println(fieldChoice);
            switch (fieldChoice) {
                case "1" -> ChooseAllDataFields(scanner);
                case "2" -> handleConstructor(scanner);
                case "3" -> handleAnnotationChoice(scanner); // Nowa metoda obsługująca wybór typu annotacji
                case "4" -> handleMethodsChoice2(scanner);
                case "5" -> handleFieldChoice(scanner);
                case "6" -> showInheritanceAndInterfacesInfo();
                case "7" ->  showPackageInfo();
                case "8" ->   showAccessModifiersInfo();
                case "0" -> {
                    menuMainReturn();
                    return;
                }
                default -> wrongChoose();
            }
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
                    handlePasswordAccess(scanner); // obsluga hasla
                    break;
                case "5":
                    handleisActive(scanner); // obsluga aktywnosci
                    break;
                case "0": // Powrót do poprzedniego poziomu
                    menuBackReturn();
                    return;
                default:
                    wrongChoose();
            }
        }
    }

    /**poddzielono na mniejsze funkcje */

    /**
     * Wariant „bez declarED” – pokazuje tylko publiczne konstruktory zadeklarowane w klasie.
     */
    public void showPublicConstructorsInfo() {
        Class<?> clazz = bank.getClass();
        Constructor<?>[] constructors = clazz.getConstructors();

        MenuPrint.dispplayAnsiMethodYellow("\n=== Publiczne konstruktory (getConstructors) ===");
        if (constructors.length == 0) {
            MenuPrint.dispplayAnsiMethodYellow("Brak publicznych konstruktorów.");
        } else {
            for (Constructor<?> ctor : constructors) {
                MenuPrint.dispplayAnsiMethodYellow("\nKonstruktor:");
                printModifiers(ctor);
                printParameters(ctor);
                printExceptions(ctor);
            }
        }
        MenuPrint.dispplayAnsiMethodYellow("=================================\n");
    }

    /**
     * Wariant „z declarED” – pokazuje wszystkie konstruktory zadeklarowane w klasie,
     * niezależnie od modyfikatora.
     */
    public void showDeclaredConstructorsInfo() {
        Class<?> clazz = bank.getClass();
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();

        MenuPrint.dispplayAnsiMethodGreen("\n=== Wszystkie konstruktory (getDeclaredConstructors) ===");
        if (constructors.length == 0) {
            MenuPrint.dispplayAnsiMethodGreen("Brak zadeklarowanych konstruktorów.");
        } else {
            for (Constructor<?> ctor : constructors) {
                MenuPrint.dispplayAnsiMethodGreen("\nKonstruktor:");
                printModifiers(ctor);
                printParameters(ctor);
                printExceptions(ctor);
            }
        }
        MenuPrint.dispplayAnsiMethodGreen("=================================\n");
    }

    private void printModifiers(Constructor<?> constructor) {
        int modifiers = constructor.getModifiers();
        MenuPrint.dispplayAnsiMethodGreen("Modyfikator dostępu: " + Modifier.toString(modifiers));
    }

    private void printParameters(Constructor<?> constructor) {
        Parameter[] parameters = constructor.getParameters();
        if (parameters.length == 0) {
            MenuPrint.dispplayAnsiMethodGreen("Parametry: brak (konstruktor bezargumentowy)");
        } else {
            MenuPrint.dispplayAnsiMethodGreen("Parametry:");
            for (Parameter param : parameters) {
                MenuPrint.dispplayAnsiMethodGreen("  - " + param.getType().getSimpleName() + " " + param.getName());
            }
        }
    }

    private void printExceptions(Constructor<?> constructor) {
        Class<?>[] exceptions = constructor.getExceptionTypes();
        if (exceptions.length > 0) {
            MenuPrint.dispplayAnsiMethodGreen("Deklarowane wyjątki:");
            for (Class<?> exception : exceptions) {
                dispplayAnsiMethodGreen("  - " + exception.getSimpleName());
            }
        }else{
            dispplayAnsiMethodGreen("mniejsze od 0 ");
        }
    }



    private void handleConstructor(Scanner scanner){
        while (true) {
            showMenuConstructor();
            String choice = getNormalizedChoice(scanner.nextLine());

            switch (choice) {
                case "1": // Odczytaj  czy aktywny
                    showPublicConstructorsInfo();
                    break;
                case "2": // Ustaw  czy atywny
                    showDeclaredConstructorsInfo();
                    break;
                case "0": // Powrót do menu danych
                    return;
                default:
                    wrongChoose();
            }
        }
    }





    // IS ACTIVE  dokoncz

    private void handleisActive(Scanner scanner){
        while (true) {
          showMenuActive();
            String choice = getNormalizedChoice(scanner.nextLine());

            switch (choice) {
                case "1": // Odczytaj  czy aktywny 
                    handleisActiveAccessGet();
                    break;
                case "2": // Ustaw  czy atywny 
                     handleisActiveAccessSet(scanner);
                    break;
                case "0": // Powrót do menu danych
                    return;
                default:
                    wrongChoose();
            }
        }
    }

    private void handleisActiveAccessSet(Scanner scanner) {
        try {
            // Pobranie klasy obiektu
            Class<?> bankClass = bank.getClass();

            // Pobranie pola isActive
            Field isActiveField = bankClass.getDeclaredField("isActive");
            isActiveField.setAccessible(true);

            // Zapytanie użytkownika o nową wartość pola
            System.out.print("🔄 Ustaw nową wartość pola 'isActive' (true/false): ");
            boolean newValue = scanner.nextBoolean();
            scanner.nextLine(); // Czyszczenie bufora wejścia

            // Ustawienie nowej wartości dla pola isActive
            isActiveField.setBoolean(bank, newValue);

            // Wyświetlenie nowej wartości
            System.out.println("✅ Wartość pola 'isActive' została zmieniona na: " + isActiveField.getBoolean(bank));
        } catch (NoSuchFieldException e) {
            System.out.println("❌ Pole 'isActive' nie istnieje.");
        } catch (IllegalAccessException e) {
            System.out.println("❌ Brak dostępu do pola 'isActive'.");
        } catch (Exception e) {
            System.out.println("❌ Błąd: Wystąpił nieoczekiwany problem.");
        }
    }


    /**
     *
     * @param string
     */
    private void dispplayAnsiMethodGreen(String string){
        System.out.println(
                Ansi.ansi()
                        .fg(Ansi.Color.GREEN)
                        .bold()
                        .a(string)
                        .reset().toString());

    }

    private void dispplayAnsiMethodRed(String string){
        System.out.println(
                Ansi.ansi()
                        .fg(Ansi.Color.RED)
                        .bold()
                        .a(string)
                        .reset().toString());

    }




    private void handleisActiveAccessGet() {
        try {
            // Pobranie klasy obiektu
            Class<?> bankClass = bank.getClass();

            // Pobranie pola isActive
            Field isActiveField = bankClass.getDeclaredField("isActive");
            isActiveField.setAccessible(true);

            // Odczytanie wartości pola z instancji obiektu bank
            boolean currentValue = isActiveField.getBoolean(bank);

            // Wyświetlenie wartości
            dispplayAnsiMethodMagenta("🔍 Aktualna wartość pola 'isActive': " + currentValue);

        } catch (NoSuchFieldException e) {
            System.out.println("❌ Pole 'isActive' nie istnieje.");
        } catch (IllegalAccessException e) {
            System.out.println("❌ Brak dostępu do pola 'isActive'.");
        } catch (Exception e) {
            System.out.println("❌ Błąd: Wystąpił nieoczekiwany problem.");
        }
    }


  /**
   *  B A L A N C E
   * **/


  public void handleBalanceAccess(Scanner scanner) {
      while (true) {
          showMenuBalance();
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
                  wrongChoose();
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


          //  dispplayAnsiMethodGreen("💰 balance/saldo : " + balanceValue);


            dispplayAnsiMethodRed("💰 balance/saldo : " + balanceValue);

        } catch (NoSuchFieldException e) {
            dispplayAnsiMethodGreen("❌ Pole 'balance' nie istnieje.");
        } catch (IllegalAccessException e) {
            dispplayAnsiMethodRed("❌ Brak dostępu do pola 'balance'.");
        }

    }


    /**
     *  balance set
     *
     */


    private void handleBalanceAccessSet(Scanner scanner) {
        try {
            dispplayAnsiMethodBlue("🔄 Wprowadź nową wartość pola 'balance': ");
            String line = scanner.nextLine().trim();
            double newBalanceValue = Double.parseDouble(line);

            // Refleksja: ustawiamy pole 'balance'
            Class<?> bankClass = bank.getClass();
            Field balanceField = bankClass.getDeclaredField("balance");
            balanceField.setAccessible(true);
            balanceField.set(bank, newBalanceValue);

           dispplayAnsiMethodBlue("🔄 Wartość pola 'balance' została ustawiona na: " + newBalanceValue);

        } catch (NumberFormatException e) {
            MenuPrint.dispplayAnsiMethodRed("❌ Wprowadzono nieprawidłową wartość.");
            // już pobraliśmy linię powyżej, więc bufor jest czysty — nie trzeba dodatkowego scanner.next()
        } catch (NoSuchFieldException | IllegalAccessException e) {
            MenuPrint.dispplayAnsiMethodRed("❌ Błąd wewnętrzny: nie można uzyskać dostępu do pola 'balance'.");
            e.printStackTrace();
        }
    }


    /*    USERNAME  HANDLER  */


    public void handleUsernameAccess(Scanner scanner) {
        while (true) {
            showMenuUsername();
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
                    dispplayAnsiMethodRed("Nieprawidłowy wybór. Spróbuj ponownie.");
            }
        }
    }

    public void showAllFields() {
        MenuPrint.dispplayAnsiMethodGreen("=== getFields() (publiczne pola z klasy i nadklas) ===");
        for (Object obj : new Object[]{bank, subBank}) {
            Class<?> clazz = obj.getClass();
            Field[] fields = clazz.getFields();
            MenuPrint.dispplayAnsiMethodGreen("[" + clazz.getSimpleName() + "]");
            for (Field f : fields) {
                String line = String.format("  • %s %s (%s)",
                        Modifier.toString(f.getModifiers()),
                        f.getType().getSimpleName(),
                        f.getName()
                );
                MenuPrint.dispplayAnsiMethodGreen(line);
            }
            System.out.println();
        }
    }

    public void showDeclaredFields() {
        MenuPrint.dispplayAnsiMethodMagenta("=== getDeclaredFields() (wszystkie pola zadeklarowane w klasie) ===");
        for (Object obj : new Object[]{bank, subBank}) {
            Class<?> clazz = obj.getClass();
            Field[] fields = clazz.getDeclaredFields();
            MenuPrint.dispplayAnsiMethodMagenta("[" + clazz.getSimpleName() + "]");
            for (Field f : fields) {
                String line = String.format("  • %s %s (%s)",
                        Modifier.toString(f.getModifiers()),
                        f.getType().getSimpleName(),
                        f.getName()
                );
                MenuPrint.dispplayAnsiMethodMagenta(line);
            }
            System.out.println();
        }
    }

    public void showAllMethods() {
        MenuPrint.dispplayAnsiMethodYellow("=== getMethods() (publiczne metody z klasy, nadklas i interfejsów) ===");
        for (Object obj : new Object[]{bank, subBank}) {
            Class<?> clazz = obj.getClass();
            Method[] methods = clazz.getMethods();
            MenuPrint.dispplayAnsiMethodYellow("[" + clazz.getSimpleName() + "]");
            for (Method m : methods) {
                String line = String.format("  • %s %s %s()",
                        Modifier.toString(m.getModifiers()),
                        m.getReturnType().getSimpleName(),
                        m.getName()
                );
                MenuPrint.dispplayAnsiMethodYellow(line);
            }
            System.out.println();
        }
    }

    public void showDeclaredMethods() {
        MenuPrint.dispplayAnsiMethodMagenta("=== getDeclaredMethods() (wszystkie metody zadeklarowane w klasie) ===");
        for (Object obj : new Object[]{bank, subBank}) {
            Class<?> clazz = obj.getClass();
            Method[] methods = clazz.getDeclaredMethods();
            MenuPrint.dispplayAnsiMethodMagenta("[" + clazz.getSimpleName() + "]");
            for (Method m : methods) {
                String line = String.format("  • %s %s %s()",
                        Modifier.toString(m.getModifiers()),
                        m.getReturnType().getSimpleName(),
                        m.getName()
                );
                MenuPrint.dispplayAnsiMethodMagenta(line);
            }
            System.out.println();
        }
    }


    /**
     *  Username Get
     * **/

    private void handleUsernameAccessGet(Scanner scanner) {
        try {
            // Pobranie klasy obiektu bank
            Class<?> bankClass = bank.getClass();

            // Pobranie prywatnej metody getUsername()
            Method getUsernameMethod = bankClass.getDeclaredMethod("getUsername");
            getUsernameMethod.setAccessible(true);

            // Wywołanie metody refleksyjnie
            Object usernameValue = getUsernameMethod.invoke(bank);

            dispplayAnsiMethodGreen("🔐 Username: " + usernameValue);
        } catch (NoSuchMethodException e) {
            MenuPrint.dispplayAnsiMethodRed("❌ Metoda 'getUsername' nie istnieje.");
        } catch (IllegalAccessException e) {
            MenuPrint.dispplayAnsiMethodRed("❌ Brak dostępu do metody 'getUsername'.");
        } catch (InvocationTargetException e) {
            MenuPrint.dispplayAnsiMethodRed("❌ Błąd przy wywoływaniu metody: " + e.getCause());
        }
    }




    /***
     * Username SEt
     * **/

    private void handleUsernameAccessSet(Scanner scanner) {
        try {
            dispplayAnsiMethodBlue("🔄 Wprowadź nową wartość pola 'username': ");
            String newUsernameValue = scanner.nextLine();

            // Pobranie pola 'username' z klasy Bank
            Field usernameField = bank.getClass().getDeclaredField("username");

            // Ustawienie dostępu do prywatnego pola
            usernameField.setAccessible(true);

            // Zmiana wartości pola 'username' na instancji obiektu bank
            usernameField.set(bank, newUsernameValue);

           dispplayAnsiMethodBlue("🔄 Wartość pola 'username' została ustawiona na: " + newUsernameValue);
        } catch (NoSuchFieldException e) {
            MenuPrint.dispplayAnsiMethodRed("❌ Pole 'username' nie istnieje.");
        } catch (IllegalAccessException e) {
            MenuPrint.dispplayAnsiMethodRed("❌ Błąd podczas zmiany wartości pola 'username'.");
            e.printStackTrace();
        }
    }



    /** PASSWORD  */

    public void handlePasswordAccess(Scanner scanner) {
        while (true) {
            showMenuPassword();
            String choice = getNormalizedChoice(scanner.nextLine().trim()); // Dodano trim(), aby usunąć spacje z początku i końca

            switch (choice) {
                case "1" -> handlePasswordAccessGet(scanner); // Użycie nowego składni switch (Java 13+)
                case "2" -> handlePasswordAccessSet(scanner);
                case "0" -> {
                    MenuPrint.dispplayAnsiMethodGreen("Powrót do menu danych...");
                    return; // Dodano komunikat, aby użytkownik wiedział, co się dzieje
                }
                default -> wrongChoose(); // Użycie nowej składni switch (Java 13+)
            }
        }
    }

    private void handlePasswordAccessSet(Scanner scanner) {
        try {
            dispplayAnsiMethodBlue("🔄 Wprowadź nową wartość pola 'passwordHash': ");
            String newPasswordValue = scanner.nextLine(); // Odczytanie ciągu znaków od użytkownika

            // Pobranie klasy obiektu bank
            Class<?> bankClass = bank.getClass();

            // Pobranie metody setUsername (oczekuje String jako argumentu)
            Method setPasswordMethod = bankClass.getMethod("setPasswordHash", String.class);

            // Wywołanie metody setUsername na instancji obiektu bank
            setPasswordMethod.invoke(bank, newPasswordValue);

            dispplayAnsiMethodBlue("🔄 Wartość pola 'password' została ustawiona na: " + newPasswordValue);
        } catch (NoSuchMethodException e) {
            MenuPrint.dispplayAnsiMethodRed("❌ Metoda 'setUsername' nie istnieje.");
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            MenuPrint.dispplayAnsiMethodRed("❌ Błąd podczas wywołania metody 'setUsername'.");
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


            dispplayAnsiMethodBlue("💰 password: " + passwordValue);
        } catch (NoSuchFieldException e) {
            dispplayAnsiMethodRed("❌ Pole 'balance' nie istnieje.");
        } catch (IllegalAccessException e) {
            dispplayAnsiMethodRed("❌ Brak dostępu do pola 'balance'.");
        }
    }



    /* Data dostepu */
    public void handleAccountCreationDateAccess(Scanner scanner) {
        while (true) {
            showMenuDataAcces();
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
                    wrongChoose();
            }
        }

    }



    public void handleDateAccessSet(Scanner scanner) {
        try {
            // Pobranie klasy obiektu bank
            Class<?> bankClass = bank.getClass();

            // Pobranie metody setAccountCreationDate(LocalDateTime)
            Method setDateMethod = bankClass.getDeclaredMethod("setAccountCreationDate", LocalDateTime.class);
            setDateMethod.setAccessible(true); // pozwalamy na dostęp do prywatnej metody
            dispplayAnsiMethodYellow("format daty yyyy-MM-dd HH:mm:ss ");
            // Pobranie daty od użytkownika
            String inputDate = scanner.nextLine();

            // Konwersja na LocalDateTime
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime newDate = LocalDateTime.parse(inputDate, formatter);

            // Wywołanie metody refleksyjnie
            setDateMethod.invoke(bank, newDate);

            dispplayAnsiMethodGreen("✅ Data utworzenia konta została zmieniona na: " + newDate);

        } catch (NoSuchMethodException e) {
            dispplayAnsiMethodRed("❌ Metoda 'setAccountCreationDate' nie istnieje.");
        } catch (IllegalAccessException e) {
            dispplayAnsiMethodRed("❌ Brak dostępu do metody 'setAccountCreationDate'.");
        } catch (InvocationTargetException e) {
            dispplayAnsiMethodRed("❌ Błąd przy wywoływaniu metody: " + e.getCause());
        } catch (Exception e) {
            dispplayAnsiMethodRed("❌ Błąd: Nie udało się zmienić daty. Upewnij się, że podany format jest poprawny.");
        }
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

                dispplayAnsiMethodYellow("💰 Data utworzenia: " + ac);
            //    System.out.println("💰 Data utworzenia: " + ac);
            } catch (NoSuchFieldException e) {
                MenuPrint.dispplayAnsiMethodRed("❌ Pole 'utworzenia nie istnieje' nie istnieje.");
            } catch (IllegalAccessException e) {
                MenuPrint.dispplayAnsiMethodRed("❌ Brak dostępu do pola 'data utworzenia'.");
            }

        }


    /**
         * ANNOTACJE
         *
         *
         * **/

    public void handleAnnotationChoice(Scanner scanner) {

        while (true) {
            showMenu();
            String choice = getNormalizedChoice(scanner.nextLine());
           // System.out.println(choice);
            if (choice.equals("1")) {
                displayAllAnnotationsInfo();
            } else if (choice.equals("2")) {
                displayDeclaredAnnotationsInfo();
            } else if (choice.equals("0")) {
                return;
            } else {
                MenuPrint.dispplayAnsiMethodRed("Nieprawidłowy wybór. Spróbuj ponownie.");
            }
        }
    }


    public void handleMethodsChoice2(Scanner scanner) {

        while (true) {
            showMenuMethods2();
            String choice = getNormalizedChoice(scanner.nextLine());
            // System.out.println(choice);
            if (choice.equals("1")) {
                showAllMethods();
            } else if (choice.equals("2")) {
                showDeclaredMethods();
            } else if (choice.equals("0")) {
                return;
            } else {
                MenuPrint.dispplayAnsiMethodRed("Nieprawidłowy wybór. Spróbuj ponownie.");
            }
        }
    }


    public void handleFieldChoice(Scanner scanner) {

        while (true) {
            showMenuFields();
            String choice = getNormalizedChoice(scanner.nextLine());
            // System.out.println(choice);
            if (choice.equals("1")) {
                showAllFields();
            } else if (choice.equals("2")) {
                showDeclaredFields();
            } else if (choice.equals("0")) {
                return;
            } else {
                MenuPrint.dispplayAnsiMethodRed("Nieprawidłowy wybór. Spróbuj ponownie.");
            }
        }
    }




    /**
     * Wariant "pełny": getAnnotation() zwraca adnotację zadeklarowaną bezpośrednio
     * oraz odziedziczoną (jeśli @BankInfo ma @Inherited).
     */
    public void displayAllAnnotationsInfo() {
        Object[] banks = { bank, subBank };
        for (Object obj : banks) {
            Class<?> clazz = obj.getClass();
            BankInfo info = clazz.getAnnotation(BankInfo.class);
            if (info == null) continue;
            printInfo(clazz, info, obj);
        }
    }

    /**
     * Wariant "declare-only": getDeclaredAnnotation() zwraca tylko te adnotacje,
     * które klasa sama deklaruje.
     */
    public void displayDeclaredAnnotationsInfo() {
        Object[] banks = { bank, subBank };
        for (Object obj : banks) {
            Class<?> clazz = obj.getClass();
            BankInfo info = clazz.getDeclaredAnnotation(BankInfo.class);
            if (info == null) continue;
            printInfo(clazz, info, obj);
        }
    }

    /**
     * Wspólna metoda do wyświetlania pełnego bloku informacji.
     */
    private void printInfo(Class<?> clazz, BankInfo info, Object obj) {
        // wybór koloru wg typu instancji
        Consumer<String> printer = (obj instanceof SubBank)
                ? MenuPrint::dispplayAnsiMethodBlue
                : MenuPrint::dispplayAnsiMethodMagenta;

        printer.accept("=== Informacje o Banku (" + clazz.getSimpleName() + ") ===");
        printer.accept("Nazwa:     " + info.name());
        printer.accept("Opis:      " + info.description());
        printer.accept("Siedziba:  " + info.headquarters());
        printer.accept("Kapitał:   " + info.capital());
        printer.accept("O nas:     " + info.aboutUs());
        printer.accept("===============================\n");
    }





    // 4. Metody
    private static void showMethodInfo() {
        Class<?> clazz = Bank.class;
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            dispplayAnsiMethodBlue("Metoda: " + method.getName());
            dispplayAnsiMethodBlue("Typ zwracany: " + method.getReturnType());
            dispplayAnsiMethodBlue("Modyfikatory: " + Modifier.toString(method.getModifiers()));
        }
    }


    // 5. Pola
    private static void showFieldInfo() {
        Class<?> clazz = Bank.class;
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            dispplayAnsiMethodBlue("Pole: " + field.getName());
            dispplayAnsiMethodBlue("Typ: " + field.getType());
        }
    }

    // 6. Dziedziczenie i Interfejsy
    private static void showInheritanceAndInterfacesInfo() {
        Class<?> clazz = Bank.class;
        Class<?>[] interfaces = clazz.getInterfaces();
        for (Class<?> iface : interfaces) {
            dispplayAnsiMethodBlue("Implementowany interfejs: " + iface.getName());
        }

        Class<?> superclass = clazz.getSuperclass();
        dispplayAnsiMethodBlue("Superklasa: " + superclass.getName());
    }





    // 7. Pakiety
    private static void showPackageInfo() {
        Class<?> clazz = Bank.class;
        Package pkg = clazz.getPackage();
        dispplayAnsiMethodBlue("Pakiet: " + pkg.getName());
    }

    // 8. Modyfikatory dostępu
    private static void showAccessModifiersInfo() {
        Class<?> clazz = Bank.class;
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            dispplayAnsiMethodBlue("Metoda: " + method.getName());
            dispplayAnsiMethodBlue("Modyfikatory dostępu: " + Modifier.toString(method.getModifiers()));
        }
    }

    public void dispplayAnsiMethodYellow(String string){
        System.out.println(
                Ansi.ansi()
                        .fg(Ansi.Color.YELLOW)
                        .bold()
                        .a(string)
                        .reset().toString());

    }


    public static void dispplayAnsiMethodBlue(String string){
        System.out.println(
                Ansi.ansi()
                        .fg(Ansi.Color.BLUE)
                        .bold()
                        .a(string)
                        .reset().toString());

    }

    public void dispplayAnsiMethodMagenta(String string){
        System.out.println(
                Ansi.ansi()
                        .fg(Ansi.Color.MAGENTA)
                        .bold()
                        .a(string)
                        .reset().toString());

    }

    public void dispplayAnsiMethodWhite(String string){
        System.out.println(
                Ansi.ansi()
                        .fg(Ansi.Color.WHITE)
                        .bold()
                        .a(string)
                        .reset().toString());

    }









}