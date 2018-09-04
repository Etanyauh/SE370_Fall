package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * Controller class for the first vista.
 */
public class RegisterController {

    void authenticateUser(String user, String pw) {

    }

    @FXML private TextField reg_user;
    @FXML private PasswordField reg_pw1;
    @FXML private PasswordField reg_pw2;
    @FXML private Label statusLabel;
    /**
     * Event handler fired when the user requests a new vista.
     *
     * @param event the event that triggered the handler.
     */
    @FXML
    void nextPane(ActionEvent event) {
        VistaNavigator.loadVista(VistaNavigator.VISTA_2);
    }

    @FXML
    void backHandle(ActionEvent event) {
            VistaNavigator.loadVista(VistaNavigator.VISTA_1);
    }


    @FXML
    void registerHandle (ActionEvent event){
           // Need to handle adding user
        }



}