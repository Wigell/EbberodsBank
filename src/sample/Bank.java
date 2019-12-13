package sample;

import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

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

    public boolean checkFields(String firstName, String lastName, String pin,  String balance) {

        if(firstName.trim().isEmpty() || lastName.trim().isEmpty() || pin.trim().isEmpty() || balance.trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information saknas");
            alert.setHeaderText(null);
            alert.setContentText("Kontollera att alla fält är ifyllda");
            alert.showAndWait();
            return false;
        }
        return true;
    }
}
