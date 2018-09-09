package model;

import java.io.File; 
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

//import controller.Current;

public class EmailsBag implements Serializable {
	
	private static EmailsBag emailsBag = new EmailsBag();
	public static ArrayList<Email> emails = new ArrayList<>();
	
	public EmailsBag() {
	}

	public static void add(Email email) {
		emails.add(email);
	}
	
	public static void displayEmailsBag() {
		for (Email u: emails) {
			System.out.println(u.toString());
		}
	}


	public static EmailsBag searchBySender(String sender) {
		EmailsBag emailsFromX = new EmailsBag();
		for (Email ev: emails) {
			if (ev.getSender() == sender) {
				emailsFromX.add(ev);
			}
		}
		return emailsFromX;
	}
	
	
	
	public static void delete(Email em) {
		emails.remove(em);
	}

	
	public static void save() {
		try {
			FileOutputStream fileOutStream = new FileOutputStream("data"+File.separator+"EmailsBag.ser");
			ObjectOutputStream objectOutStream = new ObjectOutputStream(fileOutStream);
			objectOutStream.writeObject(EmailsBag.emails);
			objectOutStream.flush();
			objectOutStream.close();
			fileOutStream.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	public static void load() throws ClassNotFoundException, FileNotFoundException{
		try {
			FileInputStream fileInStream = new FileInputStream("data"+File.separator+"EmailsBag.ser");
			ObjectInputStream objectInStream = new ObjectInputStream(fileInStream);
			EmailsBag.emails = (ArrayList<Email>) objectInStream.readObject();
			objectInStream.close();
			fileInStream.close();
		} catch(IOException ioe) {
//			ioe.printStackTrace();	
		}
	}

}
