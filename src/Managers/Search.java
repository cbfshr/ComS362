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
	public ArrayList<Album> searchAlbum(String albumName) {
		ArrayList<Album> albums = database.getAllAlbums(albumName);
		
		System.out.println("Albums:");
		for(Album album :albums) {
			System.out.println(album.getName() +" - " +album.getArtist());
		}
		
		return albums;
	}

	@Override
	public ArrayList<Artist> searchArtist(String name) {
		ArrayList<Artist> artists = database.getAllArtists(name);

		System.out.println("Artists:");
		for(Artist artist : artists) {
			System.out.println(artist.getArtistName());
		}
		
		return artists;
	}
	
	@Override
	public ArrayList<Song> searchSong(String name) {
		ArrayList<Song> songs = database.getAllSongs(name);

		System.out.println("Songs:");
		for(Song s : songs) {
			System.out.println(s.getName() +" - " +s.getArtist());
		}
		
		return songs;
	}

	@Override
	public ArrayList<Song> searchTopSongs(String artistName) {
		ArrayList<Song> songs = database.getTopSongs(artistName);
		int i = 1;
		
		for(Song s : songs) {
			System.out.println(i +".) " +s.getName() +" - " +s.getArtist() +" (Plays: " +s.getPlays() +")");
			i++;
		}
		
		return songs;
	}

	@Override
	public ArrayList<Playlist> searchPlaylist(String name) {
		ArrayList<Playlist> playlists = database.getAllPlaylists(name);
		
		for(Playlist playlist : playlists) {
			System.out.println(playlist.getName());
		}
		
		return playlists;
	}
}
