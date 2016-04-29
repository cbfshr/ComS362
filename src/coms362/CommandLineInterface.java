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
		this.musicController = null; //new MusicController();
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
					search(commands);
					break;
				case "artist":
					artist(commands);
					break;
				case "album":
					album(commands);
					break;
				case "song":
					song(commands);
					break;
				case "playlist":
					playlist(commands);
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
	private void search(ArrayList<String> search)
	{
		switch(search.get(1))
		{
			case "artist":
				if(search.size() < 3 )
				{
					this.musicController.searchArtist("");
				}
				else
				{
					this.musicController.searchArtist(search.get(2));
				}
				break;
			case "album":
				if(search.size() < 3 )
				{
					this.musicController.searchAlbum("");
				}
				else
				{
					this.musicController.searchAlbum(search.get(2));
				}
				break;
			case "song":
				if(search.size() < 3 )
				{
					this.musicController.searchSong("");
				}
				else
				{
					this.musicController.searchSong(search.get(2));
				}
				break;
			case "playlist":
				if(search.size() < 3 )
				{
					this.musicController.searchPlaylist("");
				}
				else
				{
					this.musicController.searchPlaylist(search.get(2));
				}
				break;
			default: 
				System.err.println("unknown command");
		}
	}
	private void artist(ArrayList<String> artist)
	{
		switch(artist.get(1))
		{
			case "top":
				this.musicController.searchTopSongs(artist.get(2));
				break;
			case "data":
				//this.musicController.searchArtist(name)
				break;
			case "similar":
				break;
			case "rate":
				this.musicController.rateArtist(artist.get(2), Integer.parseInt(artist.get(3)));
				break;
		}
	}
	private void album(ArrayList<String> album)
	{
		switch(album.get(1))
		{
			case "data":
				break;
			case "compare":
				break;
			case "rate":
				this.musicController.rateAlbum(album.get(2),Integer.parseInt(album.get(3)));
				break;
			case "list":
				this.musicController.listSongsAlbum(album.get(2));
				break;
		}
	}
	private void song(ArrayList<String> song)
	{
		switch(song.get(1))
		{
			case "data":
				break;
			case "compare":
				break;
			case "rate":
				this.musicController.rateSong(song.get(2), Integer.parseInt(song.get(3)));
				break;
		}
	}
	private void playlist(ArrayList<String> playlist)
	{
		switch(playlist.get(1))
		{
			case "create":
				this.musicController.createPlaylist(playlist.get(2));
				break;
			case "add":
				switch(playlist.get(2))
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
				//this.musicController.deleteSong(playlist.get(3),playlist.get(4));
				break;
			case "list":
				this.musicController.listSongs(playlist.get(2));
				break;
			case "featured":
				this.musicController.getFeaturedPlaylists();
				break;
			case "rename":
				this.musicController.renamePlaylist(playlist.get(2), playlist.get(3));
				break;
			case "delete":
				this.musicController.deletePlaylist(playlist.get(2));
				break;
			case "rate":
				this.musicController.ratePlaylist(playlist.get(2), Integer.parseInt(playlist.get(3)));
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
