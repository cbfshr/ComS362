package Interfaces;
import java.util.ArrayList;

import Objects.Album;
import Objects.Artist;
import Objects.Playlist;
import Objects.Song;

public interface DatabaseInterface {
	Song				getSong(int songID, int playlistID);
	
    Artist				getArtist(int artistID);
    
    Playlist			getPlaylist(int playlistID);

    Playlist			getPlaylist(String playlistName);
    
    boolean				putPlaylist(Playlist playlist);
    
    ArrayList<Album>	getAllAlbums(String albumName);
    
    ArrayList<Artist>	getAllArtists(String artistName);

    ArrayList<Song>		getAllSongs(String songName);
    
    ArrayList<Song>		getTopSongs(int artistID);
    
    ArrayList<Playlist>	getAllPlaylists(String playlistName);
}
