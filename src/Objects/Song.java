package Objects;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import Interfaces.SongInterface;

public class Song implements SongInterface {
	private int ID;
	private String name;
	private String artist;
	private String album;
	private String duration;
	private String trackNumber;
	private String sampleRate;
	private String contentType;
	private String genre;
	private int plays;
	private int rating;
	
	public Song(int ID, String songName, String artist, String album, String duration, String trackNumber, String sampleRate, String contentType, String genre, int plays, int rating) {
		this.ID = ID;
		this.name = songName;
		this.artist = artist;
		this.album = album;
		this.duration = duration;
		this.trackNumber = trackNumber;
		this.sampleRate = sampleRate;
		this.contentType = contentType;
		this.genre = genre;
		this.plays = plays;
		this.rating = rating;
	}

	@Override
	public int getID() {
		return ID;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getArtist() {
		return artist;
	}

	@Override
	public String getAlbum() {
		return album;
	}

	@Override
	public String getDuration() {
		long duration = (long) (Long.parseLong(this.duration.substring(0, this.duration.indexOf("."))));
		Date durationDate = new Date(duration);
		DateFormat formatter = new SimpleDateFormat("mm:ss:SSS");
		String durationFormatted = formatter.format(durationDate);
		return durationFormatted;
	}

	@Override
	public String getTrackNumber() {
		return trackNumber;
	}

	@Override
	public String getSampleRate() {
		return sampleRate;
	}

	@Override
	public String getContentType() {
		return contentType;
	}

	@Override
	public String getGenre() {
		return genre;
	}
	
	@Override
	public int getPlays() {
		return plays;
	}

	@Override
	public int getRating() {
		return rating;
	}
}
