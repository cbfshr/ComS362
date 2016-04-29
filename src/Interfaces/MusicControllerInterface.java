package Interfaces;

import java.io.File;
import java.util.ArrayList;
import Objects.Album;
import Objects.Artist;
import Objects.Playlist;
import Objects.Song;

public interface MusicControllerInterface {
	boolean				populateMusicDatabase(String path);
	
	ArrayList<Song>		searchSong(String songName);
	
	ArrayList<Song>		searchSongDetails(String songName);
	
	ArrayList<Artist>	searchArtist(String artistName);
	
	ArrayList<Artist>	searchArtistDetails(String artistName);
	
	ArrayList<Album>	searchAlbum(String albumName);
	
	ArrayList<Album>	searchAlbumDetails(String albumName);
	
	ArrayList<String>	searchGenre(String genreName);
	
	ArrayList<Playlist>	searchPlaylist(String playlistName);
	
	ArrayList<Song>		searchTopSongs(String artistName);
	
	boolean				createPlaylist(String playlistName);
	
	void				getPlaylist(String playlistName);
	
	boolean				addSongToPlaylist(String songName, String playlistName);
	
	boolean				deleteSongFromPlaylist(String songName, String playlistName);
	
	boolean				addAllArtistSongsToPlaylist(String artistName, String playlistName);
	
	ArrayList<Song>		listSongs(String playlistName);

	ArrayList<Song>		listSongsAlbum(String albumName);

	boolean				renamePlaylist(String playlistName, String newPlaylistName);

	boolean				deletePlaylist(String playlistName);

	ArrayList<Playlist>	getFeaturedPlaylists();

	boolean				rateArtist(String artistName, int rating);

	boolean				rateAlbum(String albumName, int rating);

	boolean				rateSong(String songName, int rating);

	boolean				ratePlaylist(String playlistName, int rating);
}
