package Interfaces;

import java.util.ArrayList;

import Objects.Song;

public interface PlaylistInterface {
	String			getName();
	
	boolean			addSong(Song song);
	
	boolean			deleteSong(int songID);
	
	ArrayList<Song>	getAllSongs();
}
