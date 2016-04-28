package Objects;

import java.util.ArrayList;

import Interfaces.PlaylistInterface;

public class Playlist implements PlaylistInterface {
	private int ID;
	private String playlistName;
	private ArrayList<Song> songs;
	private boolean featured;
	private int rating;
	
	public Playlist(String playlistName) {
		this.playlistName = playlistName;
		
		songs = new ArrayList<Song>();
	}
	
	public Playlist(int playlistID, String playlistName, int featured, int rating) {
		this.ID = playlistID;
		this.playlistName = playlistName;
		this.featured = (featured == 1);
		this.rating = rating;
		
		songs = new ArrayList<Song>();
	}
	
	@Override
	public int getID() {
		return ID;
	}

	@Override
	public String getName() {
		return playlistName;
	}
	
	@Override
	public boolean addSong(Song song) {
		return songs.add(song);
	}

	@Override
	public boolean deleteSong(int songID) {
		return songs.remove(songs.get(songID));
	}

	@Override
	public ArrayList<Song> getAllSongs() {
		return songs;
	}

	@Override
	public boolean isFeatured() {
		return featured;
	}

	@Override
	public int getRating() {
		return rating;
	}
}
