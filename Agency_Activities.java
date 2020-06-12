

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
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;



public class Agency_Activities {

    public static void Branch_activities() {
        boolean runBank = false;
        // init Matrix_Activities 
        Matrix_Activities Branch = new Matrix_Activities("");
        
        
        Clients User_Client;
        Staff User_Employee;
        do {
           Scanner sc = new Scanner(System.in);
            /* login as a customer or employee
            System.out.println(
                    "press 1 if employee.");
            
            System.out.println(
                    "press 2 if customer.");
            
            System.out.println(
                    "press 3 to exit");
           */
            String user;
              user = JOptionPane.showInputDialog("Press 1 if employee.\nPress 2 if customer.\nPress 3 to exit" );
            switch (user) {
                case "1":
                    
                    User_Employee = Agency_Activities.mainEmployeePrompt(Branch, sc);
                    Agency_Activities.printEmployeeMenu(User_Employee, sc, Branch);
                    break;

                case "2":
                   
                    User_Client = Agency_Activities.mainMenuPrompt(Branch, sc);
                   
                    Agency_Activities.userMenu(User_Client, sc);
                    break;
                case "3":
                    JOptionPane.showMessageDialog(null, "Bye\n" + "See you soon");
                    //System.out.println("Bye");
                    runBank = true;
                    break;
                default:
                     JOptionPane.showMessageDialog(null,"Invalid option!\n" +"Please try again.");
                    //System.out.println("Invalid option, please try again.");
                    break;
            }
        } while (!runBank);
    }

    public static Clients mainMenuPrompt(Matrix_Activities Branch, Scanner sc) {

        // init
       //String accNumber;
      // String pin;
       //String fName; 
       //String lName;
       String response;
        
        boolean login_User;
        Clients User = new Clients();

        do {
            response = JOptionPane.showInputDialog("Enter client First Name:");
            //System.out.print("Enter client First Name: ");
            String fname = response;
            //fName = sc.nextLine();
            response = JOptionPane.showInputDialog("Enter client Last Name:");
            //System.out.print("Enter client Last Name: ");
            String lName = response;
            //System.out.print("Enter Account Number: ");
            response = JOptionPane.showInputDialog("\"Enter Account Number:");
            String accNumber = response;
            response = JOptionPane.showInputDialog("Enter your pin:");
            //System.out.print("Enter pin: ");
            String pin = response;

            login_User = Branch.userLogin(accNumber, pin);
            if (login_User == false) {
                JOptionPane.showMessageDialog(null, "Incorrect account/PIN\n" + "Please try again");
                //System.out.println("Incorrect account/pin combination. " + "Please try again. ");
            } else {
                User = new Clients(accNumber, 1);
                login_User = true;
            }
        } while (login_User == false);
        return User;
    }

    public static Staff mainEmployeePrompt(Matrix_Activities Branch, Scanner sc) {
        // init
        String PIN;
       String response;
        Staff login_Employee = new Staff(Branch);

       
        do {
            response = JOptionPane.showInputDialog("Welcome\n"+"Enter your  password");
            //System.out.println("Welcome");
            //System.out.print("Enter your  password");
            //System.out.println("");
            PIN = response;

           
            login_Employee = Branch.employeeLogin(PIN);
            if (login_Employee == null) {
                JOptionPane.showMessageDialog(null, "Incorrect PIN\n" + "Please try again");
                //System.out.println("Incorrect PIN " + "Please try again");
            }
        } while (login_Employee == null); 
        return login_Employee;
    }

    public static void userMenu(Clients User, Scanner sc) {
       
        int choice;
        String response = JOptionPane.showInputDialog("Welcome, please select one option\n" + 
                "1) Show account statement\n"+
                "2) Make a withdraw\n" +
                "3) Make a deposit\n" +
                "Press 4 to exit\n" );
        // user menu
        do {
         
            choice = Integer.parseInt(response);
            
            if (choice < 1 || choice > 4) {
                JOptionPane.showMessageDialog(null,"Invalid option");
            }
        } while (choice < 1 || choice > 4);

      
        switch (choice) {

            case 1:
                Agency_Activities.Statement(User, sc);
                break;

            case 2:
                Agency_Activities.user_Withdraw(User, sc);
                break;

            case 3:
                Agency_Activities.Deposit(User, sc);
                break;

            case 4:
               
                sc.nextLine();
                break;
        }

       
        if (choice != 4) {
            Agency_Activities.userMenu(User, sc);
        }
    }

