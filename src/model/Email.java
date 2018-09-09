package model;

import java.text.DateFormat;

public class Email {
	private String subject;
	private String timestamp;
	private String body; 
	private String sender;
	private long stamp;

	public Email(String sender, String subject, String body){
		this.stamp = System.currentTimeMillis();
		this.timestamp = DateFormat.getDateTimeInstance().format(this.stamp);  
	}
	
	public void setBody(String body){
		this.body = body;
	}
	
	public void setSubject(String sub){
		this.subject = sub;
	}
	
	public long getStamp() {
		return this.stamp;
	}
	public String getSender() {
		return this.sender;
	}
	public String getSubject() {
		return this.subject;
	}
	public String getTimeStamp() {
		return this.timestamp;
	}
	public String getBody() {
		return this.body;
	}
	
}
