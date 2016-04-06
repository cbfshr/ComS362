package Interfaces;
import java.util.ArrayList;

import Objects.Album;
import Objects.Artist;
import Objects.Playlist;
import Objects.Song;

public interface LibraryInterface {
	boolean			addSong(Song song, int playlistID);
	
	boolean			createPlaylist(String name);
	
	boolean			deleteSong(int songID, int playlistID);
	
	ArrayList<Song>	listSongs(int playlistID);
	
	Playlist		getPlaylist(String playlistName);
	
	Playlist		getPlaylist(int playlistID);
}
