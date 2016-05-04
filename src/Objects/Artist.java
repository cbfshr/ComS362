package Objects;

import Interfaces.ArtistInterface;

public class Artist implements ArtistInterface {
	int ID;
	private String artistName;
	private int rating;
	private int numberOfSongs;
	private int numberOfAlbums;
	private String genre;
	
	public Artist(int ID, String artistName, int rating, int numberOfSongs, int numberOfAlbums, String genre) {
		this.ID = ID;
		this.artistName = artistName;
		this.rating = rating;
		this.numberOfSongs = numberOfSongs;
		this.numberOfAlbums = numberOfAlbums;
		this.genre = genre;
	}

	@Override
	public int getArtistID() {
		return this.ID;
	}

	@Override
	public String getArtistName() {
		return this.artistName;
	}

	@Override
	public int getRating() {
		return rating;
	}

	@Override
	public int getNumberOfSongs() {
		return numberOfSongs;
	}

	@Override
	public int getNumberOfAlbums() {
		return numberOfAlbums;
	}

	@Override
	public String getGenre() {
		return genre;
	}
	
	@Override
	public String getArtistData() {
		return artistName + " " + rating + " " + numberOfSongs + " " + numberOfAlbums;
	}
	
	@Override
	public boolean rate(int newRating) {
		rating = newRating;
		return true;
	}
}
