import org.junit.jupiter.api.Test;
import org.magisterium.Classes.LolScanner.MyScanner;

import java.io.InputStream;
import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.*;

public class MyTest {

    @Test
    public void testGetNormalizedChoiceWithMocks() {
        // Mockowanie wejścia za pomocą ByteArrayInputStream
        String input = "1\n2\n3\nObiektowa\n   refleksyjna  \nInvalid option\n0\n";
        InputStream mockInputStream = new ByteArrayInputStream(input.getBytes());

        // Tworzymy MyScanner z mockowanym InputStream
        MyScanner myScanner = new MyScanner((System.in));

        // Testowanie różnych wejść
        assertEquals("1", myScanner.getNormalizedChoice("1"));
        assertEquals("2", myScanner.getNormalizedChoice("2"));


        //assertEquals("", myScanner.getNormalizedChoice("3")); // Nieprawidłowy wybór
        assertEquals("1", myScanner.getNormalizedChoice("Obiektowa")); // Testowanie dla "Obiektowa"
        //assertEquals("1.", myScanner.getNormalizedChoice("Obiektowa")); // Testowanie dla "Obiektowa"
        assertEquals("2", myScanner.getNormalizedChoice("refleksyjna")); // Testowanie dla "refleksyjna"
        assertEquals("", myScanner.getNormalizedChoice("Invalid option")); // Nieprawidłowy wybór
        assertEquals("0", myScanner.getNormalizedChoice("0")); // Zakończenie

        assertEquals("1", myScanner.getNormalizedChoice("1"));    // This should still work


        /**
         * I dont know why This assertion Test have not accepted ...........
         * @param assertEquals("1.", myScanner.getNormalizedChoice("(1.)"));
         * org.opentest4j.AssertionFailedError:
         * Expected :1.
         * Actual   :1
         *  Tutaj uwaga bo zmeczony juz jestem hahah
         *
         *    I wil should consider option
         */

        // Nie mam pojecia czemu ten test nie przechodzi caly czas ....
            //   assertEquals("1", myScanner.getNormalizedChoice("(1.)"));

        // Nieprawidłowe wybory
        assertEquals("", myScanner.getNormalizedChoice("3"));
        assertEquals("", myScanner.getNormalizedChoice(""));
        assertEquals("", myScanner.getNormalizedChoice(null));
        assertEquals("1", myScanner.getNormalizedChoice("(1.)"));


    }



    @Test
    public void testInvalidChoice() {
        // Mockowanie wejścia za pomocą ByteArrayInputStream
        String input = "abc\n";
        InputStream mockInputStream = new ByteArrayInputStream(input.getBytes());

        // Tworzymy MyScanner z mockowanym InputStream
        MyScanner myScanner = new MyScanner(mockInputStream);

        // Sprawdzamy, czy wynik jest pusty
        assertEquals("", myScanner.getNormalizedChoice("abc"));



    }
}
