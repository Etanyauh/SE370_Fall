package model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Database {



//	public static void main(String[] args) throws Exception {
//
//		createTable();
//		//getConnection();
//		//insert("afd","sdf","sdfsd","wef");
//		get();
//	}


	public static void createTable() throws Exception{
		try {

			Connection con = getConnection();
			PreparedStatement create = con.prepareStatement("CREATE TABLE IF NOT EXISTS email(user varchar(255) NOT NULL,password varchar(255),first varchar(255),last varchar(255),PRIMARY KEY(user))");
			create.executeUpdate();

		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			System.out.println("Created Table");
		}

	}

	public static void insert(String user,String pass,String first,String last) throws Exception{
		
		
		try {

			Connection con = getConnection();
			PreparedStatement insert = con.prepareStatement("INSERT INTO email (user,password,first,last) VALUES ('"+user+"','"+pass+"', '"+first+"',  '"+last+"'  )");
			insert.executeUpdate();

		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			System.out.println("Insert Complete");
		}


	}


	public static ArrayList<String> get() throws Exception{
		try {

			Connection con = getConnection();
			PreparedStatement statement = con.prepareStatement("SELECT * FROM email");
			
			ResultSet result = statement.executeQuery();
			
			ArrayList<String> array = new ArrayList<String>();
			while(result.next()) {
				System.out.print(result.getString("user") + " ");
				System.out.println(result.getString("password"));
				
				array.add(result.getString("last"));
			}
			System.out.println("All records have been selected");
			return array;

		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			System.out.println("Insert Complete");
		}
		return null;
	}
	

	/**
	 * gets the connection from mysql
	 * @return
	 * @throws Exception
	 */
	public static Connection getConnection() throws Exception{
		try{
			String driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/test?autoReconnect=true&useSSL=false&verifyServerCertificate=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
			String username = "root";
			String password = "cs370";
			Class.forName(driver);

			Connection conn = DriverManager.getConnection(url,username,password);
			System.out.println("Connected");
			return conn;
		} catch(Exception e){System.out.println(e);}


		return null;
	}





}
