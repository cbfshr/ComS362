package Interfaces;
import java.util.ArrayList;

import Objects.Song;

public interface LibraryInterface {
	boolean			addSong(Song song, int playlistID);
	boolean			createPlaylist(String name);
	boolean			deleteSong(int songID, int playlistID);
	ArrayList<Song>	listSongs(int playlistID);    
}
