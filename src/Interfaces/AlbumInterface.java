package Interfaces;

import java.util.ArrayList;

import Objects.Song;

public interface AlbumInterface {
	public int getID();
	
	public String getArtist();
	
	public String getName();
	
	public ArrayList<Song> getSongs();
	
	public void addSong(Song song);
	
	public int getRating();
	
	public String getReleaseDate();
	
	public int getNumberOfSongs();
}
