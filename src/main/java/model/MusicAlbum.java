package model;

public class MusicAlbum {

    private String title;
    private String artist;
    private String genre;
    private int year;


    public MusicAlbum(){};

    public MusicAlbum(String title,String artist, String genre,  int year ) {
        this.genre = genre;
        this.title = title;
        this.year = year;
        this.artist = artist;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public boolean isValid(){
        return title != null && !title.isEmpty()
            && artist != null && !artist.isEmpty()
            && genre != null && !genre.isEmpty()
            && year > 1990;
    }

    @Override
    public String toString() {
        return "MusicAlbum{" +
                "title='" + title + '\'' +
                ", artist='" + artist + '\'' +
                ", genre='" + genre + '\'' +
                ", year=" + year +
                '}';
    }
}
