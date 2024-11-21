package org.magisterium.Classes.LolScanner;

import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;

public class FontSize {
    // Unicode block elements for text expansion
    private static final char[] BLOCK_ELEMENTS = {
            '░', '▒', '▓', '█'
    };

    // ANSI color codes for variety
    private static final Ansi.Color[] COLORS = {
            Ansi.Color.GREEN,
            Ansi.Color.YELLOW,
            Ansi.Color.BLUE,
            Ansi.Color.MAGENTA,
            Ansi.Color.CYAN,
            Ansi.Color.RED
    };

    private double scaleFactor;

    public FontSize(double scaleFactor) {
        if (scaleFactor <= 0) {
            throw new IllegalArgumentException("Scale factor must be greater than 0.");
        }
        this.scaleFactor = scaleFactor;
    }

    public String enlarge(String text) {
        AnsiConsole.systemInstall();
        StringBuilder enlargedText = new StringBuilder();

        AnsiConsole.systemUninstall();
        return enlargedText.toString();
    }

    public void setScaleFactor(double scaleFactor) {
        if (scaleFactor <= 0) {
            throw new IllegalArgumentException("Scale factor must be greater than 0.");
        }
        this.scaleFactor = scaleFactor;
    }
}