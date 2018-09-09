package model;

import java.io.Serializable;

public class User implements Serializable {
	private EmailsBag emails;
	private String username;
	private String passwordHash;
	private byte[] salt;

	public User(String username, String passwordHash, byte[] salt) {
		super();
		this.username = username;
		this.passwordHash = passwordHash;
		this.emails = new EmailsBag();
		this.salt = salt;
	}

	public EmailsBag getEmails() {
		return this.emails;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public byte[] getSalt() {
	    return this.salt;
    }

    public void setSalt(byte[] salt) {
	    this.salt = salt;
    }

	public String display() {
		String toString = null;
		toString = "Username: " + username + ". Password Hash: " + passwordHash;
		return toString;
	}

}
