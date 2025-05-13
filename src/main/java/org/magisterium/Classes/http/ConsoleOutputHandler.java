// File: org/magisterium/Classes/http/ConsoleOutputHandler.java
package org.magisterium.Classes.http;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ConsoleOutputHandler implements HttpHandler {
    private final ConcurrentLinkedQueue<String> outputQueue;
    private final List<String> outputHistory = new ArrayList<>();

    public ConsoleOutputHandler(ConcurrentLinkedQueue<String> outputQueue) {
        this.outputQueue = outputQueue;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        // Dodaj nowe linie do historii
        while (!outputQueue.isEmpty()) {
            outputHistory.add(outputQueue.poll());
        }

        // Zwróć całą historię
        StringBuilder output = new StringBuilder();
        for (String line : outputHistory) {
            output.append(line);
        }

        byte[] response = output.toString().getBytes(StandardCharsets.UTF_8);
        exchange.getResponseHeaders().set("Content-Type", "text/plain; charset=UTF-8");
        exchange.sendResponseHeaders(200, response.length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(response);
        }
    }
}