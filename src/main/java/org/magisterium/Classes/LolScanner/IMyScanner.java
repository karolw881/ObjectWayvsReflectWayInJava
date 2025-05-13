package org.magisterium.Classes.LolScanner;
interface IMyScanner {
    /**
     * Uruchamia główną pętlę programu, wyświetlając menu i obsługując wybory użytkownika.
     */
    void run();
    /**
     * Normalizuje wybór użytkownika do standardowego formatu.
     * @param input String zawierający wybór użytkownika (może zawierać kropkę i nawiasy).
     * @return znormalizowany string ("1", "2", "0" lub "" dla błędnego wyboru).
     */
    String getNormalizedChoice(String input);
    /**
     * Normalizuje podany string.
     * @param input String do normalizacji.
     * @return znormalizowany string.
     */
    String Normalize(String input);
}