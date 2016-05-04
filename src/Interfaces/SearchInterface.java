package Interfaces;

import java.util.ArrayList;
import Objects.Album;
import Objects.Artist;
import Objects.Playlist;
import Objects.Song;

public interface SearchInterface {
	ArrayList<Album>	searchAlbum(String name, boolean showDetails);

	ArrayList<Artist>	searchArtist(String name, boolean showDetails);

	ArrayList<String>	searchGenre(String name);

	ArrayList<Song>		searchSong(String name, boolean showDetails);
	
	ArrayList<Song>		searchTopSongs(String artistName);
	
	ArrayList<Playlist>	searchPlaylist(String name);
	
	ArrayList<Album>	getNewReleases();
}
