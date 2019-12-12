package sample;

import java.io.Serializable;

public class Customer implements Serializable {
    private String firstName;
    private String lastName;
    private int accountNumber;
    private double balance;
    private String createDate;
    private String latestTransaction;
    private String transaction  = "";
    private int pin;

    // Constructors
    public Customer(){}

     public Customer (String firstName, String lastName, int pin, Double balance, int accountNumber, String createDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.accountNumber = accountNumber;
        this.createDate = createDate;
        this.latestTransaction = createDate;
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

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(double amount) {

        this.balance = balance + amount;
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
            transaction = transaction.concat(latestTransaction).concat("\n");
    }

    public Customer getCustomer (int accountNumber) {
        Bank bank = new Bank();
        return bank.customers.get(accountNumber);
    }

    public void setPin(int pin) {
                this.pin = pin;
        Main main = new Main();
        main.textAreaBottom.clear();

    }
    public int getPin(){
        return pin;
    }

    public void insert(double input){
        this.balance = balance + input;
    }

    public void withdraw(double input) {

            this.balance = balance - input;

    }
}
