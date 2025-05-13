package org.magisterium.Classes.Banks;

import org.magisterium.Annotations.BankInfo;


public class SubBank extends Bank {
    // --- nowe pola ---
    private String branchCode;               // prywatne
    protected int employeeCount;             // chronione
    public static final String SUBBANK_TYPE; // publiczne (będzie widoczne w getFields())
    static {
        SUBBANK_TYPE = "Regionalny";
    }


    public SubBank(double initialBalance, String username, String password) {
        super(initialBalance, username, password);
    }


    // --- nowa publiczna metoda ---
    public void processLoan(double amount) {
        System.out.println("Processing loan of " + amount + " PLN at branch " + branchCode);
    }

    // --- nowa prywatna metoda ---
    private double calculateInterest(double amount, double rate) {
        return amount * rate / 100.0;
    }

    // --- nadpisanie metody z Bank ---
    @Override
    public String toString() {
        return super.toString() +
                ", SubBank(branch=" + branchCode + ", employees=" + employeeCount + ")";
    }

}
