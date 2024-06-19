package br.infnet.musicfun.domain.playlist.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PlaylistDTO {
    private Long id;
    private String name;
    private List<MusicDTO> musics;
}
