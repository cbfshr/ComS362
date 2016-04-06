package Objects;

import java.util.ArrayList;

public class Artist {
	int ID;
	private String artistName;
	ArrayList<Album> albums;
	ArrayList<Song> tracks;
	ArrayList<Song> topTracks;
	
	public Artist(int ID, String artistName, ArrayList<Album> albums, ArrayList<Song> tracks, ArrayList<Song> topTracks) {
		this.ID = ID;
		this.artistName = artistName;
		this.albums = albums;
		this.tracks = new ArrayList<Song>(tracks);
		this.topTracks = topTracks;
	}
	
	public String getArtistName() {
		return this.artistName;
	}
}
