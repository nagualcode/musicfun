package br.infnet.musicfun.domain.user.controller;

import br.infnet.musicfun.domain.user.dto.UserDTO;
import br.infnet.musicfun.domain.user.model.AppUser;
import br.infnet.musicfun.domain.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
        return userService.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable Long id) {
        return userService.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @PostMapping
    public UserDTO createUser(@RequestBody UserDTO userDTO) {
        AppUser user = convertToEntity(userDTO);
        AppUser savedUser = userService.save(user);
        return convertToDTO(savedUser);
    }

    @PutMapping("/{id}")
    public UserDTO updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        AppUser user = convertToEntity(userDTO);
        user.setId(id);
        AppUser updatedUser = userService.update(user);
        return convertToDTO(updatedUser);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.delete(id);
    }

    private UserDTO convertToDTO(AppUser user) {
        return new UserDTO(user.getId(), user.getUsername(), user.getEmail());
    }

    private AppUser convertToEntity(UserDTO userDTO) {
        AppUser user = new AppUser();
        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        return user;
    }
}
