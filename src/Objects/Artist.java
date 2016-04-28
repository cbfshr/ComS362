package Objects;

import Interfaces.ArtistInterface;

public class Artist implements ArtistInterface {
	int ID;
	private String artistName;
	private int rating;
	
	public Artist(int ID, String artistName, int rating) {
		this.ID = ID;
		this.artistName = artistName;
		this.rating = rating;
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
}
