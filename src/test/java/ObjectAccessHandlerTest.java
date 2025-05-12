

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.magisterium.Classes.Banks.Bank;
import org.magisterium.Classes.ObjectWay.ObjectAccessHandler;
import org.mockito.Mockito;

import java.util.Scanner;

import static org.mockito.Mockito.*;

class ObjectAccessHandlerTest {

    private Bank bankMock;
    private ObjectAccessHandler objectAccessHandler;


    @BeforeEach
    void setUp() {
        bankMock = mock(Bank.class);
        objectAccessHandler = new ObjectAccessHandler(bankMock);
    }





    @Test
    @Order(1)
    void testHandleBalanceAccess_DisplayBalance() {
        // Arrange
        when(bankMock.getBalance()).thenReturn(1000.0);

        // Act
        objectAccessHandler.handleFieldAccess("1", new Scanner("2\n0\n"));

        // Assert
        verify(bankMock, times(1)).getBalance();
    }

    @Test
    @Order(2)
    void testHandleBalanceAccess_UpdateBalance() {
        // Arrange
        double newBalance = 1500.0;
        Scanner scanner = new Scanner("3\n" + newBalance + "\n0\n");

        // Act
        objectAccessHandler.handleFieldAccess("1", scanner);

        // Assert
        verify(bankMock, times(1)).setBalance(newBalance);
    }

    @Order(3)
    @Test
    void testHandleUsernameAccess_DisplayUsername() {
        // Arrange
        when(bankMock.getUsername()).thenReturn("TestUser");

        // Act
        objectAccessHandler.handleFieldAccess("2", new Scanner("2\n0\n"));

        // Assert
        verify(bankMock, times(1)).getUsername();
    }



    @Order(4)
    @Test
    void testHandleUsernameAccess_UpdateUsername() {
        // Arrange
        String newUsername = "NewUser";
        Scanner scanner = new Scanner("3\n" + newUsername + "\n0\n");

        // Act
        objectAccessHandler.handleFieldAccess("2", scanner);

        // Assert
        verify(bankMock, times(1)).setUsername(newUsername);
    }


    @Order(5)
    @Test
    void testHandleActivityStatusAccess_DisplayStatus() {
        // Arrange
        when(bankMock.isActive()).thenReturn(true);

        // Act
        objectAccessHandler.handleFieldAccess("5", new Scanner("2\n0\n"));

        // Assert
        verify(bankMock, times(1)).isActive();
    }

    @Order(6)
    @Test
    void testHandleActivityStatusAccess_UpdateStatus() {
        // Arrange
        boolean newStatus = false;
        Scanner scanner = new Scanner("3\n" + newStatus + "\n0\n");

        // Act
        objectAccessHandler.handleFieldAccess("5", scanner);

        // Assert
        verify(bankMock, times(1)).setActive(false);
    }



    @Order(7)
    @Test
    void testGetNormalizedChoice_ValidInput() {
        // Act
        String result = objectAccessHandler.getNormalizedChoice("  3. ");

        // Assert
        assert result.equals("3");
    }

    @Order(8)
    @Test
    void testGetNormalizedChoice_InvalidInput() {
        // Act
        String result = objectAccessHandler.getNormalizedChoice("abc");

        // Assert
        verifyNoInteractions(bankMock);
    }

    @Order(9)
    @Test
    void testHandleBalanceAccess_InvalidChoice_StaysOnMenu() {

        Scanner scanner = new Scanner("9\n0\n");


        objectAccessHandler.handleFieldAccess("1", scanner);

        verifyNoInteractions(bankMock);
    }

    @Order(10)
    @Test
    void testHandleUsernameAccess_InvalidChoice_StaysOnMenu() {
        Scanner scanner = new Scanner("x\n0\n");
        objectAccessHandler.handleFieldAccess("2", scanner);
        verifyNoInteractions(bankMock);
    }

    @Order(11)
    @Test
    void testHandleActivityStatusAccess_InvalidChoice_StaysOnMenu() {
        Scanner scanner = new Scanner("foo\n0\n");
        objectAccessHandler.handleFieldAccess("5", scanner);
        verifyNoInteractions(bankMock);
    }


    @Order(12)
    @Test
    void testNormalize_NullInput() {
        // Act
        String result = objectAccessHandler.Normalize(null);
        // Assert
        verifyNoInteractions(bankMock);
    }

    @Order(13)
    @Test
    void testNormalize_PreservesTrailingDot() {
        String result = objectAccessHandler.Normalize("(Hello).");
        verifyNoInteractions(bankMock);
    }

    @Order(14)
    @Test
    void testNormalize_RemovesAllInternalDots() {
        String result = objectAccessHandler.Normalize("3.14 test");
        verifyNoInteractions(bankMock);
    }


    @Order(15)
    @Test
    void testGetNormalizedChoice_ValidInput1() {
        // Act
        String result = objectAccessHandler.getNormalizedChoice("  3.... ");

        // Assert
        assert result.equals("3");
    }




}
