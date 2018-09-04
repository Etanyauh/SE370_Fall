package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;

import javafx.scene.control.*;

/**
 * Controller class for the first vista.
 */
public class Vista1Controller {

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
        VistaNavigator.loadVista(VistaNavigator.VISTA_2);
    }

    @FXML
    void loginHandle(ActionEvent event) {
        statusLabel.setText("");
        String user = usernameField.getText();
        String pw = passwordField.getText();
        //boolean isUser = authenticateUser(user, pw);
        if(user == "Dylan"){
            statusLabel.setText("User: "+user+" Pass: "+pw);
            //VistaNavigator.loadVista();
        } else {
            statusLabel.setText("User: "+user+" Pass: "+pw+"   Field(s) are empty");
        }
    }

        @FXML
        void registerHandle (ActionEvent event){
            VistaNavigator.loadVista((VistaNavigator.RegisterScreen));
        }



}