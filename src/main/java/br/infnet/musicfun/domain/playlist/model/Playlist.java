package br.infnet.musicfun.domain.playlist.model;

import br.infnet.musicfun.domain.core.model.BaseEntity;
import br.infnet.musicfun.domain.playlist.model.Music;
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
public class Playlist extends BaseEntity { // Extending BaseEntity
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private List<Music> musics;

    // Getter methods
    @Override
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
