package org.magisterium.Classes.Banks;

import lombok.Getter;
import lombok.Setter;
import org.magisterium.Annotations.BankInfo;
import org.magisterium.Classes.ReflectWay.BankAccount;




import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Data;



@Data // Generuje gettery, settery, equals, hashCode i toString
@AllArgsConstructor
@Getter
@Setter


@BankInfo(
        description = "Największy bank w regionie.",
        headquarters = "Warszawa, Polska",
        capital = 5000000.0,
        aboutUs = "Oferujemy szeroką gamę usług finansowych dla klientów indywidualnych i firm.",
        name = "Bank Magisterium"
)

public class Bank  implements BankAccount {
    private double balance;
    private  String passwordHash; // Przechowujemy tylko hash hasła
    private String username;
    protected LocalDateTime accountCreationDate; // Użycie LocalDateTime
    protected boolean isActive;
    protected boolean isIndebted;
    public String string;

    /**
     *
     * @param initialBalance
     * @param username
     * @param password
     */
    public Bank(double initialBalance, String username, String password) {

        this.balance = initialBalance;
        this.username = username;
        this.passwordHash = password;
        this.accountCreationDate = LocalDateTime.now();
        this.isActive = true;
    }

    /**
     *
     * @param initialBalance
     * @param username
     * @param password
     * @param accountCreationDate
     */

    private Bank(double initialBalance, String username, String password , LocalDateTime accountCreationDate) {

        this.balance = initialBalance;
        this.username = username;
        this.passwordHash = password;
        this.accountCreationDate = LocalDateTime.now();
        this.isActive = true;
    }


    /**
     *
     * @return Bank fields
     */
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

    /**
     *
     * @param obj
     * @return
     */

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Bank other = (Bank) obj;
        return Double.compare(other.balance, balance) == 0 && Objects.equals(username, other.username);
    }


}
