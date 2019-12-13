package sample;

import java.util.ArrayList;

public class Bank {
    private int numberOfCustomers;
    private final String NAMEOFBANK = "EBBERÖDS BANK";
    ArrayList<Customer> customers = new ArrayList<>();

    public Bank(){
    }
    public int getNumberOfCustomers () {
        numberOfCustomers = customers.size();
        return numberOfCustomers;
    }
    public int setNumbersOfCustomers(){
        numberOfCustomers = getNumberOfCustomers() + 1;
        return numberOfCustomers;
    }
    public String getNAMEOFBANK() {
        return NAMEOFBANK;
    }
}
