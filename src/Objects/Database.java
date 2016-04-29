package Objects;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import Database.DatabaseConnection;
import Interfaces.DatabaseInterface;

public class Database implements DatabaseInterface {
	Connection database;
	Statement statement;
	
	public Database() {
		// Initialize connection to the database
		DatabaseConnection databaseConnection = new DatabaseConnection();

		// Connect to the database
		database = databaseConnection.ConnectToDatabase();
	}
	
//	public void listFilesForFolder(final File folder) {
//		for(final File fileEntry : folder.listFiles()) {
//			if(fileEntry.isDirectory()) {
//				listFilesForFolder(fileEntry);
//			} else {
//				String fileName = fileEntry.getName();
//				System.out.println(fileName);
//				if(fileName.contains(".mp3")) {
//					putSongMetadata(fileEntry.getAbsolutePath());
//				}
//			}
//		}
//	}
	
	public void putSongMetadata(String filePath) {
		try {
			InputStream input = new FileInputStream(new File(filePath));
			ContentHandler handler = new DefaultHandler();
			Metadata metadata = new Metadata();
			Parser parser = new Mp3Parser();
			ParseContext parseCtx = new ParseContext();
			parser.parse(input, handler, metadata, parseCtx);
			input.close();
			
			// Retrieve the necessary info from metadata
//			System.out.println("----------------------------------------------");
			System.out.println("Title: " + metadata.get("title"));
			System.out.println("Artist: " + metadata.get("xmpDM:artist"));
			System.out.println("Album: " + metadata.get("xmpDM:album"));
			System.out.println("Genre: "+metadata.get("xmpDM:genre"));
			System.out.println("Release Date: "+metadata.get("xmpDM:releaseDate"));
			System.out.println("Length: "+metadata.get("xmpDM:duration"));
			System.out.println("Track Number: "+metadata.get("xmpDM:trackNumber"));
			System.out.println("Sample Rate: "+metadata.get("samplerate"));
			System.out.println("Content Type: "+metadata.get("Content-Type"));

			// GENRE
			int genreID = -1;
			String genreNameMetadata = metadata.get("xmpDM:genre");
			if(genreNameMetadata != null && !genreNameMetadata.isEmpty()) {
				// check if the artist exists
				ResultSet results;
				try {
					statement = database.createStatement();
					String query =   "SELECT ID, GenreName FROM Genres "
									+"WHERE GenreName = '" +genreNameMetadata +"'";
					results = statement.executeQuery(query);
					
					if(!results.first()) {
						// Genre does not exist, so create it
						try {
							statement = database.createStatement();
							query =   "INSERT INTO Genres(GenreName) VALUES('" +genreNameMetadata +"')";
							statement.executeUpdate(query);
						} catch (SQLException e) {
							e.printStackTrace();
						}
						
						// Now get the genre ID
						query =   "SELECT ID, GenreName FROM Genres "
								 +"WHERE GenreName = '" +genreNameMetadata +"'";
						results = statement.executeQuery(query);
					}
					
					if(results.first()) {
						genreID = results.getInt("ID");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			// ARTIST
			int artistID = -1;
			// Check if the artist has already been added to the database
			String artistNameMetadata = metadata.get("xmpDM:artist");
			if(artistNameMetadata != null && !artistNameMetadata.isEmpty()) {
				if(artistNameMetadata.contains("'")) {
					artistNameMetadata = artistNameMetadata.replace("'", "''");
				}
				
				// check if the artist exists
				Artist artist = getArtist(artistNameMetadata);
				
				// If the artist is not in the database...
				if(artist == null) {
					System.out.println("Adding artist to database.");
					// Add the artist to the database
					try {
						statement = database.createStatement();
						String query =   "INSERT INTO Artists(ArtistName, GenreID) VALUES('" +artistNameMetadata +"', " +genreID +")";
						statement.executeUpdate(query);
					} catch (SQLException e) {
						e.printStackTrace();
					}
					
					// Get the artist ID from the database
					artist = getArtist(artistNameMetadata);
					if(artist != null) {
						System.out.println("Got the artist! " +artist.getArtistName() +" " +artist.getArtistID());
						artistID = artist.getArtistID();
					}
				} else {
					artistID = artist.getArtistID();
				}
			}
			

			// ALBUM
			int albumID = -1;
			String albumNameMetadata = metadata.get("xmpDM:album");
			if(genreID != -1 && artistID != -1 && albumNameMetadata != null && !albumNameMetadata.isEmpty()) {
				// check if the artist exists
				Album album = getAlbum(albumNameMetadata);
				
				if(albumNameMetadata.contains("'")) {
					albumNameMetadata = albumNameMetadata.replace("'", "''");
				}
				
				// If the artist is not in the database...
				if(album == null) {
					System.out.println("Adding album to database. ArtistID: " +artistID);
					// Add the artist to the database
					try {
						statement = database.createStatement();
						String query =   "INSERT INTO Albums(AlbumName, ArtistID, GenreID, ReleaseDate) "
										+"VALUES('" +albumNameMetadata +"', " +artistID +", " +genreID +", '" +metadata.get("xmpDM:releaseDate") +"')";
						statement.executeUpdate(query);
					} catch (SQLException e) {
						e.printStackTrace();
					}
					
					// Get the artist ID from the database
					album = getAlbum(albumNameMetadata);
					if(album != null) {
						System.out.println("Got the album! " +album.getName() +" " +album.getID());
						albumID = album.getID();
					}
				} else {
					System.out.println("The album already exists in the database.");
					albumID = album.getID();
				}
			}
			
			if(artistID != -1 && albumID != -1 && genreID != -1)  {
				// SONG
				try {				
					Random rand = new Random();
					int plays = rand.nextInt(300);
					String title = metadata.get("title");
					
					if(title.contains("'")) {
						System.out.println("Song has apostrophe. Modifying name to comply with SQL.");
						title = title.replace("'", "''");
						System.out.println(title);
					}
					
					statement = database.createStatement();
					String query = "INSERT INTO Songs(SongName, ArtistID, AlbumID, GenreID, Duration, TrackNumber, SampleRate, ContentType, Plays) "
								  +"VALUES('" +title +"', " +artistID +", " +albumID +", " +genreID +", '" +metadata.get("xmpDM:duration")
								  		+"', '" +metadata.get("xmpDM:trackNumber") +"', '" +metadata.get("samplerate") +"', '" +metadata.get("Content-Type") +"', " +plays
								  +")";
					
					statement.executeUpdate(query);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (TikaException e) {
			e.printStackTrace();
		}
	}
	//-------------------------------------------------------------------------------------------------------------------
	
//	@Override
//	public Song getSong(int songID, int playlistID) {
//		return null;
//	}

	@Override
	public Artist getArtist(String artistName) {
		ArrayList<Artist> artists = getAllArtists(artistName);
		
		if(artists != null) {
			for(Artist a : artists) {
				System.out.println("getArtist: Artist Name: " +a.getArtistName());
			}
			
			return artists.get(0);
		}
		
		return null;
	}
	
	@Override
	public Album getAlbum(String albumName) {
		ArrayList<Album> albums = getAllAlbums(albumName);
		
		if(albums != null && albums.size() > 0) {
			for(Album a : albums) {
				System.out.println("getAlbum: AlbumName: " +a.getName());
			}
			
			return albums.get(0);
		}
		
		return null;
	}
	
	@Override
	public Playlist getPlaylist(String playlistName) {
		try {
			statement = database.createStatement();
			
			String playlistQuery =   "SELECT * "
									+"FROM Playlists "
									+"WHERE playlistName = '" +playlistName +"'";
			ResultSet playlistInfo = statement.executeQuery(playlistQuery);
	
			// If the playlist does not exist, the query results will be empty
			if(!playlistInfo.next()) {
				return null;
			}
			
			// To create the playlist, we need the ID and name, which we can grab 
			Playlist playlist = new Playlist(playlistInfo.getInt("ID"), playlistInfo.getString("PlaylistName"), playlistInfo.getInt("Featured"), playlistInfo.getInt("Rating"));
	
			// Get all the songs in the playlist
			// This will return a bunch of tuples that we can build into the list of songs in the playlist		
			
//			query =  "SELECT songs.ID, songs.SongName, artists.ArtistName, albums.AlbumName, songs.Duration, songs.TrackNumber, songs.SampleRate, songs.ContentType, songs.Genre, songs.Plays "
//					+"FROM Songs songs "
//					+"INNER JOIN Artists artists ON (songs.ArtistID = artists.ID) "
//					+"INNER JOIN Albums albums ON (songs.AlbumID = albums.ID) ";
			String playlistSongsQuery =  "SELECT songs.ID, songs.SongName, artists.ArtistName, albums.AlbumName, songs.Duration, songs.TrackNumber, songs.SampleRate, songs.ContentType, genres.GenreName, songs.Plays, songs.Rating "
										+"FROM PlaylistSongs playlistSongs "
										+"INNER JOIN Songs songs ON (playlistSongs.SongID = songs.ID) "
										+"INNER JOIN Playlists playlists ON (playlistSongs.PlaylistID = playlists.ID) "
										+"	INNER JOIN Artists artists ON (songs.ArtistID = artists.ID) "
										+"	INNER JOIN Albums albums ON (songs.AlbumID = albums.ID) "
										+"	INNER JOIN Genres genres ON (songs.GenreID = genres.ID) "
										+"WHERE PlaylistID = " +playlistInfo.getInt("ID");
			ResultSet results = statement.executeQuery(playlistSongsQuery);
			
			// Add each song to the playlist item
			while(results.next()) {
				playlist.addSong(
						new Song(
								results.getInt("songs.ID"),
								results.getString("songs.SongName"),
								results.getString("artists.ArtistName"),
								results.getString("albums.AlbumName"),
								results.getString("songs.Duration"),
								results.getString("songs.TrackNumber"),
								results.getString("songs.SampleRate"),
								results.getString("songs.ContentType"),
								results.getString("genres.GenreName"),
								results.getInt("songs.Plays"),
								results.getInt("songs.Rating")
							)
				);
			}
			
			System.out.println("Playlist has been retrieved from the database.");
			return playlist;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean putPlaylist(Playlist playlist) {
		try {
			statement = database.createStatement();
			
			// Check if the playlist already exists
			if(getPlaylist(playlist.getName()) != null) {
				// If it exists, UPDATE
				String query =   "UPDATE Playlists "
								+"SET PlaylistName = '" +playlist.getName() +"'"
								+"WHERE ID = '" +playlist.getID() +"'";
				statement.executeUpdate(query);
				
				// Do stuff to update the songs
				ArrayList<Song> songs = playlist.getAllSongs();
				String songQuery;
				ResultSet results;
				for(Song song : songs) {
					// Check if the song is already in the playlist
					songQuery =  "SELECT * FROM PlaylistSongs "
								+"WHERE SongID = " +song.getID();
					
					results = statement.executeQuery(songQuery);
					
					// If the results are empty, add the song to the playlist in the database
					if(!results.next()) {
						songQuery =  "INSERT INTO PlaylistSongs(SongID, PlaylistID) "
									+"VALUES(" +song.getID() +", " +playlist.getID() +")";
						statement.executeUpdate(songQuery);
					}
				}
			} else {
				// If it doesn't exist, INSERT
				Random rand = new Random();
				int featured = rand.nextInt(2);
				System.out.println(featured);
				
				String query =   "INSERT INTO Playlists(PlaylistName, Featured) "
								+"VALUES('" +playlist.getName() +"', " +featured +")";
				statement.executeUpdate(query);
				
				// Do stuff to insert the songs
				ArrayList<Song> songs = playlist.getAllSongs();
				String songQuery;
				for(Song s : songs) {
					songQuery =  "INSERT INTO PlaylistSongs(SongID, PlaylistID) "
								+"VALUES(" +s.getID() +", " +playlist.getID() +")";
					statement.executeUpdate(songQuery);
				}
			}
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		
		return false;
	}
	
	@Override
	public boolean addSongToPlaylist(String songName, String playlistName) {
		try {
			statement = database.createStatement();
			
			String query =   "INSERT INTO PlaylistSongs(SongID, PlaylistID) "
							+"SELECT songs.ID, playlists.ID "
							+"FROM Songs songs, Playlists playlists "
							+"WHERE songs.SongName = '" +songName +"' "
							+"AND playlists.PlaylistName = '" +playlistName +"'";
			statement.executeUpdate(query);
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean deleteSongFromPlaylist(String songName, String playlistName) {
		try {
			statement = database.createStatement();
			
			String query =   "DELETE FROM PlaylistSongs "
							+"WHERE playlistSongs.SongID = (SELECT ID FROM Songs "
							+"    WHERE SongName = '" +songName +"') "
							+"AND playlistSongs.PlaylistID = (SELECT ID FROM Playlists "
							+"    WHERE PlaylistName = '" +playlistName +"')";
							//+"INNER JOIN Songs songs ON (playlistSongs.SongID = songs.ID) "
							//+"INNER JOIN Playlists playlists ON (playlistSongs.PlaylistID = playlists.ID) "
							//+"WHERE songs.SongName = '" +songName +"' "
							//+"AND playlists.PlaylistName = '" +playlistName +"'";
			statement.executeUpdate(query);
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public ArrayList<Artist> getAllArtists(String artistName) {
		try {
			statement = database.createStatement();
			String query;
			if(!artistName.isEmpty()) {
				query =   "SELECT * FROM Artists artists "
								+"INNER JOIN Genres genres ON (artists.GenreID = genres.ID) "
								+"WHERE ArtistName = '" +artistName +"' ";
			} else {
				query =   "SELECT * FROM Artists artists "
								+"INNER JOIN Genres genres ON (artists.GenreID = genres.ID) ";
			}
			ResultSet results = statement.executeQuery(query);
			
			if(!results.first()) {
				System.out.println("Artist does not exist");
				return null;
			}
			
			System.out.println("Getting artist");
			Statement songsStatement = database.createStatement();
			Statement albumsStatement = database.createStatement();
			ArrayList<Artist> artists = new ArrayList<Artist>();
			do {
				System.out.println("Artist retrieved: " +results.getString("ArtistName"));
				
				int artistID = results.getInt("ID");
				int rating = results.getInt("Rating");
				String genre = results.getString("GenreName");
				
				// Get number of albums from artist:
				int numberOfAlbums = 0;
				query =  "SELECT COUNT(*) FROM Albums "
						+"WHERE ArtistID = " +artistID;
				ResultSet albumResults = albumsStatement.executeQuery(query);
				
				if(albumResults.first()) {
					numberOfAlbums = albumResults.getInt("COUNT(*)");
				}
				
				// Get number of songs from artist:
				int numberOfSongs = 0;
				query =  "SELECT COUNT(*) FROM Songs "
						+"WHERE ArtistID = " +artistID;
				ResultSet songResults = songsStatement.executeQuery(query);
				
				if(songResults.first()) {
					numberOfSongs = songResults.getInt("COUNT(*)");
				}
				
				artists.add(new Artist(artistID, artistName, rating, numberOfSongs, numberOfAlbums, genre));
			} while(results.next());
			
			return artists;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public ArrayList<Album> getAllAlbums(String albumName) {
		try {
			statement = database.createStatement();
			String query;
			
			if(albumName.isEmpty()) {
				query =   "SELECT albums.ID, albums.AlbumName, artists.ArtistName, albums.Rating, albums.ReleaseDate "
							+"FROM Albums albums "
							+"INNER JOIN Artists artists ON (albums.ArtistID = artists.ID)";
			} else {
				if(albumName.contains("'")) {
					albumName = albumName.replace("'", "''");
				}
				query =   "SELECT albums.ID, albums.AlbumName, artists.ArtistName, albums.Rating, albums.ReleaseDate "
						 +"FROM Albums albums "
						 +"INNER JOIN Artists artists ON (albums.ArtistID = artists.ID)"
						 +"WHERE AlbumName = '" +albumName +"'";
			}
			
			ResultSet results = statement.executeQuery(query);

			Statement songsStatement = database.createStatement();
			ArrayList<Album> albums = new ArrayList<Album>();
			while(results.next()) {
				// Get number of songs in the album:
				int numberOfSongs = 0;
				query =  "SELECT COUNT(*) FROM Songs "
						+"WHERE AlbumID = " +results.getInt("ID");
				ResultSet songResults = songsStatement.executeQuery(query);
				
				if(songResults.first()) {
					numberOfSongs = songResults.getInt("COUNT(*)");
				}
				
				Album album = new Album(results.getInt("ID"), results.getString("AlbumName"), results.getString("ArtistName"), results.getInt("Rating"), results.getString("ReleaseDate"), numberOfSongs);
				albums.add(album);
			}
			
			
			for(Album album : albums) {
				System.err.println(album.getName() + album.getID());
				// Get all the songs in the album
				// This will return a bunch of tuples that we can build into the list of songs in the playlist			
				String playlistSongsQuery =  "SELECT songs.ID, songs.SongName, artists.ArtistName, albums.AlbumName, songs.Duration, songs.TrackNumber, songs.SampleRate, songs.ContentType, genres.GenreName, songs.Plays, songs.Rating "
											+"FROM Songs songs "
											//+"INNER JOIN Playlists playlists ON (playlistSongs.PlaylistID = playlists.ID) "
											+"INNER JOIN Artists artists ON (songs.ArtistID = artists.ID) "
											+"INNER JOIN Albums albums ON (songs.AlbumID = albums.ID) "
											+"INNER JOIN Genres genres ON (songs.GenreID = genres.ID) "
											+"WHERE AlbumID = " +album.getID();
				ResultSet songResults = statement.executeQuery(playlistSongsQuery);
				
				// Add each song to the playlist item
				while(songResults.next()) {
					System.err.println(songResults.getString("SongName"));
					album.addSong(
						new Song(
							songResults.getInt("songs.ID"),
							songResults.getString("songs.SongName"),
							songResults.getString("artists.ArtistName"),
							songResults.getString("albums.AlbumName"),
							songResults.getString("songs.Duration"),
							songResults.getString("songs.TrackNumber"),
							songResults.getString("songs.SampleRate"),
							songResults.getString("songs.ContentType"),
							songResults.getString("genres.GenreName"),
							songResults.getInt("songs.Plays"),
							songResults.getInt("songs.Rating")
						)
					);
				}
			}
			
			return albums;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public ArrayList<String> getAllGenres(String genreName) {
		try {
			statement = database.createStatement();
			
			String query;
			if(!genreName.isEmpty()) {
				query =  "SELECT * FROM Genres "
						+"WHERE GenreName = '" +genreName +"'";
			} else {
				query =  "SELECT * FROM Genres";
			}
			ResultSet results = statement.executeQuery(query);
			
			if(!results.first()) {
				System.err.println("Genre does not exist");
				return null;
			}
			
			System.out.println("Getting genre");
			ArrayList<String> genres = new ArrayList<String>();
			do {
				System.out.println("Genre retrieved: " +results.getString("GenreName"));
				genres.add(results.getString("GenreName"));
			} while(results.next());
			
			return genres;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public ArrayList<Song> getAllSongs(String name) {
		try {
			statement = database.createStatement();
			
			String query;
			
			if(name.isEmpty()) {
				query =  "SELECT songs.ID, songs.SongName, artists.ArtistName, albums.AlbumName, songs.Duration, songs.TrackNumber, songs.SampleRate, songs.ContentType, genres.GenreName, songs.Plays, songs.Rating "
						+"FROM Songs songs "
						+"INNER JOIN Artists artists ON (songs.ArtistID = artists.ID) "
						+"INNER JOIN Albums albums ON (songs.AlbumID = albums.ID) "
						+"INNER JOIN Genres genres ON (songs.GenreID = genres.ID) ";
			} else {
				query =  "SELECT songs.ID, songs.SongName, artists.ArtistName, albums.AlbumName, songs.Duration, songs.TrackNumber, songs.SampleRate, songs.ContentType, genres.GenreName, songs.Plays, songs.Rating "
						+"FROM Songs songs "
						+"INNER JOIN Artists artists ON (songs.ArtistID = artists.ID) "
						+"INNER JOIN Albums albums ON (songs.AlbumID = albums.ID) "
						+"INNER JOIN Genres genres ON (songs.GenreID = genres.ID) "
						+"WHERE songs.SongName = '" +name +"'";
				
			}
			
			ResultSet results = statement.executeQuery(query);
			
			ArrayList<Song> songs = new ArrayList<Song>();
			while(results.next()) {
				songs.add(
						new Song(
								results.getInt("songs.ID"),
								results.getString("songs.SongName"),
								results.getString("artists.ArtistName"),
								results.getString("albums.AlbumName"),
								results.getString("songs.Duration"),
								results.getString("songs.TrackNumber"),
								results.getString("songs.SampleRate"),
								results.getString("songs.ContentType"),
								results.getString("genres.GenreName"),
								results.getInt("songs.Plays"),
								results.getInt("songs.Rating")
							)
				);
			}
			
			return songs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public ArrayList<Song> getTopSongs(String artistName) {		
		try {
			statement = database.createStatement();
			
			String query =   "SELECT songs.ID, songs.SongName, artists.ArtistName, albums.AlbumName, songs.Duration, songs.TrackNumber, songs.SampleRate, songs.ContentType, genres.GenreName, songs.Plays, songs.Rating "
							+"FROM Songs songs "
							+"INNER JOIN Artists artists ON (songs.ArtistID = artists.ID) "
							+"INNER JOIN Albums albums ON (songs.AlbumID = albums.ID) "
							+"INNER JOIN Genres genres ON (songs.GenreID = genres.ID) "
							+"ORDER BY Plays DESC";
			ResultSet results = statement.executeQuery(query);
			
			ArrayList<Song> songs = new ArrayList<Song>();
			while(results.next()) {
				songs.add(
					new Song(
						results.getInt("songs.ID"),
						results.getString("songs.SongName"),
						results.getString("artists.ArtistName"),
						results.getString("albums.AlbumName"),
						results.getString("songs.Duration"),
						results.getString("songs.TrackNumber"),
						results.getString("songs.SampleRate"),
						results.getString("songs.ContentType"),
						results.getString("genres.GenreName"),
						results.getInt("songs.Plays"),
						results.getInt("songs.Rating")
					)
				);
			}
			
			return songs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public ArrayList<Playlist> getAllPlaylists(String name) {
		try {
			statement = database.createStatement();
			String query;
			
			if(!name.isEmpty()) {
				query =   "SELECT * FROM Playlists "
						 +"WHERE PlaylistName = '" +name +"'";
			} else {
				query =   "SELECT * FROM Playlists";
				
			}
			ResultSet results = statement.executeQuery(query);
			
			ArrayList<Playlist> playlists = new ArrayList<Playlist>();
			while(results.next()) {
				playlists.add(new Playlist(results.getInt("ID"), results.getString("PlaylistName"), results.getInt("Featured"), results.getInt("Rating")));
			}
			
			return playlists;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public boolean renamePlaylist(String playlistName, String newPlaylistName) {
		try {
			statement = database.createStatement();
			String query;
			
			query =   "UPDATE Playlists "
					 +"SET PlaylistName = '" +newPlaylistName +"' "
					 +"WHERE PlaylistName = '" +playlistName +"'";
			statement.executeUpdate(query);
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean deletePlaylist(String playlistName) {
		try {
			statement = database.createStatement();
			String query;
			
			query =   "DELETE FROM Playlists "
					 +"WHERE PlaylistName = '" +playlistName +"'";
			statement.executeUpdate(query);
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public ArrayList<Playlist> getFeaturedPlaylists() {
		try {
			statement = database.createStatement();
			String query =   "SELECT * FROM Playlists "
							+"WHERE Featured = 1";
			ResultSet results = statement.executeQuery(query);
			
			ArrayList<Playlist> playlists = new ArrayList<Playlist>();
			while(results.next()) {
				playlists.add(new Playlist(results.getInt("ID"), results.getString("PlaylistName"), results.getInt("Featured"), results.getInt("Rating")));
			}
			
			return playlists;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public boolean rateArtist(String artistName, int rating) {
		try {
			statement = database.createStatement();
			String query =   "SELECT * FROM Artists "
							+"WHERE ArtistName = '" +artistName +"'";
			ResultSet results = statement.executeQuery(query);
			
			if(!results.first()) {
				return false;
			}
			
			int artistID = results.getInt("ID");
			query =  "UPDATE Artists "
					+"SET Rating = " +rating +" "
					+"WHERE ID = " +artistID;
			statement.executeUpdate(query);
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean rateAlbum(String albumName, int rating) {
		try {
			statement = database.createStatement();
			String query =   "SELECT * FROM Albums "
							+"WHERE AlbumName = '" +albumName +"'";
			ResultSet results = statement.executeQuery(query);
			
			if(!results.first()) {
				return false;
			}
			
			int albumID = results.getInt("ID");
			query =  "UPDATE Albums "
					+"SET Rating = " +rating +" "
					+"WHERE ID = " +albumID;
			statement.executeUpdate(query);
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean rateSong(String songName, int rating) {
		try {
			statement = database.createStatement();
			String query =   "SELECT * FROM Songs "
							+"WHERE SongName = '" +songName +"'";
			ResultSet results = statement.executeQuery(query);
			
			if(!results.first()) {
				return false;
			}
			
			int songID = results.getInt("ID");
			query =  "UPDATE Songs "
					+"SET Rating = " +rating +" "
					+"WHERE ID = " +songID;
			statement.executeUpdate(query);
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean ratePlaylist(String playlistName, int rating) {
		try {
			statement = database.createStatement();
			String query =   "SELECT * FROM Playlists "
							+"WHERE PlaylistName = '" +playlistName +"'";
			ResultSet results = statement.executeQuery(query);
			
			if(!results.first()) {
				return false;
			}
			
			int playlistID = results.getInt("ID");
			query =  "UPDATE Playlists "
					+"SET Rating = " +rating +" "
					+"WHERE ID = " +playlistID;
			statement.executeUpdate(query);
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
}
