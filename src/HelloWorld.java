import java.util.ArrayList;

import Objects.Playlist;
import Objects.Song;

public class HelloWorld {
	public static void main(String[] args) {
		System.err.println("This class is my favorite in the entire world!");
		
		// Create a list of songs
		ArrayList<Song> songs = new ArrayList<Song>();
		songs.add(new Song(1, "Hello, world!"));
		songs.add(new Song(2, "Goodbye, world... :("));
		
		// Create a playlist
		Playlist playlist = new Playlist(1231, "Best Playlist", songs);
		
		// Add a song to playlist
		playlist.addSong(new Song(3, "What up, world?"));
		
		// Get all songs in the playlist
		ArrayList<Song> blah = playlist.getAllSongs();
		
		// List all the songs in the playlist
		for(Song s : blah) {
			System.err.println(s.getName());
		}
	}
}
