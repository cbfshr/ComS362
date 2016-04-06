package Database;

import java.sql.*;

public class DatabaseConnection {
	// In order for this to work...
	// Make sure that mysql-connector-java-3.1.14-bin.jar is in the Build Path
	// Right click on the project
	// Build Path -> Configure Build Path -> Add External JARs -> Select mysql-connector-java-3.1.14-bin.jar
	
	// Next, download the MySQL Installer for Windows:
	// http://dev.mysql.com/downloads/mysql/
	// (There is probably another way to do this on Mac or Linux)
	// Run the installer and follow all the steps
	// Set the root password to be: ComS362!
	
	// After this, open MySQL Command Line Client and run the following script:
	// The path should be where the script is located on your computer.
	// This script will create the database, tables, and insert data
	// SOURCE D:\Projects\Eclipse\ComS362\DatabaseScript.sql
	
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost/MusicLibrary";

	// MySQL Database credentials
	static final String USER = "root";
	static final String PASS = "ComS362!";
	
	Connection connection = null;
	Statement statement = null;
	
	public Connection ConnectToDatabase() {
		// If we are already connected, don't attempt to connect again
		if(connection != null) {
			return connection;
		}
		
		try{
			// Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");
	
			// Connect to the database
			System.out.println("Connecting to a selected database...");
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("Connected database successfully...");
			return connection;
		} catch(SQLException sqlException) {
			sqlException.printStackTrace();
		} catch(Exception e) {
			//Handle errors for Class.forName
			e.printStackTrace();
		}
		
		return connection;
	}
}