    public static void printEmployeeMenu(Staff theEmployee, Scanner sc, Matrix_Activities Branch) {
        // init 
        int choice;
        String response = JOptionPane.showInputDialog("Please select one of the following options\n" +
                                                        "1) Create account\n"+
                                                        " 2) Make a withdraw\n"+
                                                        " 3) Make a Deposit\n"
                
                                                        +" 4) Delete an account\n"
                                                        +" 5) Quit" );
        // employee menu
        do {
            /*
            System.out.println(" 1) Create account");
            System.out.println(" 2) Make a withdraw");
            System.out.println(" 3) Make a Deposit");
            System.out.println(" 4) Delete an account");
            System.out.println(" 5) Quit");
            System.out.println();
            System.out.print("Enter choice: ");
            */
            choice = Integer.parseInt(response);

            if (choice < 1 || choice > 5) {
                JOptionPane.showMessageDialog(null,"Invalid choice. Please choose 1-5");
            }
        } while (choice < 1 || choice > 5);

        // process the choice
        switch (choice) {

            case 1:
                Agency_Activities.createUser(sc, Branch);
                break;

            case 2:
                Agency_Activities.user_Withdraw(Agency_Activities.getUser(sc), sc);
                break;

            case 3:
                Agency_Activities.Deposit(Agency_Activities.getUser(sc), sc);
                break;

            case 4:
                Agency_Activities.deleteUser(Agency_Activities.getUser(sc));
                break;

            case 5:
        
                sc.nextLine();
                break;
        }

     
        if (choice != 5) {
            Agency_Activities.printEmployeeMenu(theEmployee, sc, Branch);
        }
        
    }

