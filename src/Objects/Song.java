package Objects;

import Interfaces.SongInterface;

public class Song implements SongInterface {
	int ID;
	private String name;
	private String artist;
	private Album album;
//	String genre;
	
	public Song(String name, String artist) {
		this.name = name;
		this.artist = artist;
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
		// TODO Auto-generated method stub
		return null;
	}
}
