package Objects;

import java.util.ArrayList;
import Interfaces.AlbumInterface;

public class Album implements AlbumInterface{
	private int ID;
	private String artist;
	private String albumName;
	private ArrayList<Song> songs;
	private int rating;
	private String releaseDate;
	private int numberOfSongs;
	
	public Album(int ID, String albumName, String artist, int rating, String releaseDate, int numberOfSongs) {
		this.ID = ID;
		this.albumName = albumName;
		this.artist = artist;
		this.rating = rating;
		this.releaseDate = releaseDate;
		this.numberOfSongs = numberOfSongs;
		
		this.songs = new ArrayList<Song>();
	}

	@Override
	public int getID() {
		return ID;
	}

	@Override
	public String getArtist() {
		return artist;
	}

	@Override
	public String getName() {
		return albumName;
	}

	@Override
	public ArrayList<Song> getSongs() {
		return songs;
	}

	@Override	
	public void addSong(Song song) {
		songs.add(song);
	}

	@Override
	public int getRating() {
		return rating;
	}

	@Override
	public String getReleaseDate() {
		return releaseDate;
	}

	@Override
	public int getNumberOfSongs() {
		return numberOfSongs;
	}
}
