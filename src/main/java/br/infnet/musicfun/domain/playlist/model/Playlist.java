package br.infnet.musicfun.domain.playlist.model;

import br.infnet.musicfun.domain.user.model.AppUser;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Playlist implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(
            name = "playlist_music",
            joinColumns = @JoinColumn(name = "playlist_id"),
            inverseJoinColumns = @JoinColumn(name = "music_id"))
    private List<Music> musics;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser user;

    public Playlist() {
    }

    public Playlist(Long id, String name, List<Music> musics, AppUser user) {
        this.id = id;
        this.name = name;
        this.musics = musics;
        this.user = user;
    }

    public Playlist(Long id, String name, List<Music> musics) {
        this.id = id;
        this.name = name;
        this.musics = musics;
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Music> getMusics() {
        return musics;
    }

    public void setMusics(List<Music> musics) {
        this.musics = musics;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Playlist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", musics=" + musics +
                ", user=" + user +
                '}';
    }
}
