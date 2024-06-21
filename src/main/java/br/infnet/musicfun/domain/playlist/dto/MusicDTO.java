package br.infnet.musicfun.domain.playlist.dto;

public class MusicDTO {
    private Long id;
    private String title;
    private String artist;
    private int duration; // duration in seconds
    private String album; // Add album field
    private String genre; // Add genre field

    public MusicDTO() {
    }

    public MusicDTO(Long id, String title, String artist, int duration, String album, String genre) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.duration = duration;
        this.album = album;
        this.genre = genre;
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "MusicDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", artist='" + artist + '\'' +
                ", duration=" + duration +
                ", album='" + album + '\'' +
                ", genre='" + genre + '\'' +
                '}';
    }
}
