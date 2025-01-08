package org.magisterium.Classes.ReflectWay;


import java.util.LinkedHashMap;
import java.util.Map;

public class MenuConstants {
    // Stałe cytaty dla dostępu do danych
    public static final String[] DATA_ACCESS_QUOTES = {
            "🔐 Dostęp do skarbca danych...",
            "📊 Panel kontrolny aktywowany...",
            "🎯 Wybierz cel swojej operacji...",
            "💫 Przygotuj się do inspekcji..."
    };

    // Ikony dla pól - stała mapa
    public static final Map<String, String> FIELD_ICONS2 = Map.of(
            "wszystkie", "⚡⚡⚡",
            "konstruktory", "⚡⚡",
            "annotacje", "⚡"
    );

    // Metoda tworząca mapę ikon pól
    static Map<String, String> createFieldIcons() {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("Saldo [Stan konta]", "1 - 💰");
        map.put("Nazwa użytkownika [Identyfikator]", "2 - 👤");
        map.put("Data utworzenia konta [Historia]", "3 - 📅");
        map.put("Hasło [Poufne]", "4 - ⚡");
        map.put("Status aktywności", "[Monitoring]");
        return map;
    }

    // Stała mapa ikon pól
    public static final Map<String, String> FIELD_ICONS = createFieldIcons();
}