
import org.junit.jupiter.api.*;
import org.magisterium.Classes.Banks.Bank;
import org.magisterium.Classes.ReflectWay.MenuPrint;
import org.magisterium.Classes.ReflectWay.ReflectionAccessHandlerChild;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ReflectionAccessHandlerChildTest {
    private ReflectionAccessHandlerChild handler;
    private Bank testBank;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outCaptor = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
        testBank = new Bank(1000.0, "testUser", "testPass", LocalDateTime.now(), true, false , "a");
        handler = new ReflectionAccessHandlerChild(testBank);
    }

    @AfterEach
    void tearDown() {
        System.setOut(new PrintStream(outCaptor));
        System.setOut(standardOut);
        System.setOut(System.out);
    }


    private void provideInput(String input) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
    }

    @Test
    @DisplayName("Test odczytu salda")
    void testBalanceAccess_Read() {
        provideInput("1\n0\n");
        handler.handleBalanceAccess(new Scanner(System.in));
        String output = outputStreamCaptor.toString();
        assertTrue(output.contains("balance/saldo : 1000.0"));
    }

    @Test
    @DisplayName("Test ustawienia salda")
    void testBalanceAccess_Set() {
        provideInput("2\n2000.0\n0\n");
        handler.handleBalanceAccess(new Scanner(System.in));
        assertEquals(2000.0, testBank.getBalance());
    }



    @Test
    @DisplayName("Test odczytu nazwy użytkownika")
    void testUsernameAccess_Read() {

        provideInput("2\ntestPass\n0\n");

        // 3) Wywołaj metodę
        handler.handleUsernameAccess(new Scanner(System.in));

        // 4) Sprawdź, czy pole username zostało ustawione
        assertEquals("testPass", testBank.getUsername());
    }


    @Test
    @DisplayName("Test ustawienia nazwy użytkownika")
    void testUsernameAccess_Set() {
        provideInput("2\nnewUser\n0\n");
        handler.handleUsernameAccess(new Scanner(System.in));
        String output = outputStreamCaptor.toString();
        assertTrue(output.contains("Wartość pola 'username' została ustawiona na: newUser"));
    }

    @Test
    @DisplayName("Test odczytu hasła")
    void testPasswordAccess_Read() {
        provideInput("1\n0\n");
        handler.handlePasswordAccess(new Scanner(System.in));
        String output = outputStreamCaptor.toString();
        assertTrue(output.contains("\uD83D\uDCB0 password: testUser"));
    }

    @Test
    @DisplayName("Test ustawienia hasła")
    void testPasswordAccess_Set() {
        provideInput("2\nnewPass\n0\n");
        handler.handlePasswordAccess(new Scanner(System.in));
        String output = outputStreamCaptor.toString();
        assertTrue(output.contains("Wartość pola 'password' została ustawiona na: newPass"));
    }

    @Test
    @DisplayName("Test informacji o konstruktorach")
    void testShowConstructorsInfo() {
        handler.showPublicConstructorsInfo();
        String output = outputStreamCaptor.toString();

        // 1. Nagłówek zgodny z tym co drukuje metoda
        assertTrue(output.contains("=== Publiczne konstruktory (getConstructors) ==="),
                "Brak właściwego nagłówka, otrzymano:\n" + output);

        // 2. Sprawdzenie, że drukuje co najmniej jeden publiczny konstruktor
       assertTrue(output.contains("public") ||
                       output.matches("(?s).*public.*\\(.*\\).*"),
                "Nie znaleziono informacji o publicznym modyfikatorze w:\n" + output);
    }

    @Test
    @DisplayName("Test informacji o annotacjach")
    void testAnnotationInfo() {
        // 1. Wywołujemy metodę (która nic nie zwraca)
        handler.displayAllAnnotationsInfo();

        // 2. Pobieramy to, co zostało wypisane na konsolę
        String output = outputStreamCaptor.toString();

        // 3. Asercje – np. sprawdzamy, że znalazły się nazwy klas
        assertTrue(output.contains("Banku"),
                "Oczekiwano informacji o adnotacji BankInfo, ale było:\n" + output);

        // dodaj kolejne asercje, które potwierdzą pełny format wyjścia
    }

    @Test
    @DisplayName("Test nieprawidłowego wyboru")
    void testInvalidChoice() {
        provideInput("invalid\n0\n");
        handler.handleAccess();
        String output = outputStreamCaptor.toString();
        assertTrue(output.contains("Nieprawidłowy wybór"));
    }

    @Test
    @DisplayName("Test getNormalizedChoice")
    void testGetNormalizedChoice() {
        assertEquals("1", handler.getNormalizedChoice(" 1 "));
        assertEquals("2", handler.getNormalizedChoice("2."));
        assertEquals("", handler.getNormalizedChoice("invalid"));
    }

    @Test
    @DisplayName("Test menu głównego")
    void testMainMenu() {
        provideInput("0\n");
        handler.handleAccess();
        String output = outputStreamCaptor.toString();
        assertTrue(output.contains("Menu Główne"));
        assertTrue(output.contains("Dostep do danych"));
    }

    @Test
    @DisplayName("Test daty utworzenia konta")
    void testAccountCreationDateAccess() {
        provideInput("1\n0\n");
        handler.handleAccountCreationDateAccess(new Scanner(System.in));
        String output = outputStreamCaptor.toString();
        assertTrue(output.contains("Data utworzenia:"));
    }

    @Test
    @DisplayName("showPublicConstructorsInfo prints public constructors")
    void testShowPublicConstructorsInfo() {
        handler.showPublicConstructorsInfo();
        String out = outputStreamCaptor.toString();
        assertTrue(out.contains("=== Publiczne konstruktory (getConstructors) ==="));
        assertTrue(out.contains("Modyfikator dostępu: public"));
    }


    /*
    @Test
    @DisplayName("showDeclaredConstructorsInfo prints declared constructors")
    void testShowDeclaredConstructorsInfo() {
        handler.showDeclaredConstructorsInfo();
        String out = outputStreamCaptor.toString();
        assertTrue(out.contains("=== Wszystkie konstruktory (getDeclaredConstructors) ==="));
        // Should include SubBank and Bank constructors
        assertTrue(out.matches("(?s).*(double, String, String).+"));
    }

     */



    @Test
    @DisplayName("showAllFields prints public fields for Bank and SubBank")
    void testShowAllFields() {
        handler.showAllFields();
        String out = outputStreamCaptor.toString();
        assertTrue(out.contains("getFields() (publiczne pola"));

    }

    @Test
    @DisplayName("showDeclaredFields prints declared fields for Bank and SubBank")
    void testShowDeclaredFields() {
        handler.showDeclaredFields();
        String out = outputStreamCaptor.toString();
        assertTrue(out.contains("getDeclaredFields() (wszystkie pola zadeklarowane w klasie)"));
        assertTrue(out.contains("balance"));
        assertTrue(out.contains("username"));
    }

    @Test
    @DisplayName("showAllMethods prints public methods for Bank and SubBank")
    void testShowAllMethods() {
        handler.showAllMethods();
        String out = outputStreamCaptor.toString();
        assertTrue(out.contains("getMethods() (publiczne metody"));
        assertTrue(out.contains("getUsername"));
    }

    @Test
    @DisplayName("showDeclaredMethods prints declared methods for Bank and SubBank")
    void testShowDeclaredMethods() {
        // Wywołujemy metodę
        handler.showDeclaredMethods();

        // Przechwytujemy wyjście
        String out = outputStreamCaptor.toString();

        // 1) Pełny nagłówek
        assertTrue(out.contains(
                "=== getDeclaredMethods() (wszystkie metody zadeklarowane w klasie) ==="
        ), "Brakuje pełnego nagłówka; otrzymano:\n" + out);

        // 2) Oznaczenia klas
        assertTrue(out.contains("[Bank]"),
                "Brakuje bloku dla klasy Bank; otrzymano:\n" + out);
        assertTrue(out.contains("[SubBank]"),
                "Brakuje bloku dla klasy SubBank; otrzymano:\n" + out);

        // 3) Przynajmniej jedna znana metoda zadeklarowana w Bank, np. getUsername()
        assertTrue(out.contains("getUsername()"),
                "Brakuje deklaracji metody getUsername(); otrzymano:\n" + out);
    }


    /*
    @Test
    @DisplayName("showInheritanceAndInterfacesInfo prints superclass and interfaces")
    void testShowInheritanceAndInterfacesInfo() {
        handler.

        String out = outputStreamCaptor.toString();
        assertTrue(out.contains("Superklasa:"));
    }

     */



    @Test
    @DisplayName("testujemy paczke z assertEquals")
    void testShowPackageInfo() throws Exception {
        // 1. Pobierz prywatną, statyczną metodę
        Method method = ReflectionAccessHandlerChild.class
                .getDeclaredMethod("showPackageInfo");
        method.setAccessible(true);

        // 2. Wywołaj ją (null bo statyczna)
        method.invoke(null);

        // 3. Przechwyć wyjście i usuń białe znaki na początku/końcu


        // 4. Zbuduj oczekiwany wynik (bez kolorowania ANSI)
        String expected = "Pakiet: " + Bank.class.getPackage().getName();

        String raw = outputStreamCaptor.toString();
        String clean = raw.replaceAll("\\u001B\\[[;\\d]*m", "").trim();
        assertEquals(expected, clean);

    }



    @Test
    @DisplayName("testujemu info o annotacjaach")
    void testDisplayAllAnnotationsInfo() {
        handler.displayAllAnnotationsInfo();
        String out = outputStreamCaptor.toString();
        assertTrue(out.contains("=== Informacje o Banku (Bank) ==="));
        assertTrue(out.contains("Nazwa:"));
        assertTrue(out.contains("Opis:"));
    }

    @Test
    @DisplayName("displayDeclaredAnnotationsInfo prints declared BankInfo data")
    void testDisplayDeclaredAnnotationsInfo() {
        handler.displayDeclaredAnnotationsInfo();
        String out = outputStreamCaptor.toString();
        assertTrue(out.contains("=== Informacje o Banku (Bank) ==="));
    }

    @Test
    @DisplayName("handleisActiveAccessGet prints current isActive value")
    void testHandleIsActiveGet() {
        handler.handleisActiveAccessGet();
        String out = outputStreamCaptor.toString();
        assertTrue(out.contains("Aktualna wartość pola 'isActive':"));
    }


    @Test
    @DisplayName("handleisActiveAccessSet updates and prints isActive value via reflection")
    void testHandleIsActiveSet() throws Exception {
        // 1) Przygotuj wejście dla metody (System.in)
        provideInput("false\n");

        // 2) Pobierz prywatną metodę (z parametrem Scanner)
        Method method = ReflectionAccessHandlerChild.class
                .getDeclaredMethod("handleisActiveAccessSet", Scanner.class);
        method.setAccessible(true);

        // 3) Wywołaj metodę, podając handlera i nowy Scanner(System.in)
        Scanner scanner = new Scanner(System.in);
        method.invoke(handler, scanner);

        // 4) Pobierz i sprawdź wyjście
        String out = outputStreamCaptor.toString();
        assertTrue(out.contains("zmieniona na: false"),
                "Oczekiwano, że pojawi się 'zmieniona na: false', ale było:\n" + out);
    }




    @Test
    @DisplayName("handleDateAccessSet and get reflectively change account creation date")
    void testHandleDateAccessSetAndGet() {
        String dateStr = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        provideInput(dateStr + "\n");
        handler.handleDateAccessSet(new Scanner(System.in));
        String out = outputStreamCaptor.toString();
        assertTrue(out.contains("Data utworzenia konta została zmieniona na: "));
    }

    @Test
    @DisplayName("handleAccess -> 7 (pakiet) -> 0 (exit)")
    void testHandleAccess_PackageOption() throws Exception {
        // 1) wyłącz ANSI
        System.setProperty("jansi.passthrough", "true");

        // 2) przechwyć wyjście
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        // 3) przygotuj wejście:
        //    - menu główne: 7<enter> → showPackageInfo
        //    - menu główne: 0<enter> → exit
        System.setIn(new ByteArrayInputStream("7\n0\n".getBytes()));

        // 4) uruchom
        handler.handleAccess();

        // 5) sprawdź, że menu i wynik były wypisane
        String printed = out.toString();
        assertTrue(printed.contains("Pakiet: " + Bank.class.getPackage().getName()),
                "Oczekiwano sekcji pakietu, ale było:\n" + printed);
    }


}