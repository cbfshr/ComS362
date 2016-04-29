package coms362;

import java.util.ArrayList;
import java.util.Scanner;

import Controllers.MusicController;

import java.io.*;
import java.net.URL;

public class CommandLineInterface {
	private MusicController musicController;

	public CommandLineInterface()
	{
		this.musicController = new MusicController();
	}
	
	public void getInput()
	{
		String input = null;
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("\nEnter a command (type 'help' to view commands): ");
		input = scanner.nextLine();
		while(!input.toLowerCase().equals("quit"))
		{
			ArrayList<String> commands = parseInput(input.trim());
			
			switch(commands.get(0)){
				case "search":
					search(commands,scanner);
					break;
				case "artist":
					artist(commands,scanner);
					break;
				case "album":
					album(commands,scanner);
					break;
				case "song":
					song(commands,scanner);
					break;
				case "playlist":
					playlist(commands,scanner);
					break;
				case "new":
					newRelease(commands);
					break;
				case "categories":
					categories(commands);
					break;
				case "populate":
					System.out.println("Type the path to the music library:");
					musicController.populateMusicDatabase(scanner.nextLine().trim());
					break;
				case "quit":
					scanner.close();
					return;
				case "help":
					try {
						showCommands();
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;
				default:
					System.err.println("Unknown Command");
			}

			System.out.print("\nEnter a command (type 'help' to view commands): ");
			input = scanner.nextLine();
		}
		
		scanner.close();
	}
	

	private ArrayList<String> parseInput(String input)
	{
		int i = 0;
		int lastIndex = 0;
		ArrayList<String> commands = new ArrayList<String>();
		while( i < input.length())
		{
			if(input.charAt(i) == ' ' || i == input.length()-1)
			{
				commands.add(input.substring(lastIndex,i+1).trim());
				lastIndex = i;
			}
			i++;
		}
		
		return commands;
	}
	
	private void showCommands() throws IOException
	{
		FileReader in = null;
		try {
			URL url = getClass().getResource("CommandList");
			in = new FileReader(url.getPath());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		char c;
		while((c = (char)in.read()) != (char)-1)
		{
			System.out.printf("%c",c);
		}
		
		System.out.println("\n\n\n");
		try {
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void search(ArrayList<String> search,Scanner scanner)
	{
		switch(search.get(1))
		{
			case "artist":
				this.musicController.searchArtist(scanner.nextLine().trim());
				break;
			case "album":
				this.musicController.searchAlbum(scanner.nextLine().trim());
				break;
			case "song":
				this.musicController.searchSong(scanner.nextLine().trim());
				break;
			case "playlist":
				this.musicController.searchPlaylist(scanner.nextLine().trim());
				break;
			default: 
				System.err.println("unknown command");
		}
	}
	
	private void artist(ArrayList<String> artist,Scanner scanner)
	{
		switch(artist.get(1))
		{
			case "top":
				this.musicController.searchTopSongs(scanner.nextLine().trim());
				break;
			case "data":
				this.musicController.searchArtistDetails(scanner.nextLine().trim());
				break;
			case "similar":
				this.musicController.getSimilarArtists(scanner.nextLine().trim());
				break;
			case "rate":
				this.musicController.rateArtist(scanner.nextLine().trim(), Integer.parseInt(scanner.nextLine().trim()));
				break;
		}
	}
	
	private void album(ArrayList<String> album,Scanner scanner)
	{
		switch(album.get(1))
		{
			case "data":
				this.musicController.searchAlbumDetails(scanner.nextLine().trim());
				break;
			case "compare":
				System.out.println("Enter two album names:");
				String album1 = scanner.nextLine().trim();
				String album2 = scanner.nextLine().trim();
				System.out.println("Printing information about the two songs:");
				this.musicController.searchAlbumDetails(album1);
				this.musicController.searchAlbumDetails(album2);
				break;
			case "rate":
				this.musicController.rateAlbum(scanner.nextLine().trim(),Integer.parseInt(scanner.nextLine().trim()));
				break;
			case "list":
				this.musicController.listSongsAlbum(scanner.nextLine().trim());
				break;
		}
	}
	
	private void song(ArrayList<String> song,Scanner scanner)
	{
		switch(song.get(1))
		{
			case "data":
				this.musicController.searchSongDetails(scanner.nextLine().trim());
				break;
			case "compare":
				System.out.println("Enter two song names:");
				String song1 = scanner.nextLine().trim();
				String song2 = scanner.nextLine().trim();
				System.out.println("Printing information about the two songs:");
				this.musicController.searchSongDetails(song1);
				this.musicController.searchSongDetails(song2);
				break;
			case "rate":
				this.musicController.rateSong(scanner.nextLine().trim(), Integer.parseInt(scanner.nextLine().trim()));
				break;
		}
	}
	
	private void playlist(ArrayList<String> playlist,Scanner scanner)
	{
		switch(playlist.get(1))
		{
			case "create":
				this.musicController.createPlaylist(scanner.nextLine().trim());
				break;
			case "add":
				switch(scanner.nextLine().trim())
				{
					case "song":
						System.out.println("Enter a song name then the playlist.");
						this.musicController.addSongToPlaylist(scanner.nextLine().trim(), scanner.nextLine().trim());
						break;
					case "artist":
						System.out.println("Enter an artist name then the playlist.");
						this.musicController.addAllArtistSongsToPlaylist(scanner.nextLine().trim(), scanner.nextLine().trim());
						break;
					case "album":
						System.out.println("Enter an ablum name then the playlist.");
						this.musicController.addAllAlbumSongsToPlaylist(scanner.nextLine().trim(), scanner.nextLine().trim());
						break;
				}
				break;
			case "remove":
				System.out.println("Enter the song name to be removed followed by the playlist name.");
				this.musicController.deleteSongFromPlaylist(scanner.nextLine().trim(), scanner.nextLine().trim());
				break;
			case "list":
				this.musicController.listSongs(scanner.nextLine().trim());
				break;
			case "featured":
				this.musicController.getFeaturedPlaylists();
				break;
			case "rename":
				this.musicController.renamePlaylist(scanner.nextLine().trim(), scanner.nextLine().trim());
				break;
			case "delete":
				this.musicController.deletePlaylist(scanner.nextLine().trim());
				break;
			case "rate":
				this.musicController.ratePlaylist(scanner.nextLine().trim(), Integer.parseInt(scanner.nextLine().trim()));
				break;
		}
	}
	
	private void newRelease(ArrayList<String> newRelease)
	{
		this.musicController.getNewReleases();
	}
	
	private void categories(ArrayList<String> categories)
	{
		this.musicController.searchGenre("");
	}
	
}
