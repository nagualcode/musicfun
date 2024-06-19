package br.infnet.musicfun.domain.playlist.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlaylistDTO {
    private Long id;
    private String name;
    private List<MusicDTO> musics;
    
    // Getter methods
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<MusicDTO> getMusics() {
        return musics;
    }
}
