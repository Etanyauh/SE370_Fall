package model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Database {

	private String userName;
	private String passWord;
	private String firstName;
	private String lastName;


	public Database(){
		try {
			createTable();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Database(String user,String pass,String first,String last){
		this.userName = user;
		this.passWord = pass;
		this.firstName = first;
		this.lastName = last;
	}

	public void createTable() throws Exception{
		try {
			String email = "CREATE TABLE IF NOT EXISTS email(user varchar(255) NOT NULL,password varchar(255),first varchar(255),last varchar(255),salt varchar(255), PRIMARY KEY(user))";
			String inbox = "CREATE TABLE IF NOT EXISTS inbox(from,to,)";
			String outbox = "";
			String draft = "";
			
			
			Connection con = getConnection();
			PreparedStatement create = con.prepareStatement(email);
			create.executeUpdate();

		}catch(Exception e) {
			e.printStackTrace();
		}
		//		finally {
		//			System.out.println("Created Table");
		//		}

	}

	public void insert(String user,String pass,String first,String last,String salt) throws Exception{


		try {
			String sql = "INSERT INTO email (user,password,first,last,salt) VALUES ('"+user+"','"+pass+"', '"+first+"',  '"+last+"' , '"+salt+"'  )";
			Connection con = getConnection();
			PreparedStatement insert = con.prepareStatement(sql);
			insert.executeUpdate();

		}catch(Exception e) {
			e.printStackTrace();
		}
		//		finally {
		//			System.out.println("Insert Complete");
		//		}


	}


	//	public String getUser() throws Exception{
	//		try {
	//			
	//			Connection con = getConnection();
	//			PreparedStatement get = con.prepareStatement("SELECT ")
	//			
	//		}catch (Exception e) {
	//			e.printStackTrace();
	//		}
	//		finally {
	//			System.out.println("got user");
	//		}
	//		
	//		return null;
	//	}


	public ArrayList<String> get(String user) throws Exception{
		try {

			Connection con = getConnection();

			String sqlStatement = "SELECT * FROM email WHERE user =" + "'" + user + "'"; 

			PreparedStatement statement = con.prepareStatement(sqlStatement);
			ResultSet result = statement.executeQuery();

			ArrayList<String> array = new ArrayList<String>();
			while(result.next()) {
				//System.out.print(result.getString("user") + " ");
				//System.out.println(result.getString("password"));
				array.add(result.getString("user"));
				array.add(result.getString("first"));
				array.add(result.getString("password"));
				array.add(result.getString("last"));
				array.add(result.getString("salt"));
			}
			System.out.println("All records have been selected");
			return array;

		}catch(Exception e) {
			e.printStackTrace();
		}
		//		finally {
		//			System.out.println("Insert Complete");
		//		}
		return null;
	}


	/**
	 * gets the connection from mysql
	 * @return
	 * @throws Exception
	 */
	public Connection getConnection() throws Exception{
		try{
			String driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/test?autoReconnect=true&useSSL=false&verifyServerCertificate=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
			String username = "root";
			String password = "cs370";
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url,username,password);
			//System.out.println("Connected");
			return conn;
		} catch(Exception e){System.out.println(e);}


		return null;
	}

//	public static User search(String username) {
//		for (User u : users) {
//			if (u.getUsername().equalsIgnoreCase(username)) {
//				return u;
//			}
//		}
//		return null;
//	}

	public String toString() {
		return userName + " " + passWord + " " + firstName + " " + lastName;
	}


}
