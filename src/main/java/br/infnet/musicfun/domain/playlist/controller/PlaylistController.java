package br.infnet.musicfun.domain.playlist.controller;

import br.infnet.musicfun.domain.playlist.dto.MusicDTO;
import br.infnet.musicfun.domain.playlist.dto.PlaylistDTO;
import br.infnet.musicfun.domain.playlist.model.Music;
import br.infnet.musicfun.domain.playlist.model.Playlist;
import br.infnet.musicfun.domain.playlist.service.PlaylistService;
import br.infnet.musicfun.domain.user.dto.UserDTO;
import br.infnet.musicfun.domain.user.model.AppUser;
import br.infnet.musicfun.domain.user.service.UserService;
import br.infnet.musicfun.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
        return playlistService.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public PlaylistDTO getPlaylistById(@PathVariable Long id) {
        return playlistService.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Playlist not found with id " + id));
    }

    @GetMapping("/user")
    public List<PlaylistDTO> getPlaylistsByUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        return playlistService.findByUserUsername(username).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/user/{username}")
    public List<PlaylistDTO> getPlaylistsByUsername(@PathVariable String username) {
        return playlistService.findByUserUsername(username).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/search")
    public List<PlaylistDTO> getPlaylistsByName(@RequestParam String name) {
        return playlistService.findByName(name).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @PostMapping
    public PlaylistDTO createOrUpdatePlaylist(@RequestBody PlaylistDTO playlistDTO) {
        Playlist playlist = convertToEntity(playlistDTO);
        Playlist savedPlaylist = playlistService.saveOrUpdate(playlist);
        return convertToDTO(savedPlaylist);
    }

    @PutMapping("/{id}")
    public PlaylistDTO updatePlaylist(@PathVariable Long id, @RequestBody PlaylistDTO playlistDTO) {
        Playlist playlist = convertToEntity(playlistDTO);
        playlist.setId(id);
        Playlist updatedPlaylist = playlistService.saveOrUpdate(playlist);
        return convertToDTO(updatedPlaylist);
    }

    @DeleteMapping("/{id}")
    public void deletePlaylist(@PathVariable Long id) {
        playlistService.delete(id);
    }

    @PostMapping("/user/favorites")
    public PlaylistDTO addMusicToUserFavorites(@RequestBody List<MusicDTO> musicDTOs) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();

        AppUser user = userService.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with username " + username));

        Playlist userFavorites = playlistService.findByUserUsername(username).stream()
                .filter(playlist -> "UserFavorites".equals(playlist.getName()))
                .findFirst()
                .orElse(new Playlist(null, "UserFavorites", new ArrayList<>(), user));

        List<Music> musics = musicDTOs.stream()
                .map(musicDTO -> new Music(musicDTO.getId(), musicDTO.getTitle(), musicDTO.getArtist(), musicDTO.getDuration(), musicDTO.getAlbum(), musicDTO.getGenre()))
                .collect(Collectors.toList());

        userFavorites.getMusics().addAll(musics);

        Playlist updatedPlaylist = playlistService.saveOrUpdate(userFavorites);
        return convertToDTO(updatedPlaylist);
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

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        AppUser user = userService.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        playlist.setUser(user);

        return playlist;
    }
}
