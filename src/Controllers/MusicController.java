package Controllers;
import java.util.ArrayList;

import Interfaces.MusicControllerInterface;
import Objects.Album;
import Objects.Artist;
import Objects.Playlist;
import Objects.Song;

public class MusicController implements MusicControllerInterface {

	@Override
	public ArrayList<Song> searchSong(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Artist> searchArtist(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Album> searchAlbum(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Playlist> searchPlaylist(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Song> searchTopSongs(int artistID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Playlist createPlaylist(String playlistName) {
		// TODO Auto-generated method stub
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
