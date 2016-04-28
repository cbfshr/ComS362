package coms362;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import Objects.Database;
import Objects.Song;

public class FileParser {

	public static void main(String args[]) {
		final File test = new File("C:/Users/Cal/Music/iTunes/iTunes Media/Music/");
		
		Database d = new Database();
		d.listFilesForFolder(test);
		
		//listFilesForFolder(test);
	}

	public static void listFilesForFolder(final File folder) {
		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				listFilesForFolder(fileEntry);
			} else {
				String fileName = fileEntry.getName();
				if (fileName.contains(".mp3")) {
					getMetadata(fileEntry.getAbsolutePath());
				}
			}
		}
	}

	public static void getMetadata(String filePath) {
		try {
			InputStream input = new FileInputStream(new File(filePath));
			ContentHandler handler = new DefaultHandler();
			Metadata metadata = new Metadata();
			Parser parser = new Mp3Parser();
			ParseContext parseCtx = new ParseContext();
			parser.parse(input, handler, metadata, parseCtx);
			input.close();

			// Retrieve the necessary info from metadata
			System.out.println("----------------------------------------------");
			System.out.println("Title: " + metadata.get("title"));
			System.out.println("Artist: " + metadata.get("xmpDM:artist"));
			System.out.println("Genre: "+metadata.get("xmpDM:genre"));
			System.out.println("Release Date: "+metadata.get("xmpDM:releaseDate"));
			System.out.println("Genre: "+metadata.get("xmpDM:genre"));
			System.out.println("Length: "+metadata.get("xmpDM:duration"));
			System.out.println("Track Number: "+metadata.get("xmpDM:trackNumber"));
			System.out.println("Sample Rate: "+metadata.get("samplerate"));
			System.out.println("Content Type: "+metadata.get("Content-Type"));
			
			

			//INSERT INTO Songs(SongName, ArtistID, AlbumID, Plays) VALUES('Boulevard of Broken Dreams', 1, 1, 123);
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
}
