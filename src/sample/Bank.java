package sample;

import java.util.ArrayList;

public class Bank {
    private String numberOfCustomers;
    private final String NAMEOFBANK = "EBBERÖDS BANK";
    ArrayList<Customer> customers = new ArrayList<>();

    public Bank(){
    }
    public String getNumberOfCustomers () {
        numberOfCustomers = String.valueOf(customers.size());
        return numberOfCustomers;
    }
    public void setNumbersOfCustomers(){

        numberOfCustomers = getNumberOfCustomers() + 1;
    }
    public String getNAMEOFBANK() {
        return NAMEOFBANK;
    }
}
