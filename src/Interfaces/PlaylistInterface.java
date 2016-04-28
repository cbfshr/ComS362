package Interfaces;

import java.util.ArrayList;

import Objects.Song;

public interface PlaylistInterface {
	int 			getID();
	
	String			getName();
	
	boolean			addSong(Song song);
	
	boolean			deleteSong(int songID);

	ArrayList<Song>	getAllSongs();
	
	boolean			isFeatured();
	
	int				getRating();
}
