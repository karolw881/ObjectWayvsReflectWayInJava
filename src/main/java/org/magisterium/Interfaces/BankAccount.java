package org.magisterium.Interfaces;


public interface BankAccount {
    double getBalance();
    void deposit(double amount);
    boolean withdraw(double amount);
    String getAdditionalInfo();
    boolean verifyPassword(String password); // Metoda do weryfikacji hasła
    boolean isActive(); // Metoda sprawdzająca czy konto jest aktywne

    /*
    @Override
    String toString();
    @Override
    boolean equals(Object obj);

     */
}
