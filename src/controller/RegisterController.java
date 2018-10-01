package controller;


import java.security.NoSuchAlgorithmException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.Current;
import model.Database;
import model.Password;
import model.User;
import model.UsersBag;


public class RegisterController {
	
	@FXML private TextField firstName_field;
	@FXML private TextField lastName_field;
	@FXML private TextField username_field;
	@FXML private TextField reg_user;
	@FXML private Label statusLabel;
	@FXML private PasswordField password_field_1;
	@FXML private PasswordField password_field_2;
    String encode ;

	
	private Database dataBase = new Database();
	
	@FXML
	void registerHandle (ActionEvent event) throws Exception {
		String username = username_field.getText().trim();
		if(!Authenticate.checkRegisterFields(username_field, firstName_field, lastName_field, password_field_1, password_field_2)){
            statusLabel.setText("Check fields!"); 
		} else if(!Authenticate.checkPasswordLengthLong(password_field_1)){
			statusLabel.setText("Password length must be < 12");
		} else if(!Authenticate.checkPasswordLengthShort(password_field_1)){
			statusLabel.setText("Password length must be > 3");
		} else if(!Authenticate.checkPasswordMatch(password_field_1, password_field_2)){
			statusLabel.setText("Passwords dont match");
        } else if(username.length() > 20) {
            statusLabel.setText("Username must be < 21 characters.");
        } else {
            
            String pass = password_field_1.getText().trim();
            String firstName = firstName_field.getText().trim();
            String lastName = lastName_field.getText();
            byte[] salt = Password.getSalt();
            encode = new String(salt, "ISO-8859-1");
            String userPass = Password.get_SHA_256_SecurePassword(pass, salt);

            boolean domainValid = Authenticate.validateDomain(username);
            boolean lengthValid = Authenticate.checkLength(username);
           
            if (!domainValid) {
                statusLabel.setText("Unsupported Domain");
//            } else if(!lengthValid) {
//            	statusLabel.setText("User needs to be at least 4-12 characters long");
            }
            
//            else if(!lengthValid) {
//            	statusLabel.setText("User length too short");
//            }
            
            else {
            	
                if (dataBase.getUser(username).isEmpty()) {
                    
                	
                	User tempUser = new User(username, userPass, firstName, lastName, salt);
                   // Database dB = new Database(); 
                	
                	System.out.println("encode:" + encode);
                    dataBase.insertUser(username, userPass, firstName, lastName, encode);
                    
                    System.out.println(dataBase.getUser(username).size());
                    
                    System.out.println(dataBase.getUser(username).toString());
                    
                    
                    
//                    UsersBag.add(tempUser);
//                    UsersBag.save();
                    Current.getSession().user = username;
                    ViewNavigator.loadScreen(ViewNavigator.EmailScreen);
                } else {
                	statusLabel.setText("Username exists!");
                }
            }
        }
	}
	
	@FXML void backHandle(ActionEvent event) { 
		ViewNavigator.loadScreen(ViewNavigator.SIGN_IN);
	}
}