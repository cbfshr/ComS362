package Objects;

import Interfaces.SongInterface;

public class Song implements SongInterface {
//	int ID;
	String name;
//	Album album;
//	Artist artist;
//	String genre;
	
	public Song(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}
}
