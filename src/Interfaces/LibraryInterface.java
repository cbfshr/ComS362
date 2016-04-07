package Interfaces;
import java.util.ArrayList;

import Objects.Album;
import Objects.Artist;
import Objects.Playlist;
import Objects.Song;

public interface LibraryInterface {
	boolean			addSong(Song song, String playlistName);
	
	boolean			deleteSong(int songID, int playlistID);
	
	ArrayList<Song>	listSongs(String playlistName);
	
	boolean			createPlaylist(String playlistName);
	
	Playlist		getPlaylist(String playlistName);
	
	Playlist		getPlaylist(int playlistID);
}