    public static void Statement(Clients User, Scanner sc) {
        String accountType = "";
        do {
            
            String response = JOptionPane.showInputDialog("Enter the account type 'savings' or 'current'");
            //System.out.printf("Enter the account type 'savings' or 'current'");
            //System.out.println();
            accountType = response;
            
            if ("savings".equals(accountType) || "current".equals(accountType)) {
                try {
                    
                    String filename = new File(".").getCanonicalPath() + "/src/NewBank/" + User.getAccountNumber() + "-" + accountType + ".txt";
                    FileReader Reader = new FileReader(filename);
                    BufferedReader bufferedReader = new BufferedReader(Reader);

                    String line = "";
                    while ((line = bufferedReader.readLine()) != null) {
                        //display each line
                        // JOptionPane.showMessageDialog(null,line);
                         
                         StringBuilder s = new StringBuilder();
                    while ((line = bufferedReader.readLine()) != null) {
                                s.append(line+"\t \n");
                    }

                        JOptionPane.showMessageDialog(null, s);
                    }
                    Reader.close();
                    break;

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(null,"Invalid input, please choose savings or current");
                //System.out.println();
            }
        } while (!"savings".equals(accountType) || !"current".equals(accountType));

    }


    private static void createUser(Scanner sc, Matrix_Activities Branch) {
        String F_Name = "", L_Name = "";
        String response = JOptionPane.showInputDialog("Enter Client's First name:\n");
        
        //System.out.println("Enter Client's First name: ");
        F_Name = response;
        
        response = JOptionPane.showInputDialog("Enter Client's Last name:\n");
        //System.out.println("Enter Client's Last name: ");
        L_Name = response;
       JOptionPane.showMessageDialog(null,"Client created\n"+ "Thank you");


       
        Clients newUser = Branch.addUser(new Clients(F_Name, L_Name, Matrix_Activities.getPin(Matrix_Activities.getAccountNumber(F_Name, L_Name)), Branch));

   
        Account new_SavingsAcc = new Account(F_Name, L_Name, newUser, Branch, Account.SAVINGS);
        Account new_CurrentAcc = new Account(F_Name, L_Name, newUser, Branch, Account.CURRENT);
        newUser.addAccount(new_SavingsAcc);
        newUser.addAccount(new_CurrentAcc);
        Branch.addAccount(new_SavingsAcc, newUser);
        Branch.addAccount(new_CurrentAcc, newUser);
       
        
    }

    private static void user_Withdraw(Clients User, Scanner sc) {
        
        //sc.nextLine();
        if (User == null) {
            JOptionPane.showMessageDialog(null,"This user does not exist.");
        } else {
            boolean correct = false;
            //String choice = "";
            do {
               String response = JOptionPane.showInputDialog("Enter Client's Last name:\n");
                response = User.getAccountType();
            if (response.equals("savings") || response.equals("current")) {
                    correct = true;
            double amount;
            do {
            sc.nextLine();
                       response=  JOptionPane.showInputDialog("How much would you like to withdraw? "+User.getAccountType()+"account");
                        amount = Double.parseDouble(response);
            if (amount > 0) {
            Account userAccount = new Account(User, User.getAccountType());
            User.addAccount(userAccount);
                            try {
                                if (getBalance(User) - amount < 0) {
                                    JOptionPane.showMessageDialog(null,"Insufficient funds, please try again. ");
                                    return;
                                }
                                userAccount.addTransaction(getBalance(User) - amount, "withdraw" + amount, new File(new File(".").getCanonicalPath() + "/src/NewBank/" + User.getAccountNumber() + "-"+User.getAccountType()+".txt"));
                                System.out.println(amount+" withdrawn!");
                            } catch (IOException e) {
                            }
                        }

                    } while (amount < 0);
                } else {
                    JOptionPane.showMessageDialog(null,"Invalid Account");
                    return;
                }
            } while (!correct);
        }
    }

    private static Clients getUser(Scanner sc) {
        String response = JOptionPane.showInputDialog("Enter Account Number: ");
        String accountNumber = "";
        int accountType = 0;
        
        System.out.println("Enter Account Number: ");
        accountNumber = response;
        response = JOptionPane.showInputDialog("Savings or current, press 1 for savings 2 for current");
        
        //System.out.println("Savings or current, press 1 for savings 2 for current");
        accountType = Integer.parseInt(response);
        try {
            BufferedReader Reader = new BufferedReader(new FileReader(new File(".").getCanonicalPath() + "/src/NewBank/customers.txt"));

            String line;
            while ((line = Reader.readLine()) != null) {
                if (null != line
                        && line.contains(accountNumber)) {
                    return new Clients(accountNumber, accountType);
                }
            }
            Reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void Deposit(Clients User, Scanner sc) {
        if (User == null) {
            JOptionPane.showMessageDialog(null,"this user does not exist");
        } else {
            boolean correct = true;
            String choice = "";
           do {
                String response = JOptionPane.showInputDialog("Deposit\n"+ 
                        "Enter the account type 'savings' or 'current'");
                //System.out.println("Please select in which account you want to make a deposit");
                response = User.getAccountType();
                if (response.equals("savings") || response.equals("current")) {
                    correct = true;
                    double amount;
                    do {
                        //sc.nextLine();
            response = JOptionPane.showInputDialog("How much to deposit in " +
                    User.getAccountType()+ " account");
                        //System.out.println("How much to deposit in "+ User.getAccountType()+" account");
                        amount = Double.parseDouble(response);
                        if (amount > 0) {
                            Account userAccount = new Account(User, User.getAccountType());
                            User.addAccount(userAccount);
                            try {
                                userAccount.addTransaction(amount + getBalance(User), "deposit " + amount, new File(new File(".").getCanonicalPath() + "/src/NewBank/" + User.getAccountNumber() + "-"+User.getAccountType()+".txt"));
                               JOptionPane.showMessageDialog(null, amount + " was deposited successfully in your "+ User.getAccountType()+"!");
                            } catch (IOException e) {
                            }
                        }

                    } while (amount < 0);
                } else {
                    JOptionPane.showMessageDialog(null,"Invalid account");
                    return;
                }
            } while (!correct);

        }
    }

    private static void deleteUser(Clients user) {
        try {
            //delete the customers file
            new File(new File(".").getCanonicalPath() + "/src/NewBank/" + user.getAccountNumber() + "-" + user.getAccountType() + ".txt").delete();
        } catch (IOException ex) {
            Logger.getLogger(Agency_Activities.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            
            //copy and paste to new file except the customer to be deleted
            BufferedReader reader = new BufferedReader(new FileReader(new File(new File(".").getCanonicalPath() + "/src//NewBank/customers.txt")));
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File(new File(".").getCanonicalPath() + "/src/NewBank/customers-past.txt")));

            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                if (null != currentLine && !currentLine.contains(user.getAccountNumber() + "\t" + user.getAccountType())) {
                    writer.write(currentLine + System.getProperty("line.separator"));
                }
            }
            writer.close();
            reader.close();
            
            
            if(new File(new File(".").getCanonicalPath() + "/src/NewBank/customers.txt").exists()){
                JOptionPane.showMessageDialog(null, new File(new File(".").getCanonicalPath() + "/src/NewBank/customers.txt").delete());
                if(new File(new File(".").getCanonicalPath() + "/src/NewBank/customers-past.txt").exists()){
                 JOptionPane.showMessageDialog(null,new File(new File(".").getCanonicalPath() + "/src/NewBank/customers-past.txt").renameTo(new File(new File(".").getCanonicalPath() + "/src/NewBank/customers.txt")));
           }
           }
           
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    private static double getBalance(Clients user) {

        double balance = 0.0;
        try {
            FileReader reader = new FileReader(new File(".").getCanonicalPath() + "/src/NewBank/" + user.getAccountNumber() + "-" + user.getAccountType() + ".txt");
            BufferedReader bufferedReader = new BufferedReader(reader);

            String fileContent = " ";
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                fileContent += line;
            }

       
            String[] fileArray = fileContent.split("\\s+");
            balance = Double.parseDouble(fileArray[fileArray.length - 3]);
            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return balance;
    }
}
