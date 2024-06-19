package br.infnet.musicfun.domain.playlist.controller;

import br.infnet.musicfun.domain.playlist.dto.MusicDTO;
import br.infnet.musicfun.domain.playlist.model.Music;
import br.infnet.musicfun.domain.playlist.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/musics")
public class MusicController {

    @Autowired
    private MusicService musicService;

    @GetMapping
    public List<MusicDTO> getAllMusics() {
        return musicService.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MusicDTO> getMusicById(@PathVariable Long id) {
        return musicService.findById(id).map(music -> ResponseEntity.ok(convertToDTO(music)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<MusicDTO> createMusic(@RequestBody MusicDTO musicDTO) {
        Music music = convertToEntity(musicDTO);
        Music savedMusic = musicService.save(music);
        return ResponseEntity.ok(convertToDTO(savedMusic));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMusic(@PathVariable Long id) {
        musicService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private MusicDTO convertToDTO(Music music) {
        return MusicDTO.builder()
                .id(music.getId())
                .title(music.getTitle())
                .artist(music.getArtist())
                .duration(music.getDuration())
                .build();
    }

    private Music convertToEntity(MusicDTO musicDTO) {
        return Music.builder()
                .title(musicDTO.getTitle())
                .artist(musicDTO.getArtist())
                .duration(musicDTO.getDuration())
                .build();
    }
}
