package br.infnet.musicfun.domain.user.controller;

import br.infnet.musicfun.domain.user.dto.UserDTO;
import br.infnet.musicfun.domain.user.model.User;
import br.infnet.musicfun.domain.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        return userService.findById(id).map(user -> ResponseEntity.ok(convertToDTO(user)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        User user = convertToEntity(userDTO);
        User savedUser = userService.save(user);
        return ResponseEntity.ok(convertToDTO(savedUser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private UserDTO convertToDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .roles(user.getRoles().stream().map(role -> role.getName()).collect(Collectors.toSet()))
                .build();
    }

    private User convertToEntity(UserDTO userDTO) {
        User user = User.builder()
                .username(userDTO.getUsername())
                .password(userDTO.getPassword())
                .build();
        // Role conversion logic would be added here
        return user;
    }
}
