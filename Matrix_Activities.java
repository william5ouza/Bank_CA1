
package NewBank;
/* 
    CA2 OOP module
    Dorset College 2020
    William Souza
    20268
   */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Matrix_Activities {

    public Matrix_Activities(String name) {
        this.Name = name;
        this.Users = new ArrayList<Clients>();
        this.Accounts = new ArrayList<Account>();

        //create customers.txt file
        try {
            String my_File = new File(".").getCanonicalPath() + "/src/NewBank/customers.txt";
            File file = new File(my_File);
            boolean newFile = file.createNewFile();
            if (newFile) {
                FileWriter Writer = new FileWriter(my_File, true);
                BufferedWriter FileWrt = new BufferedWriter(Writer);

               FileWrt.write("First Name\tLast Name\tAccount Number\tAccount Type");
                FileWrt.newLine();
                FileWrt.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //get pin from account number
    static String getPin(String accountNumber) {
       
        String pin = "";
        for (int i = 0, hyphenIndex = 2; i < accountNumber.length(); i++) {
            if (accountNumber.charAt(i) == '-') {
                hyphenIndex--;
            }
            if (hyphenIndex == 0) {
                pin = accountNumber.substring(++i);
                break;
            }
        }

        return pin;
    }

    //get account number from firstname and last Name
    static String getAccountNumber(String fName, String lName) {
        String accountNumber=String.valueOf(fName.charAt(0)) + String.valueOf(lName.charAt(0)) + "-"
                + String.valueOf(fName.concat(lName).length())
                + "-"
                + String.valueOf((fName.toLowerCase().charAt(0) - 'a') + 1)
                + "-"
                + String.valueOf((lName.toLowerCase().charAt(0) - 'a') + 1);
        return accountNumber;
    }

    private String Name;

    private ArrayList<Clients> Users;

    private ArrayList<Account> Accounts;

    public Clients addUser(Clients user) {
        //create and add savings
        Account savings = new Account(user.getFirstName(), user.getLastName(), user, this, "savings");
        addAccount(savings, user);
        //create and add current
        Account current = new Account(user.getFirstName(), user.getLastName(), user, this, "current");
        addAccount(current, user);
        //write to customer.txt
        try {
            FileWriter writer = new FileWriter(new File(".").getCanonicalPath() + "/src/NewBank/customers.txt", true);
            BufferedWriter fileWriter = new BufferedWriter(writer);

            fileWriter.write(user.getFirstName() + "\t" + user.getLastName() + "\t" + user.getAccountNumber() + "\t" + "savings");
            fileWriter.newLine();
            fileWriter.write(user.getFirstName() + "\t" + user.getLastName() + "\t" + user.getAccountNumber() + "\t" + "current");
            fileWriter.newLine();
            fileWriter.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        return user;

    }

    public boolean userLogin(String accountNumber, String pin) {

        //get each line from file
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(".").getCanonicalPath() + "/src/NewBank/customers.txt"));

            String line;
            //look for the user acount number
            while ((line = reader.readLine()) != null) {
                if (null != line && line.contains(accountNumber)) {
                    return true;
                }
            }
            reader.close();
        } catch (IOException ex) {
            Logger.getLogger(Matrix_Activities.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        return false;
    }

   public void addAccount(Account newAccount, Clients user) {
        //files created
        try {
            //create new account
            String newAccountName = new File(".").getCanonicalPath() + "/src/NewBank/" + newAccount.getName();
            File file = new File(newAccountName);
            boolean newFile = file.createNewFile();
            if (!newFile) {
                
                 FileWriter writer = new FileWriter(newAccountName, true);
                BufferedWriter bufferedWriter = new BufferedWriter(writer);

                bufferedWriter.write("tAmount\tDate");
                bufferedWriter.newLine();
                bufferedWriter.close();
                //add the first transaction
                newAccount.addTransaction(0.00, " New " + " Account "+ newAccount.getName(),  file);
             
            }else{
                
              System.out.println("the " + newAccount.getType() + "account already exists");  
          
                
                
            }
            
        } catch (Exception e) {
                e.printStackTrace();       
                //write to the new file
               
        }
    }

    public String getName() {
        return Name;
    }

    Staff employeeLogin(String password) {
        if ("a1234".equals(password.toLowerCase())) {
            return new Staff(this);
        }
        return null;
    }
}
