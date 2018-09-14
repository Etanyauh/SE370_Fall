package controller;


import java.security.NoSuchAlgorithmException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
	
	
	@FXML
	void registerHandle (ActionEvent event) throws NoSuchAlgorithmException {
		
        if(!Authenticate.checkRegisterFields(username_field, firstName_field, lastName_field, password_field_1, password_field_2) || !Authenticate.checkPasswords(password_field_1, password_field_2)){
            statusLabel.setText("Error registering. Check fields!");
        } else {
            String username = username_field.getText().trim();
            String pass = password_field_1.getText().trim();
            String firstName = firstName_field.getText().trim();
            String lastName = lastName_field.getText();
            byte[] salt = Password.getSalt();
            String userPass = Password.get_SHA_256_SecurePassword(pass, salt);

            boolean domainValid = Authenticate.validateDomain(username);
            if (!domainValid) {
                statusLabel.setText("Unsupported Domain");
            } else {
                if (!UsersBag.exists(username)) {
                    User tempUser = new User(username, userPass, firstName, lastName, salt);
                    UsersBag.add(tempUser);
                    UsersBag.save();
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