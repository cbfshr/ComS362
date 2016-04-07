package Objects;

import java.util.ArrayList;

public class Album {
//	int ID;
	private String artist;
	private String albumName;
	private ArrayList<Song> songs;
	
	public Album(String albumName, String artist) {
		this.albumName = albumName;
		this.artist = artist;
	}

	public String getArtist() {
		return artist;
	}
	
	public String getName() {
		return albumName;
	}
	
	public ArrayList<Song> getSongs() {
		return songs;
	}
}
