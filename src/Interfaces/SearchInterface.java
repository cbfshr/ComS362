package Interfaces;
import java.util.ArrayList;

import Objects.Album;
import Objects.Artist;
import Objects.Playlist;
import Objects.Song;

public interface SearchInterface {
	ArrayList<Album>	searchAlbum(String name);
	
	ArrayList<Artist>	searchArtist(String name);
	
	ArrayList<Song>		searchSong(String name);
	
	ArrayList<Song>		searchTopSongs(String artistName);
	
	ArrayList<Playlist>	searchPlaylist(String name);
}
