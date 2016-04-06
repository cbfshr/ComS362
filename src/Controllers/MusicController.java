package Controllers;
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
	public ArrayList<Song> searchSong(String name) {
		return search.searchSong(name);
	}

	@Override
	public ArrayList<Artist> searchArtist(String name) {
		return search.searchArtist(name);
	}

	@Override
	public ArrayList<Album> searchAlbum(String name) {
		return search.searchAlbum(name);
	}

	@Override
	public ArrayList<Playlist> searchPlaylist(String name) {
		return search.searchPlaylist(name);
	}

	@Override
	public ArrayList<Song> searchTopSongs(String artistName) {
		ArrayList<Song> songs = search.searchTopSongs(artistName);
		int i = 1;
		
		for(Song s : songs) {
			System.out.println(i +".) " +s.getName() +" - " +s.getArtist() +" (Plays: " +s.getPlays() +")");
			i++;
		}
		
		return songs;
	}

	@Override
	public Playlist createPlaylist(String playlistName) {
		//return musicLibrary.createPlaylist(playlistName);
		return null;
	}

	@Override
	public boolean addSong(int songID, int playlistID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteSong(int songID, int playlistID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Song> listSongs(int playlistID) {
		// TODO Auto-generated method stub
		return null;
	}

}
