package br.infnet.musicfun.domain.playlist.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class PlaylistDTO {
    private Long id;
    private String name;
    private Set<MusicDTO> musics;
}
