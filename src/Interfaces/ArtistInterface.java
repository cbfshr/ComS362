package Interfaces;

public interface ArtistInterface {
	String getArtistData();
	
	boolean	rate(int rating);	

	public int getArtistID();
	
	public String getArtistName();
	
	public int getRating();
	
	public int getNumberOfSongs();

	public int getNumberOfAlbums();
	
	public String getGenre();
}
