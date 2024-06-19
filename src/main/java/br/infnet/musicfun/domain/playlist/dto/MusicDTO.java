package br.infnet.musicfun.domain.playlist.dto;

import lombok.Builder;
import lombok.Data;

import java.time.Duration;

@Data
@Builder
public class MusicDTO {
    private Long id;
    private String title;
    private String artist;
    private Duration duration;
}
