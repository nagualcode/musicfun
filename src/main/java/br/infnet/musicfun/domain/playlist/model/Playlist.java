package br.infnet.musicfun.domain.playlist.model;

import br.infnet.musicfun.domain.core.model.BaseEntity;
import br.infnet.musicfun.domain.user.model.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "playlists")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Playlist extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @ManyToMany
    @JoinTable(
            name = "playlist_music",
            joinColumns = @JoinColumn(name = "playlist_id"),
            inverseJoinColumns = @JoinColumn(name = "music_id"))
    private Set<Music> musics = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
