package Objects;

import Interfaces.SongInterface;

public class Song implements SongInterface {
	int ID;
	String name;
//	Album album;
//	Artist artist;
//	String genre;
	
	public Song(int ID, String name) {
		this.ID = ID;
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}
}
