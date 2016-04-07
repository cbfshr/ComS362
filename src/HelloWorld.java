import java.util.ArrayList;

import Controllers.MusicController;
import Objects.*;

public class HelloWorld {
	public static void main(String[] args) {
		MusicController musicController = new MusicController();
		
		// Right now these do not check against the passed arguments!!!
		System.out.println("Top Songs:");
		musicController.searchTopSongs("");
		
		System.out.println("\nArtists");
		musicController.searchArtist("");
		
		System.out.println("\nAlbums");
		musicController.searchAlbum("");
		
		System.out.println("\nPlaylists");
		musicController.searchPlaylist("");
		
		musicController.createPlaylist("Playlist1");

		System.out.println("\nPlaylists");
		musicController.searchPlaylist("");
		
		musicController.getPlaylist("Playlist1");
		
		System.out.println("\nSongs");
		musicController.searchSong("");

		System.out.println();
		System.out.println();
		System.out.println();
		musicController.listSongs("Playlist1");
		
		Song song = musicController.searchSong("Victorious").get(0);
		if(song != null) {
			musicController.addSong(song, "Playlist1");
		}

		musicController.listSongs("Playlist1");
		
		
		// Design flaw: When we are searching songs, should we be creating them with Artist and Album objects?
		// If we do this, we would have to build an artist and album for every song retrieved from the database
		// This sounds like an awful idea. There must be an easier way around it..
	}
}
