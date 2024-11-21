package org.magisterium.Classes.Banks;
import org.magisterium.Interfaces.BankAccount;

class ParaBank extends Bank implements BankAccount {
    public ParaBank(double initialBalance, String username, String password) {
        super(initialBalance, username, password);
    }
    @Override public String getAdditionalInfo() { return "Parabank - bank prywatny."; }
}

