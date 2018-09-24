package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import model.*;
import javafx.scene.control.*;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

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
    private Database dataBase = new Database();
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
    void loginHandle(ActionEvent event) throws Exception {
        if(!Authenticate.checkSignInFields(usernameField, passwordField)){
            statusLabel.setText("Error in username or password field");
        } else {
            String user = usernameField.getText();
            String pw = passwordField.getText();

            //boolean isUser = authenticateUser(user, pw);
            
            
            
            ArrayList<String> ary = dataBase.get(user);
        
            System.out.println(ary.get(0));
            
            if(ary.get(0).equals(user)) {
            //if (Database/UsersBag.exists(user)) {
                //statusLabel.setText("User: "+user+" Pass: "+pw);
                //ViewNavigator.loadVista();
               
            	//User temp = UsersBag.search(user);
            	String tempSalt = ary.get(4);
            	byte[] decode = tempSalt.getBytes("ISO-8859-1");
//            	System.out.println(x);
            	
            	
            
            	
            	System.out.println("decode:" + tempSalt);
                if (ary.get(2).equals(Password.get_SHA_256_SecurePassword(pw, decode))) {
                    //User.CurrentUser.setUser(temp);
                    // -----------------------------
//                    Email emailTest = new Email();
//                    emailTest.setSender("joe@yg.com");
//                    emailTest.setBody("This is a test email, hope it finds you well!");
//                    emailTest.setSubject("Test");
//                    emailTest.setRecipient(temp.getUsername());
//                    
//                    User.CurrentUser.getUser().resetEmails();
//                    User.CurrentUser.getUser().addEmail(emailTest);
                   // UsersBag.save();
                    
                	System.out.println("Password Authenticated:");
                    System.out.println("User: "+user);
                    //System.out.println("Password Hash: "+temp.getPasswordHash());
                    ViewNavigator.loadScreen(ViewNavigator.EmailScreen);
                }
                else {
                    System.out.println("Password Not Authenticated:");
                    System.out.println("User: "+user);
                  //  System.out.println("Password Hash: "+temp.getPasswordHash());
                  //  System.out.println("Entered Password: "+ Password.get_SHA_256_SecurePassword(pw, temp.getSalt()));
                    statusLabel.setText("Incorrect Password");
                }
            } else {
                statusLabel.setText("Username does not exist");
            }
        }
    }

        @FXML
        void registerHandle (ActionEvent event) throws NoSuchAlgorithmException {
        	ViewNavigator.loadScreen(ViewNavigator.RegisterScreen);
        }
             





}