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
				case "quit":
					scanner.close();
					return;
				default:
					System.err.println("Unknown Command");
				try {
					showCommands();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

					
			}
			
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
				//this.musicController.searchArtist(name)
				break;
			case "similar":
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
				break;
			case "compare":
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
				break;
			case "compare":
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
						//this.playlist(playlist.get(4),playlist.get(3));
						break;
					case "artist":
						break;
					case "album":
						break;
				}
				break;
			case "remove":
				//this.musicController.deleteSong(playlist.get(2),playlist.get(3));
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
		
	}
	private void categories(ArrayList<String> categories)
	{
		this.musicController.searchGenre("");
	}
	
}
