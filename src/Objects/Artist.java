package Objects;

import java.util.ArrayList;

public class Artist {
	int ID;
	ArrayList<Album> albums;
	ArrayList<Song> tracks;
	ArrayList<Song> topTracks;
	
	public Artist(int ID, ArrayList<Album> albums, ArrayList<Song> tracks, ArrayList<Song> topTracks) {
		this.ID = ID;
		this.albums = albums;
		this.tracks = new ArrayList<Song>(tracks);
		this.topTracks = topTracks;
	}
}
