package pl.sda.mp3analysis.sevice;

import com.mpatric.mp3agic.*;
import pl.sda.mp3analysis.dao.SongDAO;
import pl.sda.mp3analysis.entity.Song;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;

public class SongService {
    private SongDAO songDAO;

    public SongService() {
        songDAO = new SongDAO();
    }

    public void save(String fileLocation) {

        Path dir = Paths.get(fileLocation);

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir, "*.{mp3}")) {
            for (Path entry : stream) {

                String title = entry.getFileName().toString();
                File file = new File(dir + "\\" + title);
                DecimalFormat df2 = new DecimalFormat("#.##");

                songDAO.openSession();

                Mp3File mp3file = new Mp3File(dir + "\\" + title);
                if (mp3file.hasId3v1Tag()) {
                    ID3v1 id3v1Tag = mp3file.getId3v1Tag();
                    String size = df2.format(file.length() / 1024.0 / 1024.0) + "mb";
                    Song song = new Song(id3v1Tag.getArtist(), id3v1Tag.getYear(), id3v1Tag.getAlbum(), id3v1Tag.getTitle(), size);
                    songDAO.save(song);
                }
                if (mp3file.hasId3v2Tag()) {
                    ID3v2 id3v2Tag = mp3file.getId3v2Tag();
                    String size = df2.format(file.length() / 1024.0 / 1024.0) + "mb";
                    Song song = new Song(id3v2Tag.getArtist(), id3v2Tag.getYear(), id3v2Tag.getAlbum(), id3v2Tag.getTitle(), size);
                    songDAO.save(song);
                } else {
                    System.out.println("Can't definite file parameters");
                }
                songDAO.closeCurrentSession();
            }
        } catch (IOException | UnsupportedTagException | InvalidDataException x) {
            System.err.println(x);
        }
    }

    public void findAll() {
        songDAO.openSession();
        System.out.println(songDAO.findAll());
        songDAO.closeCurrentSession();
    }

    public void findByTitle(String title) {
        songDAO.openSession();
        if (songDAO.findByTitle(title).isEmpty()) {
            System.out.println("Title not found");
        } else {
            System.out.println(songDAO.findByTitle(title));
        }
        songDAO.closeCurrentSession();
    }

    public void findByArtist(String artist) {
        songDAO.openSession();
        if (songDAO.findByArtist(artist).isEmpty()) {
            System.out.println("Artist not found");
        } else {
            System.out.println(songDAO.findByArtist(artist));
        }
        songDAO.closeCurrentSession();
    }
}
