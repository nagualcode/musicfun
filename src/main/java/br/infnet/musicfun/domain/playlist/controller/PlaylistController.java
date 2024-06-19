package br.infnet.musicfun.domain.playlist.controller;

import br.infnet.musicfun.domain.playlist.dto.PlaylistDTO;
import br.infnet.musicfun.domain.playlist.model.Playlist;
import br.infnet.musicfun.domain.playlist.service.PlaylistService;
import br.infnet.musicfun.domain.user.model.User;
import br.infnet.musicfun.domain.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/playlists")
public class PlaylistController {

    @Autowired
    private PlaylistService playlistService;

    @Autowired
    private UserService userService;

    @GetMapping
    public List<PlaylistDTO> getAllPlaylists() {
        return playlistService.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlaylistDTO> getPlaylistById(@PathVariable Long id) {
        return playlistService.findById(id).map(playlist -> ResponseEntity.ok(convertToDTO(playlist)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PlaylistDTO> createPlaylist(@RequestBody PlaylistDTO playlistDTO) {
        Playlist playlist = convertToEntity(playlistDTO);
        // Assuming you have user management logic to get the current user
        User user = userService.findById(1L).orElseThrow(() -> new RuntimeException("User not found")); 
        playlist.setUser(user);
        Playlist savedPlaylist = playlistService.save(playlist);
        return ResponseEntity.ok(convertToDTO(savedPlaylist));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlaylist(@PathVariable Long id) {
        playlistService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private PlaylistDTO convertToDTO(Playlist playlist) {
        return PlaylistDTO.builder()
                .id(playlist.getId())
                .name(playlist.getName())
                .musics(playlist.getMusics().stream().map(music -> {
                    return MusicDTO.builder()
                            .id(music.getId())
                            .title(music.getTitle())
                            .artist(music.getArtist())
                            .duration(music.getDuration())
                            .build();
                }).collect(Collectors.toSet()))
                .build();
    }

    private Playlist convertToEntity(PlaylistDTO playlistDTO) {
        return Playlist.builder()
                .name(playlistDTO.getName())
                // Music conversion logic would be added here
                .build();
    }
}
