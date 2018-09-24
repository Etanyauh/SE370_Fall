package controller;


import java.security.NoSuchAlgorithmException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
		if(!Authenticate.checkRegisterFields(username_field, firstName_field, lastName_field, password_field_1, password_field_2) || !Authenticate.checkPasswords(password_field_1, password_field_2)){
            statusLabel.setText("Error registering. Check fields!");   
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
            	
                if (dataBase.get(username).isEmpty()) {
                    
                	
                	User tempUser = new User(username, userPass, firstName, lastName, salt);
                   // Database dB = new Database(); 
                	
                	System.out.println("encode:" + encode);
                    dataBase.insert(username, userPass, firstName, lastName, encode);
                    
                    System.out.println(dataBase.get(username).size());
                    
                    System.out.println(dataBase.get(username).toString());
                    
                    
                    
//                    UsersBag.add(tempUser);
//                    UsersBag.save();
                    User.CurrentUser.setUser(tempUser);
                    ViewNavigator.loadScreen(ViewNavigator.EmailScreen);
                } else {
                	statusLabel.setText("Username already exists for chosen domain!");
                }
            }
        }
	}
	
	@FXML void backHandle(ActionEvent event) { 
		ViewNavigator.loadScreen(ViewNavigator.SIGN_IN);
	}
}