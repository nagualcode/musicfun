package br.infnet.musicfun.domain.playlist.model;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Music implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String title;
    private String artist;
    private int duration;

    // Getter methods
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public int getDuration() {
        return duration;
    }
}
