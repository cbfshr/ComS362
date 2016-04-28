package Interfaces;
import java.util.ArrayList;

import Objects.Album;
import Objects.Artist;
import Objects.Playlist;
import Objects.Song;

public interface LibraryInterface {
	boolean				addSong(Song song, String playlistName);
	
	boolean				deleteSong(Song song, String playlistName);
	
	ArrayList<Song>		listSongs(String playlistName);
	
	boolean				createPlaylist(String playlistName);
	
	Playlist			getPlaylist(String playlistName);
	
	boolean				renamePlaylist(String playlistName, String newPlaylistName);
	
	boolean				deletePlaylist(String playlistName);

	ArrayList<Song> 	listSongsAlbum(String albumName);

	ArrayList<Playlist> getFeaturedPlaylists();

	boolean				rateArtist(String artistName, int rating);

	boolean				rateAlbum(String albumName, int rating);

	boolean				rateSong(String songName, int rating);

	boolean				ratePlaylist(String playlistName, int rating);
}
