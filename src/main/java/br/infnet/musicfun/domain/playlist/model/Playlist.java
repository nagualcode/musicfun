package br.infnet.musicfun.domain.playlist.model;

import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Playlist implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private List<Music> musics;

    // Getter methods
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Music> getMusics() {
        return musics;
    }
}
