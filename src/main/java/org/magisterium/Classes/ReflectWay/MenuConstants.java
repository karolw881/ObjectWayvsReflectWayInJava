package org.magisterium.Classes.ReflectWay;


import java.util.LinkedHashMap;
import java.util.Map;

public class MenuConstants {
    // Stałe cytaty dla dostępu do danych
    public static final String[] DATA_ACCESS_QUOTES = {
            "🔐 Dostęp do skarbca danych...\n",
            "📊 Panel kontrolny aktywowany...\n",
            "🎯 Wybierz cel swojej operacji...\n",
            "💫 Przygotuj się do inspekcji...\n"
    };

    // Ikony dla pól - stała mapa
    public static final Map<String, String> FIELD_ICONS2 = Map.of(
            "wszystkie", "⭐",
            "konstruktory", "⭐",
            "annotacje", "⭐"
    );

    // Metoda tworząca mapę ikon pól
    static Map<String, String> createFieldIcons() {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("Saldo [Stan konta]", "1 - 💰");
        map.put("Nazwa użytkownika [Identyfikator]", "2 - 👤");
        map.put("Data utworzenia konta [Historia]", "3 - 📅");
        map.put("Hasło [Poufne]", "4 - ⚡");
        map.put("Status aktywności", "5 - [Monitoring]");
        return map;
    }


}