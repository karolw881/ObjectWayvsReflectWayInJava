// File: org/magisterium/Classes/http/ConsoleToHtmlHandler.java
package org.magisterium.Classes.http;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ConsoleToHtmlHandler implements HttpHandler {
    private final ConcurrentLinkedQueue<String> outputQueue;

    public ConsoleToHtmlHandler(ConcurrentLinkedQueue<String> outputQueue) {
        this.outputQueue = outputQueue;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String html = """
            <!DOCTYPE html>
            <html>
            <head>
                <title>Console Output</title>
                <meta charset="UTF-8">
                <style>
                    body { 
                        background-color: #1a1a1a; 
                        color: #fff; 
                        font-family: monospace; 
                        padding: 20px;
                        margin: 0;
                    }
                    #console { 
                        white-space: pre-wrap; 
                        font-size: 14px; 
                        line-height: 1.5;
                        padding: 10px;
                    }
                    .cyan { color: cyan; }
                    .magenta { color: magenta; }
                    .green { color: lightgreen; }
                    .yellow { color: yellow; }
                    .red { color: red; }
                    .blue { color: lightblue; }
                </style>
                <script>
                    let lastContent = '';
                    
                    function updateConsole() {
                        fetch('/output')
                            .then(response => response.text())
                            .then(text => {
                                if (text !== lastContent) {
                                    const console = document.getElementById('console');
                                    text = text
                                        .replace(/\\u001B\\[36m/g, '<span class="cyan">')
                                        .replace(/\\u001B\\[35m/g, '<span class="magenta">')
                                        .replace(/\\u001B\\[32m/g, '<span class="green">')
                                        .replace(/\\u001B\\[33m/g, '<span class="yellow">')
                                        .replace(/\\u001B\\[31m/g, '<span class="red">')
                                        .replace(/\\u001B\\[34m/g, '<span class="blue">')
                                        .replace(/\\u001B\\[0m/g, '</span>')
                                        .replace(/\\u001B\\[1m/g, '<strong>')
                                        .replace(/\\u001B\\[22m/g, '</strong>');
                                    console.innerHTML = text;
                                    lastContent = text;
                                    window.scrollTo(0, document.body.scrollHeight);
                                }
                            });
                    }
                    
                    // Update co 100ms
                    setInterval(updateConsole, 100);
                    
                    // Pierwsze pobranie
                    updateConsole();
                </script>
            </head>
            <body>
                <div id="console"></div>
            </body>
            </html>
            """;

        exchange.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");
        exchange.sendResponseHeaders(200, html.getBytes(StandardCharsets.UTF_8).length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(html.getBytes(StandardCharsets.UTF_8));
        }
    }
}