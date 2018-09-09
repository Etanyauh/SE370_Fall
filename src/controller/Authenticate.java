package controller;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.UsersBag;

import javax.xml.soap.Text;
import java.util.ArrayList;
import java.util.List;

public class Authenticate {
    public static List<String> domains = new ArrayList<>();

    public static boolean validateDomain(String username){
        domains.add("yg.com");
        domains.add("lnb.com");
        domains.add("qc.edu");
        boolean isValid = false;
        for(String item: domains) {
            if(username.contains(item)) {
                isValid = true;
                break;
            }
        }
        return isValid;
    }

    public static boolean checkFields(TextField userField, PasswordField passwordField) {
        if (userField.getText().isEmpty() || userField.getText().trim().isEmpty() || passwordField.getText().isEmpty()) {
            return false;
        }
        return true;
    }

}
