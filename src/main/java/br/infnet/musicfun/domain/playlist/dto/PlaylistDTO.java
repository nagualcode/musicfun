package br.infnet.musicfun.domain.playlist.dto;

import br.infnet.musicfun.domain.user.dto.UserDTO;
import java.util.List;

public class PlaylistDTO {
    private Long id;
    private String name;
    private List<MusicDTO> musics;
    private UserDTO user;

    public PlaylistDTO() {
    }

    public PlaylistDTO(Long id, String name, List<MusicDTO> musics) {
        this.id = id;
        this.name = name;
        this.musics = musics;
    }

    public PlaylistDTO(Long id, String name, List<MusicDTO> musics, UserDTO user) {
        this.id = id;
        this.name = name;
        this.musics = musics;
        this.user = user;
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

    public List<MusicDTO> getMusics() {
        return musics;
    }

    public void setMusics(List<MusicDTO> musics) {
        this.musics = musics;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}
