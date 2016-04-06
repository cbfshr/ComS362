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
	
	// After this, open MySQL Command Line Client and run the following queries:
	// CREATE DATABASE MusicLibrary;
	// connect MusicLibrary;
	// CREATE TABLE PlaylistInfo (
	// ID int NOT NULL AUTO_INCREMENT,
	// PlaylistName varchar(30) NOT NULL,
	// PRIMARY KEY (ID)
	// );

	// CREATE TABLE Artists (
	// ID int NOT NULL AUTO_INCREMENT,
	// ArtistName varchar(50) NOT NULL,
	// PRIMARY KEY (ID)
	// );
	
	// CREATE TABLE Albums (
	// ID int NOT NULL AUTO_INCREMENT,
	// AlbumName varchar(50) NOT NULL,
	// PRIMARY KEY (ID)
	// );
	
	// CREATE TABLE Songs (
	// ID int NOT NULL AUTO_INCREMENT,
	// ArtistID int,
	// AlbumID int,
	// PRIMARY KEY (ID)
	// );
	
	// CREATE TABLE PlaylistContent (
	// ID int NOT NULL
	// );
	
	// Obviously, if we add tables for the PlaylistSongs, Artists, Albums, etc.,
	// then all 3 of us have to do this, unless we are able to figure out
	// a way to do it programmatically. I think doing it programmatically could work
	// by checking to see if a table exists. If the table does not exist, then the program
	// can run the query to create the necessary tables.
	
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost/MusicLibrary";

	//  Database credentials
	static final String USER = "root";
	static final String PASS = "ComS362!";
	
	Connection conn = null;
	Statement stmt = null;
	
	public Connection ConnectToDatabase() {
		if(conn != null) {
			return conn;
		}
		
		try{
			//STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");
	
			//STEP 3: Open a connection
			System.out.println("Connecting to a selected database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("Connected database successfully...");
			return conn;
		} catch(SQLException se) {
			//Handle errors for JDBC
			se.printStackTrace();
		} catch(Exception e) {
			//Handle errors for Class.forName
			e.printStackTrace();
		}
		
		return conn;
	}
} //end DatabaseConnection
