
import org.junit.jupiter.api.*;
import org.magisterium.Classes.Banks.Bank;
import org.magisterium.Classes.ReflectWay.ReflectionAccessHandlerChild;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ReflectionAccessHandlerChildTest {
    private ReflectionAccessHandlerChild handler;
    private Bank testBank;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final PrintStream standardOut = System.out;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
        testBank = new Bank(1000.0, "testUser", "testPass", LocalDateTime.now(), true, false);
        handler = new ReflectionAccessHandlerChild(testBank);
    }

    @AfterEach
    void tearDown() {
        System.setOut(standardOut);
    }

    // Helper method to simulate user input
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


    // tu cos zle
    @Test
    @DisplayName("Test odczytu nazwy użytkownika")
    void testUsernameAccess_Read() {
        provideInput("1\n0\n");
        handler.handleUsernameAccess(new Scanner(System.in));
        String output = outputStreamCaptor.toString();
        assertTrue(output.contains("\uD83D\uDCB0 username: testPass"));
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
        handler.showConstructorsInfo();
        String output = outputStreamCaptor.toString();
        assertTrue(output.contains("=== Informacje o Konstruktorach ==="));
        assertTrue(output.contains("Modyfikator dostępu: public"));
    }

    @Test
    @DisplayName("Test informacji o annotacjach")
    void testAnnotationInfo() {
        provideInput("2\n0\n");
        handler.handleAnnotationChoice(new Scanner(System.in));
        String output = outputStreamCaptor.toString();
        assertTrue(output.contains("=== Informacje o Banku ==="));
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
}