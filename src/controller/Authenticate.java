package controller;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.UsersBag;

import javax.xml.soap.Text;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Authenticate {

    public static boolean validateDomain(String username){
//        domains.add("yg.com");
//        domains.add("lnb.com");
//        domains.add("qc.edu");
        Pattern domain_pattern = Pattern.compile("^[a-zA-Z0-9._%+-]+((@yg.com)|(@lnb.gov)|(@qc.edu))$");
        Matcher domain_matcher = domain_pattern.matcher(username);
        if (domain_matcher.find()) {
        	return true;
        }
       return false;
    }

    
    
    public static boolean checkSignInFields(TextField userField, PasswordField passwordField) {
    	if (userField.getText().trim().isEmpty() || passwordField.getText().isEmpty()) {
    		System.out.println("One or more fields empty");
            return false;
        }
		System.out.println("Fields pass!");
        return true;
    }
    
    public static boolean checkRegisterFields(TextField userField, TextField firstName, TextField lastName, PasswordField pw1, PasswordField pw2) {
        if (userField.getText().trim().isEmpty() ||firstName.getText().trim().isEmpty() ||lastName.getText().trim().isEmpty() || pw1.getText().isEmpty() || pw2.getText().isEmpty()) {
    		System.out.println("One or more fields empty");
            return false;
        }
		System.out.println("Fields pass!");
        return true;
    }
    
    public static boolean checkLength(String user) {
    
    	return (user.length() < 4 || user.length() > 12) ? true: false;
    
    }
    
    
    public static boolean checkPasswords(PasswordField p1, PasswordField p2) {
    	String pw1 = p1.getText();
    	String pw2 = p2.getText();
    	if(pw1.length() < 4 || pw1.length() > 12){
    		System.out.println("Password is not within allowed length range!");
    		return false;
    	}
    	if(!(pw1.equals(pw2))){
    		System.out.println("Passwords dont match!");
    		return false;
    	}
    	System.out.println("Passwords match!");
    	return true;
    }

}
