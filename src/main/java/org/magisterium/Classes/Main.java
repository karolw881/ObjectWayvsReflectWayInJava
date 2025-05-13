package org.magisterium.Classes;

import com.sun.net.httpserver.HttpServer;
import org.magisterium.Classes.LolScanner.MyScanner;
import org.magisterium.Classes.http.ConsoleToHtmlHandler;
import org.magisterium.Classes.http.ConsoleOutputHandler;

import java.io.*;
import java.net.InetSocketAddress;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Main {
    private static final ConcurrentLinkedQueue<String> consoleOutput = new ConcurrentLinkedQueue<>();

    public static void main(String[] args) throws IOException {
        // Przekierowanie wyjścia konsoli
        redirectSystemOut();

        // Uruchomienie serwera HTTP
        HttpServer server = HttpServer.create(new InetSocketAddress(7777), 0);
        server.createContext("/", new ConsoleToHtmlHandler(consoleOutput));
        server.createContext("/output", new ConsoleOutputHandler(consoleOutput));
        server.setExecutor(null);
        server.start();

        System.out.println("Serwer uruchomiony na http://localhost:7777");

        // Uruchomienie skanera
        MyScanner scanner = new MyScanner(System.in);
        scanner.run();
    }

    private static void redirectSystemOut() {
        PrintStream originalOut = System.out;

        PrintStream customOut = new PrintStream(new OutputStream() {
            private StringBuilder line = new StringBuilder();

            @Override
            public void write(int b) {
                char c = (char) b;
                line.append(c);

                if (c == '\n') {
                    consoleOutput.offer(line.toString());
                    line = new StringBuilder();
                }

                originalOut.write(b);
            }
        });

        System.setOut(customOut);
    }
}