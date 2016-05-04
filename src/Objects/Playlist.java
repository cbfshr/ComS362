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
	public boolean deleteSong(String songName) {
		return songs.remove(songName);
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
	
	@Override
	public boolean addSongList(ArrayList<Song> songs) {
		for(Song s: songs) {
			this.songs.add(s);
		}
		return true;
	}
	
	@Override
	public boolean rate(int newRating) {
		rating = newRating;
		return true;
	}
	
	@Override
	public boolean rename(String newName) {
		playlistName = newName;
		return true;
	}
}
