package br.infnet.musicfun.domain.playlist.repository;

import br.infnet.musicfun.domain.playlist.model.Music;
import br.infnet.musicfun.domain.core.repository.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicRepository extends BaseRepository<Music> {
}
