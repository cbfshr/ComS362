package Objects;

import java.util.ArrayList;

import Interfaces.PlaylistInterface;

public class Playlist implements PlaylistInterface {
	private String playlistName;
	private ArrayList<Song> songs;
	
	public Playlist(String playlistName) {
		this.playlistName = playlistName;
		
		songs = new ArrayList<Song>();
	}

	@Override
	public String getName() {
		return playlistName;
	}
	
	@Override
	public boolean addSong(Song song) {
		return songs.add(song);
	}

	@Override
	public boolean deleteSong(int songID) {
		return songs.remove(songs.get(songID));
	}

	@Override
	public ArrayList<Song> getAllSongs() {
		return songs;
	}
}
