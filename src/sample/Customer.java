package sample;

import java.io.Serializable;
import java.time.LocalTime;

public class Customer implements Serializable {
    private String firstName;
    private String lastName;
    private String accountNumber;
    private String balance = "";
    private String createDate;
    private String latestTransaction;
    private String transaction  = "";
    private String pin;

    // Constructors
    public Customer(){}

     public Customer (String firstName, String lastName, String pin, String balance, String accountNumber, String createDate, String latestTransaction) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.accountNumber = accountNumber;
        this.createDate = createDate;
        this.transaction = "Insättning: " + latestTransaction + " kr " + createDate + "\n";
        this.pin = pin;
        this.balance = balance;

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getBalance() {
        return balance;
    }
    public void setBalance(String balance) {
        this.balance = balance;
    }

    public void insert(String amount) {
        double tempAmount = Double.parseDouble(amount);
        double tempBalance = Double.parseDouble(balance);
        double newBalance = tempBalance + tempAmount;
        this.balance = String.valueOf(newBalance);
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getTransactions() {
        return transaction;
    }

    public void setTransactions(String latestTransaction) {
            transaction = transaction.concat("\n").concat(latestTransaction);
    }

    public void setPin(String pin) {
        this.pin = pin;

    }
    public String getPin(){
        return pin;
    }

    public void withdraw(String input, String balance) {

        double tempInput = Double.parseDouble(input);
        double tempBalance = Double.parseDouble(balance);
        double newBalance = tempBalance - tempInput;

            this.balance = String.valueOf(newBalance);

    }
}
