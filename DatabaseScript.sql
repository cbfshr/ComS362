/* In order to run this script,
	1. Open MySQL Command Line Client
	2. Type (The path will most-likely be different):
		SOURCE D:\Projects\Eclipse\ComS362\DatabaseScript.sql
	3. Double check that everything worked:
		Several SQL statements will be run at the end of the script to display
		information about the database!
*/

-- Create the Database
CREATE DATABASE MusicLibrary;
USE MusicLibrary;

-- Drop the Tables --
DROP TABLE PlaylistSongs;
DROP TABLE Playlists;
DROP TABLE Songs;
DROP TABLE Albums;
DROP TABLE Artists;
DROP TABLE Genres;

-- ---------------------
-- Create the Tables --
-- ---------------------
-- Create a table to hold all the Genre names
CREATE TABLE Genres (
	ID int NOT NULL AUTO_INCREMENT,
	GenreName varchar(50) NOT NULL,
	Rating int,
	PRIMARY KEY (ID, GenreName)
);

-- Create a table to hold all the Artist names
CREATE TABLE Artists (
	ID int NOT NULL AUTO_INCREMENT,
	ArtistName varchar(50),
	GenreID int,
	Rating int,
	PRIMARY KEY (ID, ArtistName),
	FOREIGN KEY (GenreID) REFERENCES Genres(ID) ON DELETE CASCADE
);

-- Create a table to hold all the Album names
CREATE TABLE Albums (
	ID int NOT NULL AUTO_INCREMENT,
	AlbumName varchar(50) NOT NULL,
	ArtistID int,
	GenreID int,
	Rating int,
	ReleaseDate varchar(30),
	PRIMARY KEY (ID, AlbumName),
	FOREIGN KEY (ArtistID) REFERENCES Artists(ID) ON DELETE CASCADE,
	FOREIGN KEY (GenreID) REFERENCES Genres(ID) ON DELETE CASCADE
);

-- Create Table Songs
CREATE TABLE Songs (
	ID int NOT NULL AUTO_INCREMENT,
	SongName varchar(50) NOT NULL,
	ArtistID int,
	AlbumID int,
	GenreID int,
	Duration varchar(30),
	TrackNumber varchar(30),
	SampleRate varchar(30),
	ContentType varchar(30),
	Plays int,
	Rating int,
	PRIMARY KEY (ID),
	FOREIGN KEY (ArtistID) REFERENCES Artists(ID) ON DELETE CASCADE,
	FOREIGN KEY (AlbumID) REFERENCES Albums(ID) ON DELETE CASCADE,
	FOREIGN KEY (GenreID) REFERENCES Genres(ID) ON DELETE CASCADE
);

-- Create a table to hold all the Playlist names
CREATE TABLE Playlists (
	ID int NOT NULL AUTO_INCREMENT,
	PlaylistName varchar(30),
	Featured int,
	Rating int,
	PRIMARY KEY (ID)
);

-- Create Table PlaylistSongs
CREATE TABLE PlaylistSongs (
	SongID int NOT NULL,
	PlaylistID int NOT NULL,
	PRIMARY KEY (SongID),
	FOREIGN KEY (SongID) REFERENCES Songs(ID) ON DELETE CASCADE,
	FOREIGN KEY (PlaylistID) REFERENCES Playlists(ID) ON DELETE CASCADE
);

-- ----------------------------
-- Show Database Information --
-- ----------------------------
SHOW TABLES;

-- SELECT * FROM Artists;
-- SELECT * FROM Albums;
-- SELECT * FROM Songs;
