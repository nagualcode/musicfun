package br.infnet.musicfun.domain.playlist.dto;

import java.util.List;

public class PlaylistDTO {
    private Long id;
    private String name;
    private List<MusicDTO> musics;

    public PlaylistDTO() {
    }

    public PlaylistDTO(Long id, String name, List<MusicDTO> musics) {
        this.id = id;
        this.name = name;
        this.musics = musics;
    }

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

    public List<MusicDTO> getMusics() {
        return musics;
    }

    public void setMusics(List<MusicDTO> musics) {
        this.musics = musics;
    }

    @Override
    public String toString() {
        return "PlaylistDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", musics=" + musics +
                '}';
    }
}
