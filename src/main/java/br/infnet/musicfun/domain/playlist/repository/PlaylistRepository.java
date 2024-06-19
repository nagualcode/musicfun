package br.infnet.musicfun.domain.playlist.repository;

import br.infnet.musicfun.domain.playlist.model.Playlist;
import br.infnet.musicfun.domain.core.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaylistRepository extends BaseRepository<Playlist> {
    List<Playlist> findByUserId(Long userId);
}
