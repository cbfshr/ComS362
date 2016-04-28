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
			System.out.println(album.getName() +" - " +album.getArtist() +" (Rating: " +album.getRating() +")");
		}
		
		return albums;
	}

	@Override
	public ArrayList<Artist> searchArtist(String name) {
		ArrayList<Artist> artists = database.getAllArtists(name);

		System.out.println("Artists:");
		for(Artist artist : artists) {
			System.out.println(artist.getArtistName() +" (Rating: " +artist.getRating() +")");
		}
		
		return artists;
	}
	
	@Override
	public ArrayList<Song> searchSong(String name, boolean showDetails) {
		ArrayList<Song> songs = database.getAllSongs(name);

		// TODO:
		System.out.println("Search song results:");
		if(!showDetails) {
			System.out.println(String.format("%-25s%-20s", "Song", "Artist"));
		} else {
			System.out.println(String.format("%-25s%-20s%-25s%-15s%-10s%-15s%-15s%-20s%-8s%-6s", "Song", "Artist", "Album", "Duration", "Track #", "Sample Rate", "Content Type", "Genre", "Plays", "Rating"));
		}
		
		for(Song s : songs) {
			if(!showDetails) {
				System.out.println(String.format("%-25s%-20s", s.getName(), s.getArtist()));
			} else {
				System.out.println(String.format("%-25s%-20s%-25s%-15s%-10s%-15s%-15s%-20s%-5d%-6s", s.getName(), s.getArtist(), s.getAlbum(), s.getDuration(), s.getTrackNumber(), s.getSampleRate(), s.getContentType(), s.getGenre(), s.getPlays(), s.getRating()));
			}
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
			if(playlist.isFeatured()) {
				System.out.println(playlist.getName() +" (*Featured Playlist)" +"(Rating: " +playlist.getRating() +")");
			} else {
				System.out.println(playlist.getName());
			}
		}
		
		return playlists;
	}

	@Override
	public ArrayList<String> searchGenre(String name) {
		ArrayList<String> genres = database.getAllGenres(name);

		System.out.println("Genres:");
		for(String s : genres) {
			System.out.println(s);
		}
		
		return genres;
	}
}
