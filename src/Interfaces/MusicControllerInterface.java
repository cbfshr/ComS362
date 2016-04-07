package Interfaces;
import java.util.ArrayList;

import Objects.Album;
import Objects.Artist;
import Objects.Playlist;
import Objects.Song;

public interface MusicControllerInterface {
	ArrayList<Song>		searchSong(String songName);
	
	ArrayList<Artist>	searchArtist(String artistName);
	
	ArrayList<Album>	searchAlbum(String albumName);
	
	ArrayList<Playlist>	searchPlaylist(String playlistName);
	
	ArrayList<Song>		searchTopSongs(String artistName);
	
	boolean				createPlaylist(String playlistName);
	
	void				getPlaylist(String playlistName);
	
	boolean				addSong(int songID, int playlistID);
	
	boolean				deleteSong(int songID, int playlistID);
	
	ArrayList<Song>		listSongs(int playlistID);
}
