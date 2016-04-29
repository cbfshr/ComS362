package Interfaces;

import java.io.File;
import java.util.ArrayList;
import Objects.Playlist;
import Objects.Song;

public interface LibraryInterface {
	boolean				populateMusicLibrary(File path);
	
	boolean				addSongToPlaylist(String songName, String playlistName);
	
	boolean				deleteSongFromPlaylist(String songName, String playlistName);
	
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

	boolean				addAllAlbumSongsToPlaylist(String albumName, String playlistName);
	
	boolean				addAllArtistSongsToPlaylist(String artistName, String playlistName);
}
