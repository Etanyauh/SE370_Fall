package model;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
	private ArrayList<Email> emails;
	private String username;
	private String passwordHash;
	private byte[] salt;

	
	public static class CurrentUser {
		private static User currentUser;
		
		public static void setUser(User user){
			currentUser = user;
		}
		
		public static  User getUser(){
			return currentUser;
		}
	}
	
	
	public User(String username, String passwordHash, byte[] salt) {
		super();
		this.username = username;
		this.passwordHash = passwordHash;
		this.emails = new ArrayList<>();
		this.salt = salt;
	}

	
	public void resetEmails(){
		this.emails = new ArrayList<Email>();
	}
	
	
	public ArrayList<Email> getEmails() {
		return this.emails;
	}
	
	public void addEmail(Email email) {
		this.emails.add(email);
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
