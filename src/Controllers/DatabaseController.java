package Controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Database.DatabaseConnection;
import Interfaces.DatabaseInterface;
import Objects.Album;
import Objects.Artist;
import Objects.Playlist;
import Objects.Song;

public class DatabaseController implements DatabaseInterface {
	Connection database;
	Statement statement;
	
	public DatabaseController() {
		// Initialize connection to the database
		DatabaseConnection databaseConnection = new DatabaseConnection();

		// Connect to the database
		database = databaseConnection.ConnectToDatabase();
	}
	
	@Override
	public Song getSong(int songID, int playlistID) {
		try {
			statement = database.createStatement();

			ResultSet results = statement.executeQuery("SELECT * FROM Playlist WHERE playlistID = " +playlistID +" AND songID = " +songID);
			Song song = new Song(results.getString(2));
			return song;
			// database.query("SELECT * FROM Playlist WHERE playlistID = playlistID AND songID = songID")
			// Song song = new Song(songID, playlistID);
			// return song;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Artist getArtist(int artistID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Playlist getPlaylist(int playlistID) {
		try {
			statement = database.createStatement();
			
			// Check that the playlist exists
			ResultSet playlistInfo = statement.executeQuery("SELECT * FROM PlaylistInfo WHERE playlistID = " +playlistID);

			// If the playlist does not exist, the query results will be empty
			if(!playlistInfo.next()) {
				return null;
			}
			
			// To create the playlist, we need the name, which we can grab 
			Playlist playlist = new Playlist(playlistInfo.getString(2));

			// Get all the songs in the playlist
			// This will return a bunch of tuples that we can build into the list of songs in the playlist
			// results = database.query("SELECT * FROM Playlist WHERE playlistID = playlistID");
			
			// To create the list of songs...
			// Check the syntax necessary for this
			// for(tuple t : results) {
			//		playlist.add(new Song(t.songID, t.songName);
			// }

			System.out.println("Playlist has been retrieved from the database.");
			return playlist;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public Playlist getPlaylist(String playlistName) {
		try {
			statement = database.createStatement();
			
			// Check that the playlist exists
			ResultSet playlistInfo = statement.executeQuery("SELECT * FROM PlaylistInfo WHERE playlistName = '" +playlistName +"'");
	
			// If the playlist does not exist, the query results will be empty
			if(!playlistInfo.next()) {
				return null;
			}
			
			// To create the playlist, we need the name, which we can grab 
			Playlist playlist = new Playlist(playlistInfo.getString(2));
	
			// Get all the songs in the playlist
			// This will return a bunch of tuples that we can build into the list of songs in the playlist
			// results = database.query("SELECT * FROM Playlist WHERE playlistID = playlistID");
			
			// To create the list of songs...
			// Check the syntax necessary for this
			// for(tuple t : results) {
			//		playlist.add(new Song(t.songID, t.songName);
			// }
			
			System.out.println("Playlist has been retrieved from the database.");
			return playlist;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean putPlaylist(Playlist playlist) {
		// For every song in the playlist,
		// Either we can go through the list of songs
		//     OR
		// We can build a string of IDs and place them
		
		// I prefer the first option
		
		try {
			statement = database.createStatement();
			
			// Check that the playlist exists
			statement.executeUpdate("INSERT INTO PlaylistInfo(playlistName) values('" +playlist.getName() +"')");
			System.out.println("Playlist has been put into database.");
			
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		return false;
	}

	@Override
	public ArrayList<Album> getAllAlbums(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Artist> getAllArtists(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Song> getAllSongs(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Playlist> getAllPlaylists(String name) {
		// TODO Auto-generated method stub
		return null;
	}
}
