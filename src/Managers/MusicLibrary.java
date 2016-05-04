package Managers;

import java.io.File;
import java.util.ArrayList;
import Interfaces.LibraryInterface;
import Objects.Album;
import Objects.Artist;
import Objects.Database;
import Objects.Playlist;
import Objects.Song;

public class MusicLibrary implements LibraryInterface {
	private Database database;
	
	public MusicLibrary(Database database) {
		this.database = database;
	}

	@Override
	public boolean populateMusicLibrary(File musicLibraryFolder) {		
		for(final File fileEntry : musicLibraryFolder.listFiles()) {
			if(fileEntry.isDirectory()) {
				populateMusicLibrary(fileEntry);
			} else {
				String fileName = fileEntry.getName();
				System.out.println(fileName);
				
				if(fileName.contains(".mp3")) {
					database.putSongMetadata(fileEntry.getAbsolutePath());
				}
			}
		}
		return false;
	}

	@Override
	public boolean createPlaylist(String playlistName) {
		// Create the new playlist
		Playlist playlist = new Playlist(playlistName);
		
		// Put the playlist in the database
		boolean putPlaylist = database.putPlaylist(playlist);
		
		if(putPlaylist == true) {
			System.out.println("The playlist, " +playlistName +" has been created.");
		} else {
			System.err.println("There was an error creating your playlist.");
			return false;
		}
		
		return putPlaylist;
	}
	
	@Override
	public boolean addSongToPlaylist(String songName, String playlistName) {
		if(songName.isEmpty()) {
			System.err.println("Cannot remove song from playlist. The song name is empty.");
			return false;
		}

		Playlist playlist = database.getPlaylist(playlistName);
		Song song = database.getSong(songName);
		
		if(playlist == null && song == null) {
			return false;
		}
		
		playlist.addSong(song);
		
		return database.putPlaylist(playlist);
	}

	@Override
	public boolean deleteSongFromPlaylist(String songName, String playlistName) {
		if(songName.isEmpty()) {
			System.err.println("Cannot remove song from playlist. The song name is empty.");
			return false;
		}

		Playlist playlist = database.getPlaylist(playlistName);
		
		if(playlist != null) {
			return false;
		}
		
		playlist.deleteSong(songName);
		
		return database.putPlaylist(playlist);
	}

	@Override
	public ArrayList<Song> listSongs(String playlistName) {
		Playlist playlist = database.getPlaylist(playlistName);
		
		if(playlist == null) {
			System.err.println("Playlist not found!");
			return null;
		}
		
		System.out.println("Playlist: " +playlist.getName());
		for(Song s : playlist.getAllSongs()) {
			System.out.println(s.getName() +" - " +s.getArtist());
		}
		
		return playlist.getAllSongs();
	}

	@Override
	public Playlist getPlaylist(String playlistName) {
		Playlist playlist = database.getPlaylist(playlistName);
		
		return playlist;
	}

	@Override
	public boolean renamePlaylist(String playlistName, String newPlaylistName) {
		Playlist playlist = database.getPlaylist(playlistName);
		
		playlist.rename(newPlaylistName);
		
		return database.putPlaylist(playlist);
	}

	@Override
	public boolean deletePlaylist(String playlistName) {
		return database.deletePlaylist(playlistName);
	}

	@Override
	public ArrayList<Playlist> getFeaturedPlaylists() {
		ArrayList<Playlist> featuredPlaylists = database.getFeaturedPlaylists();
		
		System.out.println("Feature Playlists:");
		for(Playlist fp : featuredPlaylists) {
			System.out.println(fp.getName());
		}
		
		return featuredPlaylists;
	}

	@Override
	public boolean rateArtist(String artistName, int rating) {
		Artist artist = database.getArtist(artistName);
		
		artist.rate(rating);
		
		return database.putArtist(artist);
	}

	@Override
	public boolean rateAlbum(String albumName, int rating) {
		Album album = database.getAlbum(albumName);
		
		album.rate(rating);
		
		return database.putAlbum(album);
	}

	@Override
	public boolean rateSong(String songName, int rating) {
		Song song = database.getSong(songName);
		
		song.rate(rating);
		
		return database.putSong(song);
	}

	@Override
	public boolean ratePlaylist(String playlistName, int rating) {
		Playlist playlist = database.getPlaylist(playlistName);
		
		playlist.rate(rating);
		
		return database.putPlaylist(playlist);
	}

	@Override
	public boolean addAllAlbumSongsToPlaylist(String albumName, String playlistName) {
		Album album = database.getAlbum(albumName);
		Playlist playlist = database.getPlaylist(playlistName);
		
		if(album == null || playlist == null) {
			return false;
		}
		
		ArrayList<Song> albumSongs = album.getSongs();
		
		playlist.addSongList(albumSongs);
		
		return database.putPlaylist(playlist);
	}

	@Override
	public Song getSong(String songName) {
		return database.getSong(songName);
	}

	@Override
	public Album getAlbum(String albumName) {
		return database.getAlbum(albumName);
	}

	@Override
	public Artist getArtist(String artistName) {
		return database.getArtist(artistName);
	}

	@Override
	public ArrayList<Artist> getSimilarArtist(String artistName) {
		Artist artist = database.getArtist(artistName);
		
		if(artist == null) {
			return null;
		}
		
		return database.getSimilarArtists(artistName, artist.getArtistData());
	}
}
