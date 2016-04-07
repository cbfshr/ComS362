package Interfaces;
import java.util.ArrayList;

import Objects.Album;
import Objects.Artist;
import Objects.Playlist;
import Objects.Song;

public interface LibraryInterface {
	boolean			addSong(Song song, String playlistName);
	
	boolean			deleteSong(Song song, String playlistName);
	
	ArrayList<Song>	listSongs(String playlistName);
	
	boolean			createPlaylist(String playlistName);
	
	Playlist		getPlaylist(String playlistName);
}
