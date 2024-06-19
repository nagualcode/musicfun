package br.infnet.musicfun.domain.playlist.service;

import br.infnet.musicfun.domain.playlist.model.Music;
import br.infnet.musicfun.domain.playlist.repository.MusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MusicService {

    @Autowired
    private MusicRepository musicRepository;

    public List<Music> findAll() {
        return musicRepository.findAll();
    }

    public Optional<Music> findById(Long id) {
        return musicRepository.findById(id);
    }

    public Music save(Music music) {
        return musicRepository.save(music);
    }

    public Music update(Music music) {
        return musicRepository.save(music); // save method works as update if the ID exists
    }

    public void delete(Long id) {
        musicRepository.deleteById(id);
    }
}
