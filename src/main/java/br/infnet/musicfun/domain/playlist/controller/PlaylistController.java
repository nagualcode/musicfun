package br.infnet.musicfun.domain.playlist.controller;

import br.infnet.musicfun.domain.playlist.dto.MusicDTO;
import br.infnet.musicfun.domain.playlist.dto.PlaylistDTO;
import br.infnet.musicfun.domain.playlist.model.Music;
import br.infnet.musicfun.domain.playlist.model.Playlist;
import br.infnet.musicfun.domain.playlist.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/playlists")
public class PlaylistController {

    @Autowired
    private PlaylistService playlistService;

    @GetMapping
    public List<PlaylistDTO> getAllPlaylists() {
        return playlistService.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public PlaylistDTO getPlaylistById(@PathVariable Long id) {
        return playlistService.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Playlist not found"));
    }

    @PostMapping
    public PlaylistDTO createPlaylist(@RequestBody PlaylistDTO playlistDTO) {
        Playlist playlist = convertToEntity(playlistDTO);
        Playlist savedPlaylist = playlistService.save(playlist);
        return convertToDTO(savedPlaylist);
    }

    @PutMapping("/{id}")
    public PlaylistDTO updatePlaylist(@PathVariable Long id, @RequestBody PlaylistDTO playlistDTO) {
        Playlist playlist = convertToEntity(playlistDTO);
        playlist.setId(id);
        Playlist updatedPlaylist = playlistService.update(playlist);
        return convertToDTO(updatedPlaylist);
    }

    @DeleteMapping("/{id}")
    public void deletePlaylist(@PathVariable Long id) {
        playlistService.delete(id);
    }

    private PlaylistDTO convertToDTO(Playlist playlist) {
        return new PlaylistDTO(playlist.getId(), playlist.getName(), playlist.getMusics().stream()
                .map(music -> new MusicDTO(music.getId(), music.getTitle(), music.getArtist(), music.getDuration()))
                .collect(Collectors.toList()));
    }

    private Playlist convertToEntity(PlaylistDTO playlistDTO) {
        Playlist playlist = new Playlist();
        playlist.setId(playlistDTO.getId());
        playlist.setName(playlistDTO.getName());
        playlist.setMusics(playlistDTO.getMusics().stream()
                .map(dto -> new Music(dto.getId(), dto.getTitle(), dto.getArtist(), dto.getDuration()))
                .collect(Collectors.toList()));
        return playlist;
    }
}
