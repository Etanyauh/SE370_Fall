package model;

import java.io.Serializable;
import java.text.DateFormat;

public class Email implements Serializable {
	private String subject;
	private String pretty_stamp;
	private String body; 
	private String recipient;
	private String sender;
	private long raw_stamp;

	public Email(){
		this.raw_stamp = System.currentTimeMillis();
		this.pretty_stamp = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT).format(this.raw_stamp);  
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getTimestampPretty() {
		return pretty_stamp;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public long getRawStamp() {
		return raw_stamp;
	}
	
	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}


}
