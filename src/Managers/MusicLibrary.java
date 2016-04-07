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
	public boolean addSong(Song song, String playlistName) {
		Playlist playlist = database.getPlaylist(playlistName);
		if(playlist == null) {
			System.err.println("Playlist not found.");
		}
		
		playlist.addSong(song);
		
		boolean putPlaylist = database.putPlaylist(playlist);
		
		return putPlaylist;
	}

	@Override
	public boolean deleteSong(Song song, String playlistName) {
		Playlist playlist = database.getPlaylist(playlistName);

		if(playlist == null) {
			System.err.println("Playlist not found!");
			return false;
		}
		
		playlist.deleteSong(song.getID());
		
		boolean putPlaylist = database.putPlaylist(playlist);
		
		return putPlaylist;
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
}
