package sample;

import javafx.collections.FXCollections;
import javafx.scene.control.TableView;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Serilazation {
    public void serialize(ArrayList<Customer> customers, String fileName) {

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File("./customers.xml"));
            XMLEncoder xmlEncoder = new XMLEncoder(fileOutputStream);
            xmlEncoder.writeObject(customers);
            xmlEncoder.close();
            fileOutputStream.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<Customer> deserialize(ArrayList<Customer> customers, String fileName) {
        try

    {
        FileInputStream fileInputStream = new FileInputStream(new File("./customers.xml"));
        XMLDecoder xmlDecoder = new XMLDecoder(fileInputStream);
        customers = (ArrayList<Customer>) xmlDecoder.readObject();
        xmlDecoder.close();
        fileInputStream.close();
    } catch(
    IOException ex)

    {
        ex.printStackTrace();
    }
        return customers;

    }
}
