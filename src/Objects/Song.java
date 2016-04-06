package Objects;

import Interfaces.SongInterface;

public class Song implements SongInterface {
	int ID;
	private String name;
	private String artist;
	private String album;
	private int plays;
//	String genre;
	
	public Song(String name, String artist, int plays) {
		this.name = name;
		this.artist = artist;
		this.plays = plays;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getArtist() {
		return artist;
	}

	@Override
	public String getAlbum() {
		return album;
	}
	
	public int getPlays() {
		return plays;
	}
}
