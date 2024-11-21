package org.magisterium.Classes.Banks;

import org.magisterium.Interfaces.BankAccount;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.magisterium.Annotations.BankInfo;

@Data // Generuje gettery, settery, equals, hashCode i toString
@AllArgsConstructor
@BankInfo(description = " Parent class of banks ")
public class Bank  implements BankAccount {
    private double balance;
    private  String passwordHash; // Przechowujemy tylko hash hasła
    private String username;
    private  double ukBalance;

    protected long clientId;
    protected String email;
    protected String mothersMaidenName;
    protected LocalDateTime accountCreationDate; // Użycie LocalDateTime
    protected boolean isActive;
    protected boolean hasLoan;
    protected boolean hasCredit;
    protected boolean isIndebted;

    public Bank(double initialBalance, String username, String password) {
        this.balance = initialBalance;
        this.username = username;
        this.passwordHash = password;
        this.accountCreationDate = LocalDateTime.now();
        this.isActive = true;
    }


    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return "Bank{" +
                "balance=" + balance +
                ", username='" + username + '\'' +
                ", accountCreationDate=" + accountCreationDate.format(formatter) +
                ", isActive=" + isActive +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Bank other = (Bank) obj;
        return Double.compare(other.balance, balance) == 0 && Objects.equals(username, other.username);
    }

    @Override
    public double getBalance() {
        return 0;
    }

    @Override
    public void deposit(double amount) {

    }

    @Override
    public boolean withdraw(double amount) {
        return false;
    }

    @Override
    public String getAdditionalInfo() {
        return null;
    }

    @Override
    public boolean verifyPassword(String password) {
        return false;
    }

    @Override
    public boolean isActive() {
        return false;
    }
}
