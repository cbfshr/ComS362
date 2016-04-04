package Objects;

import java.util.ArrayList;

public class Album {
	int ID;
	Artist artist;
	String name;
	ArrayList<Song> songs;
	
	public Album(int ID, Artist artist, String name, ArrayList<Song> songs) {
		this.ID = ID;
		this.artist = artist;
		this.name = name;
		this.songs = songs;
	}
}
