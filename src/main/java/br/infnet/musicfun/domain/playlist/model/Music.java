package br.infnet.musicfun.domain.playlist.model;

import br.infnet.musicfun.domain.core.model.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.Duration;

@Entity
@Table(name = "musics")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Music extends BaseEntity {

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String artist;

    @Column(nullable = false)
    private Duration duration;
}
