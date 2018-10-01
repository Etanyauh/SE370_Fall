package controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Current;
import model.Database;
import model.Email;

public class ComposeController implements Initializable {

	@FXML private TableView<MessageDetails> inbox_table;
	@FXML private TableView<MessageDetails> outbox_table;
	@FXML private TableView<MessageDetails> drafts_table;
	@FXML private TableColumn<MessageDetails,String> inbox_from;
	@FXML private TableColumn<MessageDetails,String> inbox_subject;
	@FXML private TableColumn<MessageDetails,String> inbox_date;
	@FXML private TableColumn<MessageDetails,String> outbox_to;
	@FXML private TableColumn<MessageDetails,String> outbox_subject;
	@FXML private TableColumn<MessageDetails,String> outbox_date;
	@FXML private TableColumn<MessageDetails,String> drafts_to;
	@FXML private TableColumn<MessageDetails,String> drafts_subject;
	@FXML private TableColumn<MessageDetails,String> drafts_date;
	@FXML private TextArea email_textarea;
	@FXML private TextField to_field;
	@FXML private TextField subject_field;
	@FXML private Label compose_label;
	@FXML private Label username_label;
	private ObservableList<MessageDetails> data;
	private ObservableList<MessageDetails> data2;
	private ObservableList<MessageDetails> data3;
    private Database dataBase = new Database();

	
    @FXML
    void backHandle(ActionEvent event) {
            ViewNavigator.loadScreen(ViewNavigator.SIGN_IN);
    }
    
