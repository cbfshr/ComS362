package Managers;

import java.util.ArrayList;

import Interfaces.LibraryInterface;
import Objects.Database;
import Objects.Playlist;
import Objects.Song;

public class MusicLibrary implements LibraryInterface {
	private Database database;
	
	public MusicLibrary(Database database) {
		this.database = database;
	}
	
	@Override
	public boolean addSong(Song song, String playlistName) {
		// Check if the playlist already has been created	
		
		// Add the song to the playlist in the database
		// Playlist playlist = database.getPlaylist(playlistID);
		// playlist.addSong(song);
		
		int playlistID;
		Playlist playlist = database.getPlaylist(playlistName);
		if(playlist == null) {
			System.err.println("Playlist not found.");
		}
		
		//playlistID = playlist.getID();
		playlist.addSong(song);
		
		boolean putPlaylist = database.putPlaylist(playlist);
		
		// return database.putPlaylist(playlist);
		
		return putPlaylist;
	}

	@Override
	public boolean createPlaylist(String playlistName) {
		// Create the new playlist
		Playlist playlist = new Playlist(playlistName);
		
		// Put the playlist in the database
		boolean putPlaylist = database.putPlaylist(playlist);
		
		if(putPlaylist == true) {
			System.out.println("The playlist, " +playlistName +" has been created.");
		} else {
			System.err.println("There was an error creating your playlist.");
			return false;
		}
		
		return putPlaylist;
	}

	@Override
	public boolean deleteSong(int songID, int playlistID) {
		Playlist playlist = database.getPlaylist(playlistID);
		
		if(playlist == null) {
			System.err.println("Playlist not found!");
			return false;
		} else {
			return playlist.deleteSong(songID);
		}
	}

	@Override
	public ArrayList<Song> listSongs(String playlistName) {
		Playlist playlist = database.getPlaylist(playlistName);
		
		if(playlist == null) {
			System.err.println("Playlist not found!");
			return null;
		}
		
		System.out.println("Playlist: " +playlist.getName());
		for(Song s : playlist.getAllSongs()) {
			System.out.println(s.getName() +" - " +s.getArtist());
		}
		
		return playlist.getAllSongs();
	}

	@Override
	public Playlist getPlaylist(String playlistName) {
		Playlist playlist = database.getPlaylist(playlistName);
		
		/*for(Song s : playlist.getAllSongs()) {
			System.out.println(s.getName() +" - " +s.getArtist() +" (Playlist: " +playlist.getName() +")");
		}*/
		
		return playlist;
	}

	@Override
	public Playlist getPlaylist(int playlistID) {
		return database.getPlaylist(playlistID);
	}
}
