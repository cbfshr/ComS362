package Managers;

import java.io.File;
import java.util.ArrayList;
import Interfaces.LibraryInterface;
import Objects.Album;
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
			System.err.println("Song name is empty.");
			return false;
		}
		
		boolean addSong = database.addSongToPlaylist(songName, playlistName);
		
		return addSong;
	}

	@Override
	public boolean deleteSongFromPlaylist(String songName, String playlistName) {
		if(songName.isEmpty()) {
			System.err.println("Cannot remove song from playlist. The song name is empty.");
		}
		
		boolean deleteSong = database.deleteSongFromPlaylist(songName, playlistName);
		
		return deleteSong;
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
	public ArrayList<Song> listSongsAlbum(String albumName) {
		ArrayList<Album> albums = database.getAllAlbums(albumName);
	
		if(albums == null || albums.isEmpty()) {
			System.err.println("Album not found!");
			return null;
		}
		
		for(Album album : albums) {
			System.out.println("Album: " +album.getName()+" - " +album.getArtist());
			for(Song s : album.getSongs()) {
				System.out.println(s.getName());
			}
		}
		
		return null;
	}

	@Override
	public Playlist getPlaylist(String playlistName) {
		Playlist playlist = database.getPlaylist(playlistName);
		
		return playlist;
	}

	@Override
	public boolean renamePlaylist(String playlistName, String newPlaylistName) {
		return database.renamePlaylist(playlistName, newPlaylistName);
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
		return database.rateArtist(artistName, rating);
	}

	@Override
	public boolean rateAlbum(String albumName, int rating) {
		return database.rateAlbum(albumName, rating);
	}

	@Override
	public boolean rateSong(String songName, int rating) {
		return database.rateSong(songName, rating);
	}

	@Override
	public boolean ratePlaylist(String playlistName, int rating) {
		return database.ratePlaylist(playlistName, rating);
	}

	@Override
	public boolean addAllAlbumSongsToPlaylist(String albumName, String playlistName) {
		Album album = database.getAlbum(albumName);
		
		if(album == null) {
			return false;
		}
		
		ArrayList<Song> albumSongs = album.getSongs();
		
		for(Song s : albumSongs) {
			database.addSongToPlaylist(s.getName(), playlistName);
		}
		
		return false;
	}

	@Override
	public boolean addAllArtistSongsToPlaylist(String artistName, String playlistName) {
		// TODO Auto-generated method stub
		return false;
	}
}
