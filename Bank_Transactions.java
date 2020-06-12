
package NewBank;
/* 
    CA2 OOP module
    Dorset College 2020
    William Souza
    20268
   */
import java.time.LocalDate;


public class Bank_Transactions {
    private double amount;
    private LocalDate timestamp; 
    private String memo;
    private Account inAccount; 
    
    public Bank_Transactions (double amount, Account inAccount, String memo, LocalDate timestamp) {
        this.amount = amount;
        this.inAccount = inAccount;
        this.timestamp = timestamp;
        this.memo = memo;
    }
    
    
    
    public double getAmount() {
        return this.amount;
    }
    
    public String getSummary() {
        if(this.amount >= 0) {
            return String.format("%s : $%.02f : %s", this.timestamp.toString(), this.amount, this.memo);
        } else {
            return String.format("%s : $(%.02f) : %s",
                    this.timestamp.toString(), -this.amount, this.memo);
        }
    }   
}
