package Interfaces;

import java.util.ArrayList;
import Objects.Album;
import Objects.Song;

public interface AlbumInterface {
	String getAlbumData();
	
	String getTrackList();
	
	String compare(Album album2);
	
	boolean	rate(int rating);

	public int getID();
	
	public String getArtist();
	
	public String getName();
	
	public ArrayList<Song> getSongs();
	
	public void addSong(Song song);
	
	public int getRating();
	
	public String getReleaseDate();
	
	public int getNumberOfSongs();
}
