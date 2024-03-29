package sample;

import com.sun.xml.internal.ws.developer.Serialization;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main extends Application {
    Stage window;
    Scene sceneLogin, sceneBank, sceneStart, sceneLoggedIn;
    ArrayList<Customer> customers = new ArrayList<>();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Bank bank = new Bank();
    Date date = new Date();
    TextField txtfldInput = new TextField();
    TextField txtfldLoggedInInput = new TextField();
    TextField txtfldFirstName = new TextField();
    TextField txtfldLastName = new TextField();
    TextField txtfldAccountNumber = new TextField();
    TextField txtfldBalance = new TextField();
    TextField txtfldDate = new TextField();
    TextField txtfldPin = new TextField();
    TextField txtfldAmount = new TextField();
    TextArea textAreaBottom = new TextArea();
    TextArea textAreaTop = new TextArea();
    TextArea textAreaTopLogIn = new TextArea();
    TextArea textAreaTopLoggedIn = new TextArea();
    TextArea textAreaBottomLogin = new TextArea();
    TextArea textAreaBottomLoggedIn = new TextArea();
    Customer customer = new Customer();
    PasswordField passwordField = new PasswordField();
    int choice = 9;
    static int accountnumber;

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;

        // Buttons
        Button btnLogin = new Button("Login");
        btnLogin.setOnAction(e -> {
            accountnumber = Integer.parseInt(txtfldInput.getText());
            accountnumber = accountnumber - 1;
            int pin = Integer.parseInt(passwordField.getText());

            try {
                if (pin == bank.customers.get(accountnumber).getPin()) {
                    textAreaTop.setText("Välkommen till " + bank.getNAMEOFBANK() + " " + bank.customers.get(accountnumber).getFirstName() + " " + bank.customers.get(accountnumber).getLastName()

                            + "\nVad vill du göra?" +
                            "\n1. Insättning\n2. Uttag\n3. Visa saldo\n4. Senaste transaktioner");
                    textAreaBottomLogin.clear();
                    txtfldInput.clear();
                    passwordField.clear();
                    window.setScene(sceneLoggedIn);
                } else {
                    textAreaBottomLogin.setText("Felaktig pinkod eller kontonummer");
                    passwordField.clear();
                }
            }catch (IndexOutOfBoundsException ex){
                textAreaBottomLogin.setText("Felaktigt kontonummer ");
            }
        });

        Button btnBank = new Button("Bank");
        btnBank.setMinWidth(120);
        btnBank.setOnAction(e -> window.setScene(sceneBank));

        Button btnCustomer = new Button("Kund");
        btnCustomer.setMinWidth(120);
        btnCustomer.setOnAction(e -> {
            Serilazation deserialization = new Serilazation();
            bank.customers = deserialization.deserialize(bank.customers, "./customers.xml");
            window.setScene(sceneLogin);
    });
        Button btnBankToLogin = new Button("Kund");
        btnBankToLogin.setMinWidth(100);
        btnBankToLogin.setOnAction(e -> window.setScene(sceneLogin));

        Button btnCustomerToBank = new Button("Bank");
        btnCustomerToBank.setMinWidth(100);
        btnCustomerToBank.setOnAction(e ->window.setScene(sceneBank));


        Button btnEnter = new Button("Enter");
        btnEnter.setMinWidth(100);
        btnEnter.setOnAction(e -> {
            try {
                choice = Integer.parseInt(txtfldLoggedInInput.getText());

                // Switch
                switch (choice){
                    case 1:
                        // Insättning
                        bank.customers.get(accountnumber).setBalance(Integer.parseInt(txtfldAmount.getText()));
                        textAreaBottomLoggedIn.setText("Insättning: " + Integer.parseInt(txtfldAmount.getText()) + " kr\nTillgängligt belopp: " + bank.customers.get(accountnumber).getBalance() + " kr");
                        String transaction = "Insättning: " + Integer.parseInt(txtfldAmount.getText()) + " kr\t" + java.time.LocalDate.now() + " " + java.time.LocalTime.now();
                        bank.customers.get(accountnumber).setTransactions(transaction);
                        txtfldAmount.clear();
                        txtfldLoggedInInput.clear();
                        break;
                    case 2:
                        // Uttag
                        if(bank.customers.get(accountnumber).getBalance() < Integer.parseInt(txtfldAmount.getText())){
                            textAreaBottomLoggedIn.setText("Uttag medges ej\nTillgängligt belopp " + customer.getBalance());
                        } else {
                            bank.customers.get(accountnumber).withdraw(Integer.parseInt(txtfldAmount.getText()));
                            textAreaBottomLoggedIn.setText("Uttag: " + Integer.parseInt(txtfldAmount.getText()) + " kr\nTillgängligt belopp: " + bank.customers.get(accountnumber).getBalance());
                             transaction = "Uttag: " + Integer.parseInt(txtfldAmount.getText()) + " kr\t\t" + java.time.LocalDate.now() + " " + java.time.LocalTime.now();
                             customer.setTransactions(transaction);
                        }
                        txtfldAmount.clear();
                        txtfldLoggedInInput.clear();
                        break;
                    case 3:
                        // visa saldo
                        textAreaBottomLoggedIn.setText("Saldo: " + bank.customers.get(accountnumber).getBalance());
                        txtfldAmount.clear();
                        txtfldLoggedInInput.clear();
                        break;
                    case 4:
                        // Visa senaste transaktionerna
                        textAreaBottomLoggedIn.setText(bank.customers.get(accountnumber).getTransactions());
                        txtfldLoggedInInput.clear();
                        txtfldAmount.clear();

                        break;
                }
            }
            catch (NumberFormatException ex) {
                textAreaBottomLoggedIn.setText(String.valueOf(choice));
            }
        });


        Button btnCreate = new Button("Skapa konto");
        btnCreate.setMinWidth(150);
        btnCreate.setOnAction(e ->{
            txtfldDate.setText(simpleDateFormat.format(date));
            Customer customer = new Customer(txtfldFirstName.getText(), txtfldLastName.getText(), Integer.parseInt(txtfldPin.getText()), (double) Integer.parseInt(txtfldBalance.getText()), Integer.parseInt(txtfldAccountNumber.getText()), txtfldDate.getText());
            bank.customers.add(customer);
            System.out.println(customer.getFirstName() + " " + customer.getLastName() + " Kundnr: " + customer.getAccountNumber() + " Saldo: " + customer.getBalance() + " kr " + "Pinkod: " + customer.getPin() + " " +customer.getCreateDate());
            txtfldAccountNumber.setText(String.valueOf(bank.customers.size() + 1));
            txtfldFirstName.clear();
            txtfldLastName.clear();
            txtfldPin.clear();
        });

        Button btnLogout = new Button("Logga ut");
        btnLogout.setOnAction(e ->{
            Serilazation serilazation = new Serilazation();
            serilazation.serialize(bank.customers, "./customers.xml");
            textAreaBottomLoggedIn.clear();
            window.setScene(sceneStart);
        });

        // Textfields
        passwordField.setPromptText("Ange pinkod");
        passwordField.setAlignment(Pos.CENTER);

        txtfldAmount.setPromptText("Ange belopp");
        txtfldAmount.setAlignment(Pos.CENTER);
        txtfldAmount.setMinWidth(150);

        txtfldInput.setPromptText("Ange kontonummer");
        txtfldInput.setMinWidth(150);
        txtfldInput.setAlignment(Pos.CENTER);

        txtfldLoggedInInput.setPromptText("Ange val");
        txtfldLoggedInInput.setMinWidth(150);
        txtfldLoggedInInput.setAlignment(Pos.CENTER);

        txtfldFirstName.setPromptText("First name");
        txtfldFirstName.setMinWidth(150);

        txtfldLastName.setPromptText("Last Name");
        txtfldLastName.setMinWidth(150);

        txtfldAccountNumber.setMinWidth(150);
        txtfldAccountNumber.setEditable(false);
        txtfldAccountNumber.setText(String.valueOf(bank.customers.size() + 1));

        txtfldDate.setMinWidth(150);
        txtfldDate.setText(simpleDateFormat.format(date));
        txtfldDate.setEditable(false);

        txtfldPin.setPromptText("Ange pinkod (4 siffror");
        txtfldPin.setMinWidth(150);

        txtfldBalance.setMinWidth(150);
        txtfldBalance.setPromptText("Saldo");


        // TextArea
        textAreaTopLogIn.setText("Logga in på följande sätt\nSätt i kort eller ange kontonummer\nAnge pinkod\nKlicka på Login");



        // Labels
        Label lblFirstName = new Label("Förnamn:");
        Label lblLastName = new Label("Efternamn:");
        Label lblAccountNumber = new Label("Kontonr:");
        Label lblDate = new Label("Datum:");
        Label lblPin = new Label("Pinkod");
        Label lblBalance = new Label("Saldo");

        // Layouts
        // Layout Start
        HBox hBoxStart = new HBox();
        hBoxStart.setAlignment(Pos.CENTER);
        hBoxStart.setSpacing(50);
        hBoxStart.getChildren().addAll(btnCustomer, btnBank);
        BorderPane borderPaneStart = new BorderPane();
        borderPaneStart.setCenter(hBoxStart);


        // Layout Bank
        VBox leftBank = new VBox();
        leftBank.setPadding(new Insets(10, 10, 10, 10));
        leftBank.getChildren().add(btnBankToLogin);

        GridPane gridBank = new GridPane();
        gridBank.setPadding(new Insets(10, 10, 10, 10));
        gridBank.setHgap(20);
        gridBank.setVgap(50);

        gridBank.setConstraints(lblFirstName, 0, 0);
        gridBank.setConstraints(lblLastName, 0, 1);
        gridBank.setConstraints(lblPin, 0, 2);
        gridBank.setConstraints(lblBalance, 0, 3);
        gridBank.setConstraints(lblAccountNumber, 0, 4);
        gridBank.setConstraints(lblDate, 0, 5);

        gridBank.setConstraints(txtfldFirstName, 1, 0);
        gridBank.setConstraints(txtfldLastName, 1, 1);
        gridBank.setConstraints(txtfldPin, 1, 2);
        gridBank.setConstraints(txtfldBalance, 1, 3);
        gridBank.setConstraints(txtfldAccountNumber, 1, 4);
        gridBank.setConstraints(txtfldDate, 1, 5);
        gridBank.setConstraints(btnCreate, 1, 6);

        gridBank.getChildren().addAll(lblFirstName, lblLastName, lblPin, lblBalance, lblAccountNumber, lblDate,
                txtfldFirstName, txtfldLastName, txtfldPin, txtfldBalance, txtfldAccountNumber, txtfldDate, btnCreate);

        BorderPane borderPaneBank = new BorderPane();
        borderPaneBank.setLeft(leftBank);
        borderPaneBank.setCenter(gridBank);

        // Layout Login
        HBox hBoxLogin = new HBox();
        hBoxLogin.setAlignment(Pos.CENTER);
        hBoxLogin.setPadding(new Insets( 10));
        hBoxLogin.getChildren().add(btnLogin);

        VBox vBoxRightLogin = new VBox();
        vBoxRightLogin.setMinWidth(120);

        VBox vBoxLeftLogin = new VBox();
        vBoxLeftLogin.setMinWidth(120);
        vBoxLeftLogin.setAlignment(Pos.TOP_CENTER);
        vBoxLeftLogin.setPadding(new Insets(10));
        vBoxLeftLogin.getChildren().add(btnCustomerToBank);

        VBox vBoxLogin = new VBox();
        vBoxLogin.getChildren().addAll(textAreaTopLogIn, textAreaBottomLogin, txtfldInput, passwordField, hBoxLogin);

        BorderPane borderPaneLogin = new BorderPane();
        borderPaneLogin.setLeft(vBoxLeftLogin);
        borderPaneLogin.setCenter(vBoxLogin);
        borderPaneLogin.setRight(vBoxRightLogin);

        //Layout LoggedIn
        HBox hBoxLoggedIn = new HBox();
        hBoxLoggedIn.setAlignment(Pos.CENTER);
        hBoxLoggedIn.setPadding(new Insets( 10));
        hBoxLoggedIn.getChildren().add(btnEnter);

        VBox vBoxRightLoggedIn = new VBox();
        vBoxRightLoggedIn.setMinWidth(120);

        VBox vBoxLeftLoggedIn = new VBox();
        vBoxLeftLoggedIn.setMinWidth(120);
        vBoxLeftLoggedIn.setAlignment(Pos.TOP_CENTER);
        vBoxLeftLoggedIn.setPadding(new Insets(10));
       vBoxLeftLoggedIn.getChildren().add(btnLogout);

        VBox vBoxLoggedIn = new VBox();
        vBoxLoggedIn.getChildren().addAll(textAreaTop, textAreaBottomLoggedIn, txtfldLoggedInInput,txtfldAmount, btnEnter);

        BorderPane borderPaneLoggedIn = new BorderPane();
        borderPaneLoggedIn.setLeft(vBoxLeftLoggedIn);
        borderPaneLoggedIn.setCenter(vBoxLoggedIn);
        borderPaneLoggedIn.setRight(vBoxRightLoggedIn);


        sceneStart = new Scene(borderPaneStart, 700, 750);
        sceneBank = new Scene(borderPaneBank, 700, 750);
        sceneLogin = new Scene(borderPaneLogin, 700, 750);
        sceneLoggedIn = new Scene(borderPaneLoggedIn, 700, 750);

        window.setTitle("Ebberöds bank");
        window .setScene(sceneStart);
        window.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
