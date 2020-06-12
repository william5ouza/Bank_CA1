

package NewBank;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Account {

    private String name; // The name of the account
    private double balance; // The current balance of the account
    private String type; // The account type savings or current
    private Clients holder; // The user object that ows this account
    private ArrayList<Bank_Transactions> transactions; // The list of transactions for this accounts
    public static final String SAVINGS = "savings";
    public static final String CURRENT = "current";

    public Account(String fName, String lName, Clients holder, Matrix_Activities Branch, String type) {
        // Ste the account name and holder
        this.name = holder.getAccountNumber() + "-" + type + ".txt";
        this.holder = holder;

        // Get new account UUID
        this.type = type;

        // Init transactions
        this.transactions = new ArrayList<Bank_Transactions>();

    }

    Account(Clients user, String type) {
        this.holder = user;
        this.type = type;
        // Init transactions
        this.transactions = new ArrayList<Bank_Transactions>();
    }

    public String getType() {
        return this.type;
    }

    public double getBalance() {
        double balance = 0;
        for (Bank_Transactions t : this.transactions) {
            balance += t.getAmount();
        }
        return balance;
    }

    public void printTransHistory() {

        // System.out.printf("\nTransaction history for account %s\n", this.uuid);
        for (int t = this.transactions.size() - 1; t >= 0; t--) {
            JOptionPane.showMessageDialog(null, this.transactions.get(t).getSummary());
        }
        //System.out.println();
    }

    public void addTransaction(double amount, String memo, File file) {

        // create new transaction object and add it to our list
        Bank_Transactions newTrans = new Bank_Transactions(amount, this, memo, LocalDate.now());
        this.transactions.add(newTrans);
        //log it in the transaction file
        try {
            FileWriter writer = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write(" " + amount + "\t" + LocalDate.now().toString() + "\t" + memo);
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (IOException e) {

        }
        //add transaction
    }

    public String getName() {
        return this.name;
    }
}
