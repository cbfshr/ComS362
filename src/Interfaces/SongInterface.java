package Interfaces;

import Objects.Song;

public interface SongInterface {
	String compare(Song song2);
	String getSongData();
	boolean			rate(int rating);
	
	
	
	// ---------------------------
	int		getID();
	
	String	getName();
	
	String	getArtist();
	
	String	getAlbum();
	
	String getDuration();
	
	String getTrackNumber();
	
	String getSampleRate();
	
	String getContentType();
	
	String getGenre();
	
	int		getPlays();
	
	int		getRating();
}
