package br.infnet.musicfun.domain.playlist.service;

import br.infnet.musicfun.domain.playlist.model.Playlist;
import br.infnet.musicfun.domain.playlist.repository.PlaylistRepository;
import br.infnet.musicfun.domain.user.model.AppUser;
import br.infnet.musicfun.domain.user.service.UserService;
import br.infnet.musicfun.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlaylistService {

    @Autowired
    private PlaylistRepository playlistRepository;

    @Autowired
    private UserService userService;

    public List<Playlist> findAll() {
        return playlistRepository.findAll();
    }

    public Optional<Playlist> findById(Long id) {
        return playlistRepository.findById(id);
    }

    public List<Playlist> findByUserUsername(String username) {
        return playlistRepository.findByUserUsername(username);
    }

    public List<Playlist> findByName(String name) {
        return playlistRepository.findByName(name);
    }

    public Playlist saveOrUpdate(Playlist playlist) {
        if (playlist.getId() != null) {
            Playlist existingPlaylist = playlistRepository.findById(playlist.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Playlist not found with id " + playlist.getId()));

            existingPlaylist.setName(playlist.getName());
            existingPlaylist.setMusics(playlist.getMusics());

            if (playlist.getUser() != null && playlist.getUser().getUsername() != null) {
                AppUser user = userService.findByUsername(playlist.getUser().getUsername())
                        .orElseThrow(() -> new ResourceNotFoundException("User not found with username " + playlist.getUser().getUsername()));
                existingPlaylist.setUser(user);
            }

            return playlistRepository.save(existingPlaylist);
        } else {
            if (playlist.getUser() != null && playlist.getUser().getUsername() != null) {
                AppUser user = userService.findByUsername(playlist.getUser().getUsername())
                        .orElseThrow(() -> new ResourceNotFoundException("User not found with username " + playlist.getUser().getUsername()));
                playlist.setUser(user);
            }
            return playlistRepository.save(playlist);
        }
    }

    public void delete(Long id) {
        playlistRepository.deleteById(id);
    }
}
