package Controllers;
import java.io.File;
import java.util.ArrayList;

import Interfaces.MusicControllerInterface;
import Managers.MusicLibrary;
import Managers.Search;
import Objects.Album;
import Objects.Artist;
import Objects.Database;
import Objects.Playlist;
import Objects.Song;

public class MusicController implements MusicControllerInterface {
	private Search search;
	private MusicLibrary musicLibrary;
	
	public MusicController() {
		// Connect to database
		Database database = new Database();
		
		this.musicLibrary = new MusicLibrary(database);
		this.search = new Search(database);
	}

	@Override
	public boolean populateMusicDatabase(String path) {
		File musicLibraryPath = new File(path);
		return musicLibrary.populateMusicLibrary(musicLibraryPath);
	}
	
	// -------------------------------------------------------

	@Override
	public ArrayList<Song> searchSong(String name) {
		return search.searchSong(name, false);
	}
	
	@Override
	public ArrayList<Song> searchSongDetails(String name) {
		return search.searchSong(name, true);
	}

	@Override
	public ArrayList<Artist> searchArtist(String name) {
		return search.searchArtist(name, false);
	}

	@Override
	public ArrayList<Artist> searchArtistDetails(String artistName) {
		return search.searchArtist(artistName, true);
	}

	@Override
	public ArrayList<Album> searchAlbum(String albumName) {
		return search.searchAlbum(albumName, false);
	}

	@Override
	public ArrayList<Album> searchAlbumDetails(String albumName) {
		return search.searchAlbum(albumName, true);
	}

	@Override
	public ArrayList<String> searchGenre(String genreName) {
		return search.searchGenre(genreName);
	}

	@Override
	public ArrayList<Playlist> searchPlaylist(String name) {
		return search.searchPlaylist(name);
	}

	@Override
	public ArrayList<Song> searchTopSongs(String artistName) {
		return search.searchTopSongs(artistName);
	}

	// -----------------------------------------------------------------------------------------
	// MusicLibrary Methods
	// -----------------------------------------------------------------------------------------
	
	@Override
	public boolean createPlaylist(String playlistName) {
		return musicLibrary.createPlaylist(playlistName);
	}

	@Override
	public void getPlaylist(String playlistName) {
		musicLibrary.getPlaylist(playlistName);
	}

	@Override
	public boolean addSongToPlaylist(String songName, String playlistName) {
		return musicLibrary.addSongToPlaylist(songName, playlistName);
	}

	@Override
	public boolean deleteSongFromPlaylist(String songName, String playlistName) {
		return musicLibrary.deleteSongFromPlaylist(songName, playlistName);
	}
	
	@Override
	public ArrayList<Song> listSongs(String playlistName) {
		return musicLibrary.listSongs(playlistName);
	}
	
	@Override
	public ArrayList<Song> listSongsAlbum(String albumName) {
		return musicLibrary.listSongsAlbum(albumName);
	}

	@Override
	public boolean renamePlaylist(String playlistName, String newPlaylistName) {
		return musicLibrary.renamePlaylist(playlistName, newPlaylistName);
	}

	@Override
	public boolean deletePlaylist(String playlistName) {
		return musicLibrary.deletePlaylist(playlistName);
	}

	@Override
	public ArrayList<Playlist> getFeaturedPlaylists() {
		return musicLibrary.getFeaturedPlaylists();
	}

	@Override
	public boolean rateArtist(String artistName, int rating) {
		return musicLibrary.rateArtist(artistName, rating);
	}

	@Override
	public boolean rateAlbum(String albumName, int rating) {
		return musicLibrary.rateAlbum(albumName, rating);
	}

	@Override
	public boolean rateSong(String songName, int rating) {
		return musicLibrary.rateSong(songName, rating);
	}

	@Override
	public boolean ratePlaylist(String playlistName, int rating) {
		return musicLibrary.ratePlaylist(playlistName, rating);
	}

	@Override
	public boolean addAllArtistSongsToPlaylist(String artistName, String playlistName) {
		return musicLibrary.addAllArtistSongsToPlaylist(artistName, playlistName);
	}

	@Override
	public boolean addAllAlbumSongsToPlaylist(String albumName, String playlistName) {
		return musicLibrary.addAllAlbumSongsToPlaylist(albumName, playlistName);
	}

	@Override
	public ArrayList<Artist> getSimilarArtists(String artistName) {
		return search.getSimilarArtists(artistName);
	}

	@Override
	public ArrayList<Album> getNewReleases() {
		return search.getNewReleases();
	}
}
