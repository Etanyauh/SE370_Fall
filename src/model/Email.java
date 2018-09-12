package model;

import java.io.Serializable;
import java.text.DateFormat;

public class Email implements Serializable {
	private String subject;
	private String timestampPretty;
	private String body; 
	private String recipient;
	private String sender;
	private long stampUgly;

	public Email(){
		this.stampUgly = System.currentTimeMillis();
		this.timestampPretty = DateFormat.getDateTimeInstance().format(this.stampUgly);  
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getTimestampPretty() {
		return timestampPretty;
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

	public long getStampUgly() {
		return stampUgly;
	}
	
	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}


}
