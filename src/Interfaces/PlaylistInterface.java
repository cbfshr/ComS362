package Interfaces;

import java.util.ArrayList;

import Objects.Song;

public interface PlaylistInterface {
	boolean			addSongList(ArrayList<Song> songs);
	boolean			rate(int rating);
	
	// ------------------------
	int 			getID();
	
	String			getName();
	
	boolean			addSong(Song song);
	
	boolean			deleteSong(int songID);

	ArrayList<Song>	getAllSongs();
	
	boolean			isFeatured();
	
	int				getRating();
}