    @FXML
    void composeHandle(ActionEvent event) {
//    		ViewNavigator.loadScreen(ViewNavigator.COMPOSE);
//    		email_textarea.setEditable(true);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources){
    	username_label.setText(Current.getSession().user);
    	inbox_table.setRowFactory(tv ->{
    		TableRow<MessageDetails> row = new TableRow<>();
    	    row.setOnMouseClicked(event -> {
    	    	if (!row.isEmpty() && event.getClickCount() >= 1) {
    	    		MessageDetails email = row.getItem();
    	    		emailChosen(email);
    	    		ViewNavigator.loadScreen(ViewNavigator.EmailScreen);

    	    	}
    	    });
    	    return row ;
    	 });
    	
    	
    	outbox_table.setRowFactory(tv ->{
    		TableRow<MessageDetails> row = new TableRow<>();
    	    row.setOnMouseClicked(event -> {
    	    	if (!row.isEmpty() && event.getClickCount() >= 1) {
    	    		MessageDetails email = row.getItem();
    	    		emailChosen(email);
    	    		ViewNavigator.loadScreen(ViewNavigator.EmailScreen);

    	    	}
    	    });
    	    return row ;
    	 });
    	
    	drafts_table.setRowFactory(tv ->{
    		TableRow<MessageDetails> row = new TableRow<>();
    	    row.setOnMouseClicked(event -> {
    	    	if (!row.isEmpty() && event.getClickCount() >= 1) {
    	    		Current.getSession().compose_type = "drafts";
    	    		MessageDetails email = row.getItem();
    	    		emailChosen(email);
    	    		ViewNavigator.loadScreen(ViewNavigator.COMPOSE);
    	    	}
    	    });
    	    return row ;
    	 });
    	
    	// -------------------------
    	System.out.println("Current User: "+Current.getSession().user);
    	System.out.println("Current compose_type: "+Current.getSession().compose_type);
    	loadTables();
    	switch(Current.getSession().compose_type){
    		case "forward":
	    		loadForward();
	        	break;

	    	case "reply":
	    		loadReply();
	        	break;

	    	case "new":
	    		loadNew();
	    		break;
	    	case "drafts":
	    		loadDrafts();
    	}
    
    }

    
    @FXML 
    void sendMessage(ActionEvent event) throws Exception {
    	String to = to_field.getText();
    	String subject = subject_field.getText();
    	String message = email_textarea.getText();
    	
    	if (to.trim().isEmpty()){
    		compose_label.setText("No recipient chosen");
    		return;
    	} else if (dataBase.getUser(to).isEmpty()){
			compose_label.setText("Recipient not found!");
			return;
    	}
    	if(subject.trim().isEmpty()) {
    		subject = "";
    	}
    	
    	if(Current.getSession().compose_type.equals("reply")){
    		message = message+"\n\n ---- Original Message -----\n\n"+ Current.getSession().formatted_mail;
    	} else if(Current.getSession().compose_type.equals("forward")){
    		message = Current.getSession().formatted_mail;
    	}
    	
//    	if (Current.getSession().is_draft == 1){
//    		dataBase.removeEmail();
//    	}
    	try {
			dataBase.insertEmail(Current.getSession().user,to,subject,message,System.currentTimeMillis(),0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	ViewNavigator.loadScreen(ViewNavigator.EmailScreen);

    }
   
    @FXML void saveToDrafts(ActionEvent event) {
    	try {
			dataBase.insertEmail(Current.getSession().user,to_field.getText(),subject_field.getText(),email_textarea.getText(),System.currentTimeMillis(),1);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	ViewNavigator.loadScreen(ViewNavigator.EmailScreen);
    }
    
    
    @FXML void cancel(ActionEvent event){
    	ViewNavigator.loadScreen(ViewNavigator.EmailScreen);
    	Current.getSession().compose_type = "";
    }
    
    void emailChosen(MessageDetails m){
    	MessageDetails object =  m;// (MessageDetails) inbox_table.getSelectionModel().selectedItemProperty().get();
    	Connection connect;
		ResultSet rs = null;
			try {
				connect = dataBase.getConnection();
				rs = connect.createStatement().executeQuery("SELECT * FROM inbox WHERE stamp = "+object.timestamp);
			} catch (Exception e) {
				e.printStackTrace();
			}
	    	try {
	    		while(rs.next()){
	    			String formatted_message = formatEmail(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(5),rs.getString(4));
	    			email_textarea.setText(formatted_message);
	    			Current.getSession().sender = rs.getString(1);
	    			Current.getSession().recipient = rs.getString(2); 
	    			Current.getSession().subject = rs.getString(3);
	    			Current.getSession().message = rs.getString(4);
	    			Current.getSession().stamp = Long.parseLong(rs.getString(5));
	    			Current.getSession().is_draft = rs.getInt(6);
	    			Current.getSession().formatted_mail = formatted_message;
	    			System.out.println("Draft bit: "+Current.getSession().is_draft);
	    		}
			} catch (SQLException e) {
				e.printStackTrace();
			}
    }
    
    
    public String formatEmail(String from,String to, String subject, String date1, String body){
    	long tempL = Long.parseLong(date1);
    	Date date = new Date(tempL);
        SimpleDateFormat df2 = new SimpleDateFormat("MM/dd/yy @ HH:mm:ss");
        String dateText = df2.format(date);
    	String temp = "From:\t"+from+"\n"
    				 +"To:\t\t"+to+"\n"
    				 +"Date:\t"+dateText+"\n"
    				 +"Subject:\t"+subject+"\n"
    				 +"\n"
    				 +body;
    	return temp;
    }
    
    public void loadForward(){
    	email_textarea.setEditable(false);
    	email_textarea.setText(Current.getSession().formatted_mail);
    	to_field.setText("");
    	to_field.setEditable(true);

    	subject_field.setText("FWD:<"+Current.getSession().subject+">");
    	subject_field.setEditable(false);

    }
    
    // -- Will load an email differently depending on what type of email it is. --
    public void loadReply() {
    	email_textarea.setEditable(true);
    	
    	to_field.setText(Current.getSession().sender);
    	to_field.setEditable(false);
    	
    	subject_field.setText("Reply:<"+Current.getSession().subject+">");
    	subject_field.setEditable(false);
    }
    
    public void loadNew() {
    	email_textarea.setEditable(true);
    	to_field.setText("");
    	to_field.setEditable(true);

    	subject_field.setText("");
    	subject_field.setEditable(true);

    }
    public void loadDrafts() {
    	email_textarea.setEditable(true);
    	email_textarea.setText(Current.getSession().message);
    	
    	to_field.setText(Current.getSession().recipient);
    	to_field.setEditable(true);

    	subject_field.setText(Current.getSession().subject);
    	subject_field.setEditable(true);
    }
    
    
    // This method will populate the inbox, outbox, and drafts tables.
    public void loadTables(){
    	// -- inbox
    	data = FXCollections.observableArrayList();
    	Connection connect;
    	try {
			connect = dataBase.getConnection();
			int draft_bit = 0;
	    	ResultSet rs = connect.createStatement().executeQuery("SELECT * FROM inbox WHERE recipient = '"+Current.getSession().user+"'AND is_draft="+draft_bit);
	    	while (rs.next()) {
	    		data.add(new MessageDetails(rs.getString(1),rs.getString(3),DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT).format(Long.parseLong(rs.getString(5))),Long.parseLong(rs.getString(5))));
	    	}	
	    	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	inbox_from.setCellValueFactory(new PropertyValueFactory<>("name"));
    	inbox_subject.setCellValueFactory(new PropertyValueFactory<>("subject"));
    	inbox_date.setCellValueFactory(new PropertyValueFactory<>("date"));
    	inbox_table.setItems(data);
    	
    	// --- outbox
    	data2 = FXCollections.observableArrayList();
    	Connection connect2;
    	try {
			connect2 = dataBase.getConnection();
			int draft_bit = 0;
	    	ResultSet rs2 = connect2.createStatement().executeQuery("SELECT * FROM inbox WHERE sender = '"+Current.getSession().user+"' AND is_draft ="+draft_bit);
	    	while (rs2.next()) {
	    		data2.add(new MessageDetails(rs2.getString(2),rs2.getString(3),DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT).format(Long.parseLong(rs2.getString(5))),Long.parseLong(rs2.getString(5))));
	    	}
	    	
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	outbox_to.setCellValueFactory(new PropertyValueFactory<>("name"));
    	outbox_subject.setCellValueFactory(new PropertyValueFactory<>("subject"));
    	outbox_date.setCellValueFactory(new PropertyValueFactory<>("date"));
    	outbox_table.setItems(data2);

    	// --- drafts
    	data3 = FXCollections.observableArrayList();
    	Connection connect3;
    	try {
			connect3 = dataBase.getConnection();
			int draft_bit = 1;
	    	ResultSet rs3 = connect3.createStatement().executeQuery("SELECT * FROM inbox WHERE sender = '"+Current.getSession().user+"' AND is_draft="+draft_bit);
	    	while (rs3.next()) {
	    		data3.add(new MessageDetails(rs3.getString(2),rs3.getString(3),DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT).format(Long.parseLong(rs3.getString(5))),Long.parseLong(rs3.getString(5))));
	    	}
	    	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	drafts_to.setCellValueFactory(new PropertyValueFactory<>("name"));
    	drafts_subject.setCellValueFactory(new PropertyValueFactory<>("subject"));
    	drafts_date.setCellValueFactory(new PropertyValueFactory<>("date"));
    	drafts_table.setItems(data3);


    }
}
