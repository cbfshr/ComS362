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
	public ArrayList<Album> searchAlbum(String albumName, boolean showDetails) {
		ArrayList<Album> albums = database.getAllAlbums(albumName);
		
		if(!showDetails) {
			System.out.println("Album");
		} else {
			System.out.println(String.format("%-25s%-20s%-25s%-15s%-10s", "Album", "Artist", "Number of Songs", "Release Date", "Rating"));
		}
		for(Album album :albums) {
			if(!showDetails) {
				System.out.println(album.getName() +" - " +album.getArtist());
			} else {
				System.out.println(String.format("%-25s%-20s%-25s%-15s%-10s", album.getName(), album.getArtist(), album.getNumberOfSongs(), album.getReleaseDate(), album.getRating()));
			}
		}
		
		return albums;
	}

	@Override
	public ArrayList<Artist> searchArtist(String name, boolean showDetails) {
		ArrayList<Artist> artists = database.getAllArtists(name);

		if(artists == null) {
			return null;
		}
		
		if(!showDetails) {
			System.out.println("Artists:");
		} else {
			System.out.println(String.format("%-25s%-20s%-20s%-20s%-10s", "Artist", "Genre", "Number of Albums", "Number of Songs", "Rating"));
		}
		for(Artist artist : artists) {
			if(!showDetails) {
				System.out.println(artist.getArtistName() +" (Rating: " +artist.getRating() +")");
			} else {
				System.out.println(String.format("%-25s%-20s%-20s%-20s%-10s", artist.getArtistName(), artist.getGenre(), artist.getNumberOfAlbums(), artist.getNumberOfSongs(), artist.getRating()));
			}
		}
		
		return artists;
	}
	
	@Override
	public ArrayList<Song> searchSong(String name, boolean showDetails) {
		ArrayList<Song> songs = database.getAllSongs(name);

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
		
		if(songs == null) {
			System.err.println("Artist does not exist or has no songs.");
			return null;
		}
		
		int i = 1;
		
		for(Song s : songs) {
			System.out.println(i +".) " +s.getName() +" - " +s.getArtist() +" (Plays: " +s.getPlays() +")");
			
			if(i == 5) {
				break;
			}
			
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

	@Override
	public ArrayList<Artist> getSimilarArtists(String artistName) {
		ArrayList<Artist> similarArtists = database.getSimilarArtists(artistName);
		
		System.out.println("Similar Artists:");
		for(Artist a : similarArtists) {
			System.out.println(a.getArtistName());
		}
		
		return similarArtists;
	}
}
