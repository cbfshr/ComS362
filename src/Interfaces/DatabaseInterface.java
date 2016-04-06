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
    
    boolean				putPlaylist(Playlist p);
    
    ArrayList<Album>	getAllAlbums(String name);
    
    ArrayList<Artist>	getAllArtists(String name);
    
    ArrayList<Song>		getAllSongs(String name);
    
    ArrayList<Playlist>	getAllPlaylists(String name);
}
