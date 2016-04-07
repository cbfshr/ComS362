package Objects;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Database.DatabaseConnection;
import Interfaces.DatabaseInterface;

public class Database implements DatabaseInterface {
	Connection database;
	Statement statement;
	
	public Database() {
		// Initialize connection to the database
		DatabaseConnection databaseConnection = new DatabaseConnection();

		// Connect to the database
		database = databaseConnection.ConnectToDatabase();
	}
	
	@Override
	public Song getSong(int songID, int playlistID) {
//		try {
//			statement = database.createStatement();
//
////			String query = "SELECT playlists.PlaylistName FROM PlaylistSongs"
////					+ "";
////			ResultSet results = statement.executeQuery("SELECT  FROM Playlist WHERE playlistID = " +playlistID +" AND songID = " +songID);
////			
////			// Create the song object by passing the song name
////			// This will also use 
////			Song song = new Song(results.getString(2), "ddew");
////			return song;
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return null;
	}

	@Override
	public Artist getArtist(int artistID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Playlist getPlaylist(int playlistID) {
//		try {
//			statement = database.createStatement();
//			
//			// Check that the playlist exists
//			ResultSet playlistInfo = statement.executeQuery("SELECT * FROM PlaylistInfo WHERE playlistID = " +playlistID);
//
//			// If the playlist does not exist, the query results will be empty
//			if(!playlistInfo.next()) {
//				return null;
//			}
//			
//			// To create the playlist, we need the name, which we can grab 
//			Playlist playlist = new Playlist(playlistInfo.getString(2));
//
//			// Get all the songs in the playlist
//			// This will return a bunch of tuples that we can build into the list of songs in the playlist
//			// results = database.query("SELECT * FROM Playlist WHERE playlistID = playlistID");
//			
//			// To create the list of songs...
//			// Check the syntax necessary for this
//			// for(tuple t : results) {
//			//		playlist.add(new Song(t.songID, t.songName);
//			// }
//
//			System.out.println("Playlist has been retrieved from the database.");
//			return playlist;
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return null;
	}
	
	@Override
	public Playlist getPlaylist(String playlistName) {
		try {
			statement = database.createStatement();
			
			String playlistQuery =   "SELECT ID FROM Playlists "
									+"WHERE playlistName = '" +playlistName +"'";
			ResultSet playlistInfo = statement.executeQuery(playlistQuery);
	
			// If the playlist does not exist, the query results will be empty
			if(!playlistInfo.next()) {
				return null;
			}
			
			// To create the playlist, we need the ID and name, which we can grab 
			int playlistID = playlistInfo.getInt("ID");
			Playlist playlist = new Playlist(playlistID, playlistName);
	
			// Get all the songs in the playlist
			// This will return a bunch of tuples that we can build into the list of songs in the playlist
			// results = database.query("SELECT * FROM Playlist WHERE playlistID = playlistID");
			
			String playlistSongsQuery =  "SELECT songs.ID, songs.SongName, artists.ArtistName, albums.AlbumName, songs.Plays FROM PlaylistSongs playlistSongs "
										//+"WHERE PlaylistID = " +playlistID +" "
										+"INNER JOIN Songs songs ON (playlistSongs.SongID = songs.ID) "
//										+"INNER JOIN Playlists playlists ON (playlistSongs.PlaylistID = playlists.ID) "
										+"	INNER JOIN Artists artists ON (songs.ArtistID = artists.ID) "
										+"	INNER JOIN Albums albums ON (songs.AlbumID = albums.ID)";
			ResultSet results = statement.executeQuery(playlistSongsQuery);
			
			
			// To create the list of songs...
			// Check the syntax necessary for this
			while(results.next()) {
				playlist.addSong(new Song(results.getInt("ID"), results.getString("SongName"), results.getString("ArtistName"), results.getInt("Plays")));
			}
			
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
		try {
			statement = database.createStatement();
			
			// Check if the playlist already exists
			if(getPlaylist(playlist.getName()) != null) {
				// If it exists, UPDATE
				String query =   "UPDATE Playlists "
								+"SET PlaylistName = '" +playlist.getName() +"'"
								+"WHERE ID = '" +playlist.getID() +"'";
				statement.executeUpdate(query);
				
				// Do stuff to update the songs
				ArrayList<Song> songs = playlist.getAllSongs();
				String songQuery;
				ResultSet results;
				for(Song song : songs) {
					// Check if the song is already in the playlist
					songQuery =  "SELECT * FROM PlaylistSongs "
								+"WHERE SongID = " +song.getID();
					
					results = statement.executeQuery(songQuery);
					
					// If the results are empty, add the song to the playlist in the database
					if(!results.next()) {
						songQuery =  "INSERT INTO PlaylistSongs(SongID, PlaylistID) "
									+"VALUES(" +song.getID() +", " +playlist.getID() +")";
						statement.executeUpdate(songQuery);
					}
				}
			} else {
				// If it doesn't exist, INSERT
				String query =   "INSERT INTO Playlists(PlaylistName) "
								+"VALUES('" +playlist.getName() +"')";
				statement.executeUpdate(query);
				
				// Do stuff to insert the songs
				ArrayList<Song> songs = playlist.getAllSongs();
				String songQuery;
				for(Song s : songs) {
					songQuery =  "INSERT INTO PlaylistSongs(SongID, PlaylistID) "
								+"VALUES(" +s.getID() +", " +playlist.getID() +")";
					statement.executeUpdate(songQuery);
				}
			}
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		
		return false;
	}

	@Override
	public ArrayList<Album> getAllAlbums(String albumName) {
		try {
			statement = database.createStatement();
			
			String query =   "SELECT albums.AlbumName, artists.ArtistName "
							+"FROM Albums albums "
							+"INNER JOIN Artists artists ON (albums.ArtistID = artists.ID)";
//							+"WHERE ArtistName = '" +albumName +"'";
			ResultSet results = statement.executeQuery(query);
			
			ArrayList<Album> albums = new ArrayList<Album>();
			while(results.next()) {
				albums.add(new Album(results.getString("AlbumName"), results.getString("ArtistName")));
			}
			
			return albums;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public ArrayList<Artist> getAllArtists(String artistName) {
		try {
			statement = database.createStatement();
			
			String query =   "SELECT * FROM Artists ";
//							+"WHERE ArtistName = '" +artistName +"'";
			ResultSet results = statement.executeQuery(query);
			
			ArrayList<Artist> artists = new ArrayList<Artist>();
			while(results.next()) {
				artists.add(new Artist(results.getString("ArtistName"), null, null, null));
			}
			
			return artists;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public ArrayList<Song> getAllSongs(String name) {
		try {
			statement = database.createStatement();
			
			String query =   "SELECT songs.ID, songs.SongName, artists.ArtistName, songs.Plays "
							+"FROM Songs songs "
							+"INNER JOIN Artists artists ON (songs.ArtistID = artists.ID)";
//							+"WHERE songs.SongName = '" +name +"'";
			
			ResultSet results = statement.executeQuery(query);
			
			ArrayList<Song> songs = new ArrayList<Song>();
			while(results.next()) {
				songs.add(new Song(results.getInt("songs.ID"), results.getString("songs.SongName"), results.getString("artists.ArtistName"), results.getInt("Plays")));
			}
			
			return songs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public ArrayList<Song> getTopSongs(String artistName) {		
		try {
			statement = database.createStatement();
			
			String query =   "SELECT songs.ID, songs.SongName, artists.ArtistName, songs.Plays FROM Songs "
							+"INNER JOIN Artists artists ON (Songs.ArtistID = artists.ID) "
							+"ORDER BY Plays DESC";
			ResultSet results = statement.executeQuery(query);
			
			ArrayList<Song> songs = new ArrayList<Song>();
			while(results.next()) {
				songs.add(new Song(results.getInt("songs.ID"), results.getString("SongName"), results.getString("ArtistName"), results.getInt("Plays")));
			}
			
			return songs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public ArrayList<Playlist> getAllPlaylists(String name) {
		try {
			statement = database.createStatement();
			
			String query =   "SELECT * FROM Playlists ";
//							+"WHERE PlaylistName = '" +name +"'";
			ResultSet results = statement.executeQuery(query);
			
			ArrayList<Playlist> playlists = new ArrayList<Playlist>();
			while(results.next()) {
				playlists.add(new Playlist(results.getString("PlaylistName")));
			}
			
			return playlists;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
