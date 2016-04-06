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
	public boolean addSong(Song song, int playlistID) {
		// Check if the playlist already has been created	
		
		// Add the song to the playlist in the database
		// Playlist playlist = database.getPlaylist(playlistID);
		// playlist.addSong(song);
		// return database.putPlaylist(playlist);
		
		return false;
	}

	@Override
	public boolean createPlaylist(String name) {
		// Check if the playlist already exists
		// This should be done in the database!
		if(database.getPlaylist(name) != null) {
			return false;
		}
		
		// Create the new playlist
		Playlist playlist = new Playlist(name);
		
		// Put the playlist in the database
		return database.putPlaylist(playlist);
	}

	@Override
	public boolean deleteSong(int songID, int playlistID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Song> listSongs(int playlistID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Playlist getPlaylist(String playlistName) {
		return database.getPlaylist(playlistName);
	}

	@Override
	public Playlist getPlaylist(int playlistID) {
		return database.getPlaylist(playlistID);
	}

}
