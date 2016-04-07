package Interfaces;
import java.util.ArrayList;

import Objects.Album;
import Objects.Artist;
import Objects.Playlist;
import Objects.Song;

public interface LibraryInterface {
	boolean			addSong(Song song, int playlistID);
	
	boolean			deleteSong(int songID, int playlistID);
	
	ArrayList<Song>	listSongs(int playlistID);
	
	boolean			createPlaylist(String playlistName);
	
	Playlist		getPlaylist(String playlistName);
	
	Playlist		getPlaylist(int playlistID);
}
