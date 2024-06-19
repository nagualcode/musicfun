package br.infnet.musicfun.domain.playlist.service;

import br.infnet.musicfun.domain.playlist.model.Playlist;
import br.infnet.musicfun.domain.playlist.repository.PlaylistRepository;
import br.infnet.musicfun.domain.core.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaylistService extends BaseService<Playlist> {

    @Autowired
    private PlaylistRepository playlistRepository;

    public List<Playlist> findByUserId(Long userId) {
        return playlistRepository.findByUserId(userId);
    }
}
