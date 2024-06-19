package br.infnet.musicfun.domain.playlist.repository;

import br.infnet.musicfun.domain.playlist.model.Music;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicRepository extends JpaRepository<Music, Long> {
}
