package br.infnet.musicfun.domain.playlist.controller;

import br.infnet.musicfun.domain.playlist.dto.MusicDTO;
import br.infnet.musicfun.domain.playlist.model.Music;
import br.infnet.musicfun.domain.playlist.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/music")
public class MusicController {

    @Autowired
    private MusicService musicService;

    @GetMapping
    public List<MusicDTO> getAllMusic() {
        return musicService.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public MusicDTO getMusicById(@PathVariable Long id) {
        return musicService.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Music not found"));
    }

    @PostMapping
    public MusicDTO createMusic(@RequestBody MusicDTO musicDTO) {
        Music music = convertToEntity(musicDTO);
        Music savedMusic = musicService.save(music);
        return convertToDTO(savedMusic);
    }

    @PutMapping("/{id}")
    public MusicDTO updateMusic(@PathVariable Long id, @RequestBody MusicDTO musicDTO) {
        Music music = convertToEntity(musicDTO);
        music.setId(id);
        Music updatedMusic = musicService.update(music);
        return convertToDTO(updatedMusic);
    }

    @DeleteMapping("/{id}")
    public void deleteMusic(@PathVariable Long id) {
        musicService.delete(id);
    }

    private MusicDTO convertToDTO(Music music) {
        return new MusicDTO(music.getId(), music.getTitle(), music.getArtist(), music.getDuration(), music.getAlbum(), music.getGenre());
    }

    private Music convertToEntity(MusicDTO musicDTO) {
        return new Music(musicDTO.getId(), musicDTO.getTitle(), musicDTO.getArtist(), musicDTO.getDuration(), musicDTO.getAlbum(), musicDTO.getGenre());
    }
}
