package br.infnet.musicfun.domain.playlist.controller;

import br.infnet.musicfun.domain.playlist.dto.MusicDTO;
import br.infnet.musicfun.domain.playlist.dto.PlaylistDTO;
import br.infnet.musicfun.domain.playlist.model.Music;
import br.infnet.musicfun.domain.playlist.model.Playlist;
import br.infnet.musicfun.domain.playlist.service.PlaylistService;
import br.infnet.musicfun.domain.user.dto.UserDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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

    @GetMapping("/user")
    public List<PlaylistDTO> getPlaylistsByUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        return playlistService.findByUserUsername(username).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
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
        UserDTO userDTO = null;
        if (playlist.getUser() != null) {
            userDTO = new UserDTO(playlist.getUser().getId(), playlist.getUser().getUsername(), playlist.getUser().getEmail());
        }
        return new PlaylistDTO(playlist.getId(), playlist.getName(), playlist.getMusics().stream()
                .map(music -> new MusicDTO(music.getId(), music.getTitle(), music.getArtist(), music.getDuration(), music.getAlbum(), music.getGenre()))
                .collect(Collectors.toList()), userDTO);
    }

    private Playlist convertToEntity(PlaylistDTO playlistDTO) {
        Playlist playlist = new Playlist();
        playlist.setId(playlistDTO.getId());
        playlist.setName(playlistDTO.getName());
        playlist.setMusics(playlistDTO.getMusics().stream()
                .map(musicDTO -> new Music(musicDTO.getId(), musicDTO.getTitle(), musicDTO.getArtist(), musicDTO.getDuration(), musicDTO.getAlbum(), musicDTO.getGenre()))
                .collect(Collectors.toList()));
        return playlist;
    }
}
