import java.util.ArrayList;

import Controllers.MusicController;
import Objects.*;

public class HelloWorld {
	public static void main(String[] args) {
		MusicController musicController = new MusicController();
		
		//WHERE ArtistID = " +artistID +" 
		// Right now this gets all songs!!!
		//ArrayList<Song> songs = musicController.searchSong("Boulevard of Broken Dreams");
		musicController.searchTopSongs("");
		
		// Design flaw: When we are searching songs, should we be creating them with Artist and Album objects?
		// If we do this, we would have to build an artist and album for every song retrieved from the database
		// This sounds like an awful idea. There must be an easier way around it..
		
		
		/*
		// Create a new playlist
		if(!musicLibrary.createPlaylist("Playlist1")) {
			System.err.println("There was an error creating the playlist.");
		}
		
		// Get playlist
		Playlist playlist = musicLibrary.getPlaylist("Playlist1");
		if(playlist != null) {
			System.err.println("The playlist, " +playlist.getName() +" has been retrieved.");
		} else {
			System.err.println("There was an error getting the playlist.");
		}*/
	}
}
