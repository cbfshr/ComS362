package Objects;

import java.util.ArrayList;

import Interfaces.PlaylistInterface;

public class Playlist implements PlaylistInterface {
	int ID;
	String playlistName;
	ArrayList<Song> songs;
	
	public Playlist(int ID, String playlistName, ArrayList<Song> songs) {
		this.ID = ID;
		this.playlistName = playlistName;
		this.songs = songs;
	}

	@Override
	public boolean create(String name) {
		// TODO Auto-generated method stub
		return false;
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
