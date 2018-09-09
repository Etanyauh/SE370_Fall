package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import model.*;
import javafx.scene.control.*;

import java.security.NoSuchAlgorithmException;

/**
 * Controller class for the first vista.
 */
public class SigninController {

    boolean authenticateUser(String user, String pw) {
        boolean status = false;
        if(user != "" && pw != "") {
            status = true;
        }

        return status;
    }

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Label statusLabel;
    /**
     * Event handler fired when the user requests a new vista.
     *
     * @param event the event that triggered the handler.
     */
    @FXML
    void nextPane(ActionEvent event) {
        ViewNavigator.loadScreen(ViewNavigator.TEMPLATE);
    }

    @FXML
    void loginHandle(ActionEvent event) {
        if(!Authenticate.checkFields(usernameField, passwordField)){
            statusLabel.setText("Error in username or password field");
        } else {
            String user = usernameField.getText();
            String pw = passwordField.getText();

            //boolean isUser = authenticateUser(user, pw);
            if (UsersBag.exists(user)) {
                //statusLabel.setText("User: "+user+" Pass: "+pw);
                //ViewNavigator.loadVista();
                User temp = UsersBag.search(user);
                if (temp.getPasswordHash().equals(Password.get_SHA_256_SecurePassword(pw, temp.getSalt()))) {
                    System.out.println("Password Authenticated:");
                    System.out.println("User: "+user);
                    System.out.println("Password Hash: "+temp.getPasswordHash());
                    ViewNavigator.loadScreen(ViewNavigator.EmailScreen);
                }
                else {
                    System.out.println("Password Not Authenticated:");
                    System.out.println("User: "+user);
                    System.out.println("Password Hash: "+temp.getPasswordHash());
                    System.out.println("Entered Password: "+ Password.get_SHA_256_SecurePassword(pw, temp.getSalt()));
                    statusLabel.setText("Incorrect Password");
                }
            } else {
                statusLabel.setText("Username does not exist");
            }
        }
    }

        @FXML
        void registerHandle (ActionEvent event) throws NoSuchAlgorithmException {
            if(!Authenticate.checkFields(usernameField, passwordField)){
                statusLabel.setText("Error in username or password field");
            } else {
                String user = usernameField.getText();
                String pass = passwordField.getText();
                byte[] salt = Password.getSalt();
                String userPass = Password.get_SHA_256_SecurePassword(pass, salt);

                boolean domainValid = Authenticate.validateDomain(user);
                if (!domainValid) {
                    statusLabel.setText("Unsupported Domain");
                } else {
                    if (!UsersBag.exists(user)) {
                        User tempUser = new User(user, userPass, salt);
                        UsersBag.add(tempUser);
                        UsersBag.save();
                        ViewNavigator.loadScreen((ViewNavigator.EmailScreen));
                    }
                }

            }
        }





}