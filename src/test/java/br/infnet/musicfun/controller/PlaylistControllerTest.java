package br.infnet.musicfun.controller;

import br.infnet.musicfun.domain.playlist.dto.PlaylistDTO;
import br.infnet.musicfun.domain.playlist.model.Playlist;
import br.infnet.musicfun.domain.playlist.model.Music;
import br.infnet.musicfun.domain.playlist.dto.MusicDTO;
import br.infnet.musicfun.domain.playlist.service.PlaylistService;
import br.infnet.musicfun.domain.user.model.AppUser;
import br.infnet.musicfun.domain.user.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;

@SpringBootTest
@AutoConfigureMockMvc
public class PlaylistControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlaylistService playlistService;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    private Playlist playlist;
    private PlaylistDTO playlistDTO;
    private AppUser user;

    @BeforeEach
    public void setup() {
        Music music1 = new Music(1L, "Song 1", "Artist 1", 300, "Album 1", "Genre 1");
        Music music2 = new Music(2L, "Song 2", "Artist 2", 250, "Album 2", "Genre 2");

        List<MusicDTO> musicDTOs = Arrays.asList(
                new MusicDTO(music1.getId(), music1.getTitle(), music1.getArtist(), music1.getDuration(), music1.getAlbum(), music1.getGenre()),
                new MusicDTO(music2.getId(), music2.getTitle(), music2.getArtist(), music2.getDuration(), music2.getAlbum(), music2.getGenre())
        );

        user = new AppUser();
        user.setId(1L);
        user.setUsername("testuser");
        user.setEmail("testuser@example.com");

        playlist = new Playlist(1L, "Test Playlist", Arrays.asList(music1, music2), user);
        playlistDTO = new PlaylistDTO(1L, "Test Playlist", musicDTOs, new br.infnet.musicfun.domain.user.dto.UserDTO(user.getId(), user.getUsername(), user.getEmail()));
    }

    @Test
    @WithMockUser(username = "testuser", roles = {"USER"})
    public void testGetPlaylistsByUser() throws Exception {
        Mockito.when(playlistService.findByUserUsername(Mockito.anyString())).thenReturn(Arrays.asList(playlist));

        mockMvc.perform(get("/playlists/user")
                .with(user("testuser").password("password").roles("USER")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(playlist.getId()))
                .andExpect(jsonPath("$[0].name").value(playlist.getName()))
                .andExpect(jsonPath("$[0].musics[0].title").value("Song 1"));
    }

    @Test
    @WithMockUser
    public void testGetAllPlaylists() throws Exception {
        Mockito.when(playlistService.findAll()).thenReturn(Arrays.asList(playlist));

        mockMvc.perform(get("/playlists")
                .with(user("admin").password("password").roles("USER", "ADMIN")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(playlist.getId()))
                .andExpect(jsonPath("$[0].name").value(playlist.getName()))
                .andExpect(jsonPath("$[0].musics[0].title").value("Song 1"));
    }

    @Test
    @WithMockUser
    public void testGetPlaylistById() throws Exception {
        Mockito.when(playlistService.findById(1L)).thenReturn(Optional.of(playlist));

        mockMvc.perform(get("/playlists/1")
                .with(user("admin").password("password").roles("USER", "ADMIN")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(playlist.getId()))
                .andExpect(jsonPath("$.name").value(playlist.getName()))
                .andExpect(jsonPath("$.musics[0].title").value("Song 1"));
    }

    @Test
    @WithMockUser
    public void testCreatePlaylist() throws Exception {
        Mockito.when(userService.findByUsername(Mockito.anyString())).thenReturn(Optional.of(user));
        Mockito.when(playlistService.saveOrUpdate(Mockito.any(Playlist.class))).thenReturn(playlist);

        mockMvc.perform(post("/playlists")
                        .with(csrf())
                        .with(user("admin").password("password").roles("USER", "ADMIN"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(playlistDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(playlist.getId()))
                .andExpect(jsonPath("$.name").value(playlist.getName()))
                .andExpect(jsonPath("$.musics[0].title").value("Song 1"));
    }

    @Test
    @WithMockUser
    public void testUpdatePlaylist() throws Exception {
        Mockito.when(userService.findByUsername(Mockito.anyString())).thenReturn(Optional.of(user));
        Mockito.when(playlistService.findById(1L)).thenReturn(Optional.of(playlist)); // Ensure the playlist is found
        Mockito.when(playlistService.saveOrUpdate(Mockito.any(Playlist.class))).thenReturn(playlist);

        mockMvc.perform(put("/playlists/1")
                        .with(csrf())
                        .with(user("admin").password("password").roles("USER", "ADMIN"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(playlistDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(playlist.getId()))
                .andExpect(jsonPath("$.name").value(playlist.getName()))
                .andExpect(jsonPath("$.musics[0].title").value("Song 1"));
    }

    @Test
    @WithMockUser
    public void testDeletePlaylist() throws Exception {
        Mockito.doNothing().when(playlistService).delete(1L);

        mockMvc.perform(delete("/playlists/1")
                        .with(csrf())
                        .with(user("admin").password("password").roles("USER", "ADMIN")))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "testuser", roles = {"USER"})
    public void testAddMusicToUserFavorites() throws Exception {
        MusicDTO musicDTO = new MusicDTO(3L, "Song 3", "Artist 3", 320, "Album 3", "Genre 3");
        List<MusicDTO> musicDTOs = Arrays.asList(musicDTO);

        Playlist userFavorites = new Playlist(null, "UserFavorites", new ArrayList<>(), user);
        userFavorites.getMusics().add(new Music(musicDTO.getId(), musicDTO.getTitle(), musicDTO.getArtist(), musicDTO.getDuration(), musicDTO.getAlbum(), musicDTO.getGenre()));

        Mockito.when(userService.findByUsername(Mockito.anyString())).thenReturn(Optional.of(user));
        Mockito.when(playlistService.findByUserUsername(Mockito.anyString())).thenReturn(Arrays.asList(userFavorites));
        Mockito.when(playlistService.saveOrUpdate(Mockito.any(Playlist.class))).thenReturn(userFavorites);

        mockMvc.perform(post("/playlists/user/favorites")
                        .with(csrf())
                        .with(user("testuser").password("password").roles("USER"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(musicDTOs)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("UserFavorites"))
                .andExpect(jsonPath("$.musics[0].title").value("Song 3"));
    }
}
