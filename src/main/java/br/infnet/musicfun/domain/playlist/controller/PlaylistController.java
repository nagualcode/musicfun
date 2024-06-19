package br.infnet.musicfun.domain.playlist.controller;

import br.infnet.musicfun.domain.playlist.dto.PlaylistDTO;
import br.infnet.musicfun.domain.playlist.model.Playlist;
import br.infnet.musicfun.domain.playlist.service.PlaylistService;
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
                .musics(playlist.getMusics().stream().map(this::convertMusicToDTO).collect(Collectors.toList()))
                .build();
    }

    private Playlist convertToEntity(PlaylistDTO playlistDTO) {
        Playlist playlist = new Playlist();
        playlist.setName(playlistDTO.getName());
        playlist.setMusics(playlistDTO.getMusics().stream().map(this::convertMusicToEntity).collect(Collectors.toList()));
        return playlist;
    }

    private MusicDTO convertMusicToDTO(Music music) {
        return MusicDTO.builder()
                .id(music.getId())
                .title(music.getTitle())
                .artist(music.getArtist())
                .duration(music.getDuration())
                .build();
    }

    private Music convertMusicToEntity(MusicDTO musicDTO) {
        Music music = new Music();
        music.setTitle(musicDTO.getTitle());
        music.setArtist(musicDTO.getArtist());
        music.setDuration(musicDTO.getDuration());
        return music;
    }
}
