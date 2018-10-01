package controller;

import com.mysql.cj.conf.StringProperty;

import javafx.beans.property.SimpleStringProperty;

public class MessageDetails {
	private final SimpleStringProperty name;
	private final SimpleStringProperty subject;
	private final SimpleStringProperty date;
	public final long timestamp;

	public MessageDetails(String name, String subject, String date, long stamp){
		this.name = new SimpleStringProperty(name);
		this.subject = new SimpleStringProperty(subject);
		this.date = new SimpleStringProperty(date);
		this.timestamp = stamp;
		
	}
	

	
	public String getFrom() {
		return name.get();
	}
	
	public String getSubject() {
		return subject.get();
	}
	
	public String getDate() {
		return date.get();
	}
	

	public void setFrom(String val){
		name.set(val);
	}
	
	public void setSubject(String val){
		subject.set(val);
	}
	
	public void setDate(String val){
		date.set(val);
	}
	

	
	public SimpleStringProperty nameProperty(){
		return name;
	}
	
	public SimpleStringProperty subjectProperty(){
		return subject;
	}
	
	public SimpleStringProperty dateProperty(){
		return date;
	}
}

