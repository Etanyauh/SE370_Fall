package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class UsersBag{
	
	private static UsersBag usersBag = new UsersBag();
	public static ArrayList<User> users = new ArrayList<>();

	public UsersBag() {
	}

	public static void add(User user) {
		users.add(user);
	}

	public static User search(String username) {
		for (User u : users) {
			if (u.getUsername().equalsIgnoreCase(username)) {
				return u;
			}
		}
		return null;
	}

	public static boolean exists(String username) {
		for (User u : users) {
			if (u.getUsername().equalsIgnoreCase(username)) {
				return true;
			}
		}
		return false;
	}


	public static void delete(User user) {
		users.remove(user);
	}
	
	public static UsersBag getInstance() {
		if(usersBag == null) {
			usersBag = new UsersBag();
		}
		return usersBag;
	}

	public static void displayUsersBag() {
		for (User u: users) {
			System.out.println(u.toString());
		}
	}
	
	public static void save() {
		try {
			FileOutputStream fileOutStream = new FileOutputStream(System.getProperty("user.dir")+"/data"+ File.separator+"UsersBag.ser");
			ObjectOutputStream objectOutStream = new ObjectOutputStream(fileOutStream);
			objectOutStream.writeObject(UsersBag.users);
			objectOutStream.flush();
			objectOutStream.close();
			fileOutStream.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	public static void load() throws ClassNotFoundException, FileNotFoundException{
		try {
			File usersFile = new File(System.getProperty("user.dir")+"/data"+ File.separator+"UsersBag.ser");
			if (usersFile.length() == 0) {
				System.out.println("Creating New File...");
				save();
			}
			else {
			FileInputStream fileInStream = new FileInputStream(System.getProperty("user.dir")+"/data"+ File.separator+"UsersBag.ser");
			ObjectInputStream objectInStream = new ObjectInputStream(fileInStream);
			UsersBag.users = (ArrayList<User>) objectInStream.readObject();
			objectInStream.close();
			fileInStream.close();
			}
		} catch(IOException ioe) {
//			System.out.println("File is empty");
//			ioe.printStackTrace();	
		}
			
	}
}
