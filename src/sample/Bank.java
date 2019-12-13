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

    public boolean checkFields() {
        Main main = new Main();
        if(main.txtfldFirstName.getText().trim().isEmpty() | main.txtfldLastName.getText().trim().isEmpty() | main.txtfldPin.getText().trim().isEmpty() | main.txtfldBalance.getText().trim().isEmpty()) {
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
