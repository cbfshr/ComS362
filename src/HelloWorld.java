import Controllers.MusicLibrary;
import Objects.Playlist;

public class HelloWorld {
	public static void main(String[] args) {
		MusicLibrary musicLibrary = new MusicLibrary();
		
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
		}
	}
}
