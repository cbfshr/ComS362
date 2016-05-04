package Interfaces;

import java.io.File;
import java.util.ArrayList;
import Objects.Album;
import Objects.Artist;
import Objects.Playlist;
import Objects.Song;

public interface LibraryInterface {
	Song				getSong(String songName);
	
	Album				getAlbum(String albumName);
	
	Artist				getArtist(String artistName);
	
	ArrayList<Artist>	getSimilarArtist(String artistName);
	
	ArrayList<Song>		listSongs(String playlistName);
	
	boolean				rateArtist(String artistName, int rating);
	
	boolean				rateAlbum(String albumName, int rating);
	
	boolean				rateSong(String songName, int rating);
	
	boolean				ratePlaylist(String playlistName, int rating);
	
	boolean				createPlaylist(String playlistName);
	
	Playlist			getPlaylist(String playlistName);
	
	boolean				renamePlaylist(String playlistName, String newPlaylistName);
	
	boolean				deletePlaylist(String playlistName);
	
	boolean				addSongToPlaylist(String songName, String playlistName);
	
	boolean				deleteSongFromPlaylist(String songName, String playlistName);
	
	ArrayList<Playlist> getFeaturedPlaylists();
	
	boolean				populateMusicLibrary(File path);
	
	boolean				addAllAlbumSongsToPlaylist(String albumName, String playlistName);
}
