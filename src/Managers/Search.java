package Managers;

import java.util.ArrayList;

import Interfaces.SearchInterface;
import Objects.Album;
import Objects.Artist;
import Objects.Database;
import Objects.Playlist;
import Objects.Song;

public class Search implements SearchInterface {
	private Database database;
	
	public Search(Database database) {
		this.database = database;
	}
	
	@Override
	public ArrayList<Album> searchAlbum(String name) {
		return database.getAllAlbums(name);
	}

	@Override
	public ArrayList<Artist> searchArtist(String name) {
		return database.getAllArtists(name);
	}

	@Override
	public ArrayList<Song> searchTopSongs(int artistID) {
		return database.getTopSongs(artistID);
	}

	@Override
	public ArrayList<Playlist> searchPlaylist(String name) {
		return database.getAllPlaylists(name);
	}

	@Override
	public ArrayList<Song> searchSong(String name) {
		return database.getAllSongs(name);
	}

}
