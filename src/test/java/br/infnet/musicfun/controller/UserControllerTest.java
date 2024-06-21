package br.infnet.musicfun.controller;


import br.infnet.musicfun.domain.user.dto.UserDTO;
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

import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;

// import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
// @ActiveProfiles("test")
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    private AppUser user;
    private UserDTO userDTO;

    @BeforeEach
    public void setup() {
        user = new AppUser();
        user.setId(1L);
        user.setUsername("testuser");
        user.setEmail("testuser@example.com");

        userDTO = new UserDTO(1L, "testuser", "testuser@example.com");
    }

    @Test
    public void testGetAllUsers() throws Exception {
        Mockito.when(userService.findAll()).thenReturn(Arrays.asList(user));

        mockMvc.perform(get("/users")
                .with(user("admin").password("password").roles("USER", "ADMIN")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(user.getId()))
                .andExpect(jsonPath("$[0].username").value(user.getUsername()))
                .andExpect(jsonPath("$[0].email").value(user.getEmail()));
    }

    @Test
    public void testGetUserById() throws Exception {
        Mockito.when(userService.findById(1L)).thenReturn(Optional.of(user));

        mockMvc.perform(get("/users/1")
                .with(user("admin").password("password").roles("USER", "ADMIN")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(user.getId()))
                .andExpect(jsonPath("$.username").value(user.getUsername()))
                .andExpect(jsonPath("$.email").value(user.getEmail()));
    }

    @Test
    public void testCreateUser() throws Exception {
        Mockito.when(userService.save(Mockito.any(AppUser.class))).thenReturn(user);

        mockMvc.perform(post("/users")
                        .with(csrf())
                        .with(user("admin").password("password").roles("USER", "ADMIN"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(user.getId()))
                .andExpect(jsonPath("$.username").value(user.getUsername()))
                .andExpect(jsonPath("$.email").value(user.getEmail()));
    }

    @Test
    public void testUpdateUser() throws Exception {
        Mockito.when(userService.update(Mockito.any(AppUser.class))).thenReturn(user);

        mockMvc.perform(put("/users/1")
                        .with(csrf())
                        .with(user("admin").password("password").roles("USER", "ADMIN"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(user.getId()))
                .andExpect(jsonPath("$.username").value(user.getUsername()))
                .andExpect(jsonPath("$.email").value(user.getEmail()));
    }

    @Test
    public void testDeleteUser() throws Exception {
        Mockito.doNothing().when(userService).delete(1L);

        mockMvc.perform(delete("/users/1")
                        .with(csrf())
                        .with(user("admin").password("password").roles("USER", "ADMIN")))
                .andExpect(status().isOk());
    }
}
