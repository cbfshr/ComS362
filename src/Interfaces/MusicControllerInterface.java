package Interfaces;
import java.util.ArrayList;

import Objects.Album;
import Objects.Artist;
import Objects.Playlist;
import Objects.Song;

public interface MusicControllerInterface {
	ArrayList<Song>		searchSong(String name);
	ArrayList<Artist>	searchArtist(String name);
	ArrayList<Album>	searchAlbum(String name);
	ArrayList<Playlist>	searchPlaylist(String name);
	ArrayList<Song>		searchTopSongs(int artistID);
	Playlist			createPlaylist(String playlistName);
	boolean				addSong(int songID, int playlistID);
	boolean				deleteSong(int songID, int playlistID);
	ArrayList<Song>		listSongs(int playlistID);
}
