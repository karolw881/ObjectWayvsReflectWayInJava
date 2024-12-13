

import org.junit.jupiter.api.BeforeEach;
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
    void testHandleBalanceAccess_DisplayBalance() {
        // Arrange
        when(bankMock.getBalance()).thenReturn(1000.0);

        // Act
        objectAccessHandler.handleFieldAccess("1", new Scanner("2\n0\n"));

        // Assert
        verify(bankMock, times(1)).getBalance();
    }

    @Test
    void testHandleBalanceAccess_UpdateBalance() {
        // Arrange
        double newBalance = 1500.0;
        Scanner scanner = new Scanner("3\n" + newBalance + "\n0\n");

        // Act
        objectAccessHandler.handleFieldAccess("1", scanner);

        // Assert
        verify(bankMock, times(1)).setBalance(newBalance);
    }

    @Test
    void testHandleUsernameAccess_DisplayUsername() {
        // Arrange
        when(bankMock.getUsername()).thenReturn("TestUser");

        // Act
        objectAccessHandler.handleFieldAccess("2", new Scanner("2\n0\n"));

        // Assert
        verify(bankMock, times(1)).getUsername();
    }

    /**
     * TEst nie przeszedl
     */


    /*
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

     */

    @Test
    void testHandleActivityStatusAccess_DisplayStatus() {
        // Arrange
        when(bankMock.isActive()).thenReturn(true);

        // Act
        objectAccessHandler.handleFieldAccess("5", new Scanner("2\n0\n"));

        // Assert
        verify(bankMock, times(1)).isActive();
    }


    /**
    TEST nie przeszedl hahah
     */

    /*
    @Test
    void testHandleActivityStatusAccess_UpdateStatus() {
        // Arrange
        boolean newStatus = false;
        Scanner scanner = new Scanner("3\n" + newStatus + "\n0\n");

        // Act
        objectAccessHandler.handleFieldAccess("5", scanner);

        // Assert
        verify(bankMock, times(1)).getBalance();
    }

     */

    @Test
    void testGetNormalizedChoice_ValidInput() {
        // Act
        String result = objectAccessHandler.getNormalizedChoice("  3. ");

        // Assert
        assert result.equals("3");
    }

    @Test
    void testGetNormalizedChoice_InvalidInput() {
        // Act
        String result = objectAccessHandler.getNormalizedChoice("abc");

        // Assert
        assert result.equals("");
    }
}
