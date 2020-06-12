

package NewBank;
/* 
    CA2 OOP module
    Dorset College 2020
    William Souza
    20268
   */        

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class Clients {
    private String firstName;
    private String lastName;
    private String accountNumber;
    private byte pinHash[]; 
    private ArrayList<Account> accounts;
    private String accountType;
      
    public Clients(String firstName, String lastName, String pin, Matrix_Activities Branch)
    {
        // Set user's name
        this.firstName = firstName;
        this.lastName = lastName;
        
       
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            this.pinHash = md.digest(pin.getBytes());
        } catch (NoSuchAlgorithmException e) {
         JOptionPane.showMessageDialog(null, "error, caught NoSuchAlgorithmException");
           e.printStackTrace();
           System.exit(1);
        }
         //Get a new, unique universal ID for the user
        this.accountNumber = Branch.getAccountNumber(firstName, lastName);
        // Create empty list of accounts
        this.accounts = new ArrayList<Account>();
        
      
    }

    Clients() {
        
    }

    Clients(String accountNumber, int accountType) {
        this.accountNumber = accountNumber;
        this.accounts= new ArrayList<>();
        if(accountType==1){this.accountType="savings";}
        else{this.accountType="current";}
    }
    // Add an account for the user
    // @param anAcct the account to add
    public void addAccount(Account anAcct){
        this.accounts.add(anAcct);
    }
    
    // Return the user's UUID
    // @return the uuid
     
    public String getAccountNumber(){
        return this.accountNumber;
    }
    
    // Check whether a given pin matches the true Clients pin
    // @param aPin the pin to check
    // @return whether the pin is valid or not
    
    public boolean validatePin(String aPin){
        return true;
}

    public String getFirstName() {
        return this.firstName;
    }
    String getLastName() {
        return this.lastName;
    }
        
    public int numAccounts() {
        return this.accounts.size();
    }
    
    public void printAcctTransHistory(int acctIdx) {
        this.accounts.get(acctIdx).printTransHistory();
    }
    
    public double getAcctBalance(int acctIdx) {
        return this.accounts.get(acctIdx).getBalance();
    }
    
    public void addAcctTransaction(int fromAcct, double d, String format) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    void setAccountNumber(String accountNumber) {
        this.accountNumber=accountNumber;
    }

    String getAccountType() {
        return this.accountType;
    }
}

