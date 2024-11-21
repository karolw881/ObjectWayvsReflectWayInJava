package org.magisterium.Classes.Banks;

import org.magisterium.Interfaces.BankAccount;

class StateBank extends Bank implements BankAccount {
    public StateBank(double initialBalance, String username, String password) {
        super(initialBalance, username, password);
    }
    @Override public String getAdditionalInfo() { return "Bank Państwowy - bank kontrolowany przez państwo."; }
}