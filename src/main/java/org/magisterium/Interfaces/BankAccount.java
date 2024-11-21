package org.magisterium.Interfaces;


public interface BankAccount {
    double getBalance();
    void deposit(double amount);
    boolean withdraw(double amount);
    String getAdditionalInfo();
    boolean verifyPassword(String password);
    boolean isActive();


}
