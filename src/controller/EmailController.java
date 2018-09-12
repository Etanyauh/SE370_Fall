package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Email;
import model.User;


public class EmailController implements Initializable {

	@FXML private TableView inbox_table;
	@FXML private TextArea email_textarea;
	@FXML private TableColumn inbox_from;
	@FXML private TableColumn inbox_subject;
	@FXML private TableColumn inbox_date;
	private ObservableList<Email> emails;
	
	
//    @FXML
//    void nextPane(ActionEvent event) {
//        ViewNavigator.loadScreen(ViewNavigator.);
//    }

    @FXML
    void backHandle(ActionEvent event) {
            ViewNavigator.loadScreen(ViewNavigator.SIGN_IN);
    }
    
//	public TableColumn getFromColumn() {
////		inbox_from = new TableColumn("From");
//		inbox_from.setCellValueFactory(new PropertyValueFactory<User, String>("sender"));
//		return inbox_from;
//	}
//	
//	public TableColumn getSubjectColumn() {
////		inbox_subject = new TableColumn("Subject");
//		inbox_subject.setCellValueFactory(new PropertyValueFactory<User, String>("subject"));
//		return inbox_subject;
//	}
//	
//	public TableColumn getDateColumn() {
////		inbox_date = new TableColumn("Date");
//		inbox_date.setCellValueFactory(new PropertyValueFactory<User, String>("timestampPretty"));
//		return inbox_date;
//	}
//	
	

    @Override
    public void initialize(URL location, ResourceBundle resources){
		emails = FXCollections.observableArrayList(User.CurrentUser.getUser().getEmails());
		
//		inbox_from.setCellValueFactory(new PropertyValueFactory<User, String>("sender"));
//		inbox_subject.setCellValueFactory(new PropertyValueFactory<User, String>("subject"));
//		inbox_date.setCellValueFactory(new PropertyValueFactory<User, String>("timestampPretty"));
//
//		
//    	inbox_table.getColumns().addAll(inbox_from,inbox_subject,inbox_date);
    	inbox_table.setItems(emails);
    	
    }

    
    @FXML
    void emailChosen(ActionEvent event) {
    	Email object =  (Email) inbox_table.getSelectionModel().selectedItemProperty().get();
    	email_textarea.setText(object.getBody());

    }
//    @FXML
//    void  (ActionEvent event){
//           // Need to handle adding user
//        }



}