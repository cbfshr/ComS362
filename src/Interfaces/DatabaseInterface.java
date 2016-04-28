package Interfaces;
import java.io.File;
import java.util.ArrayList;

import Objects.Album;
import Objects.Artist;
import Objects.Playlist;
import Objects.Song;

public interface DatabaseInterface {
	void				listFilesForFolder(final File folder);
	
//	Song				getSong(int songID, int playlistID);

    Artist				getArtist(String artistName);
    
    Album				getAlbum(String albumName);
    
//	Playlist			getPlaylist(int playlistID);

    Playlist			getPlaylist(String playlistName);
    
    boolean				putPlaylist(Playlist playlist);
    
    ArrayList<Artist>	getAllArtists(String artistName);
    
    ArrayList<Album>	getAllAlbums(String albumName);
    
    ArrayList<String>	getAllGenres(String genreName);

    ArrayList<Song>		getAllSongs(String songName);
    
    ArrayList<Song>		getTopSongs(String artistName);
    
    ArrayList<Playlist>	getAllPlaylists(String playlistName);

    boolean				renamePlaylist(String playlistName, String newPlaylistName);
    
    boolean				deletePlaylist(String playlistName);
    
    ArrayList<Playlist>	getFeaturedPlaylists();

	boolean				rateArtist(String artistName, int rating);

	boolean				rateAlbum(String albumName, int rating);

	boolean				rateSong(String songName, int rating);

	boolean				ratePlaylist(String playlistName, int rating);
}
