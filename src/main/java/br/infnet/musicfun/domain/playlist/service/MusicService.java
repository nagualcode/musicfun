package br.infnet.musicfun.domain.playlist.service;

import br.infnet.musicfun.domain.playlist.model.Music;
import br.infnet.musicfun.domain.playlist.repository.MusicRepository;
import br.infnet.musicfun.domain.core.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MusicService extends BaseService<Music> {

    @Autowired
    private MusicRepository musicRepository;
}
