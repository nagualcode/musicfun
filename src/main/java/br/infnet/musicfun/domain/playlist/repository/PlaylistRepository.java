package br.infnet.musicfun.domain.playlist.repository;

import br.infnet.musicfun.domain.playlist.model.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
}
