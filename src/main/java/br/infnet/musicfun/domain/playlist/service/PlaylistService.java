package br.infnet.musicfun.domain.playlist.service;

import br.infnet.musicfun.domain.playlist.model.Playlist;
import br.infnet.musicfun.domain.playlist.repository.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlaylistService {

    @Autowired
    private PlaylistRepository playlistRepository;

    public List<Playlist> findAll() {
        return playlistRepository.findAll();
    }

    public Optional<Playlist> findById(Long id) {
        return playlistRepository.findById(id);
    }

    public List<Playlist> findByUserUsername(String username) {
        return playlistRepository.findByUserUsername(username);
    }

    public Playlist save(Playlist playlist) {
        return playlistRepository.save(playlist);
    }

    public Playlist update(Playlist playlist) {
        return playlistRepository.save(playlist); // save method works as update if the ID exists
    }

    public void delete(Long id) {
        playlistRepository.deleteById(id);
    }
}
