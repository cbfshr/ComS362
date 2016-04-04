package Interfaces;

import java.util.ArrayList;

import Objects.Song;

public interface PlaylistInterface {
	boolean			create(String name);
	boolean			addSong(Song song);
	boolean			deleteSong(int songID);
	ArrayList<Song>	getAllSongs();
}
