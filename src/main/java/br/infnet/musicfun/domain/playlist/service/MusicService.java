package br.infnet.musicfun.domain.playlist.service;

import br.infnet.musicfun.domain.playlist.model.Music;
import br.infnet.musicfun.domain.playlist.repository.MusicRepository;
import br.infnet.musicfun.domain.core.service.BaseService;
import org.springframework.stereotype.Service;

@Service
public class MusicService extends BaseService<Music> {

    private final MusicRepository musicRepository;

    public MusicService(MusicRepository musicRepository) {
        this.musicRepository = musicRepository;
    }
}
