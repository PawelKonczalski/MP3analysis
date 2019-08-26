package pl.sda.mp3analysis.entity;

import javax.persistence.*;

@Entity
@Table(name = "songs")
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "artist")
    private String artist;

    @Column(name = "year")
    private String year;

    @Column(name = "album")
    private String album;

    @Column(name = "title")
    private String title;

    @Column(name = "size")
    private String size;

    public Song() {
    }

    public Song(String artist, String year, String album, String title, String size) {
        this.artist = artist;
        this.year = year;
        this.album = album;
        this.title = title;
        this.size = size;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", artist='" + artist + '\'' +
                ", year='" + year + '\'' +
                ", album='" + album + '\'' +
                ", title='" + title + '\'' +
                ", size='" + size + '\'' +
                '}';
    }
}
