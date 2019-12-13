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

    public void setNumberOfCustomers(String numberOfCustomers) {
        this.numberOfCustomers = numberOfCustomers;
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(ArrayList<Customer> customers) {
        this.customers = customers;
    }

    public String getNAMEOFBANK() {
        return NAMEOFBANK;
    }
}
