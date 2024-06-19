package br.infnet.musicfun.domain.user.controller;

import br.infnet.musicfun.domain.user.dto.RoleDTO;
import br.infnet.musicfun.domain.user.model.Role;
import br.infnet.musicfun.domain.user.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping
    public List<RoleDTO> getAllRoles() {
        return roleService.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public RoleDTO getRoleById(@PathVariable Long id) {
        return roleService.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Role not found"));
    }

    @PostMapping
    public RoleDTO createRole(@RequestBody RoleDTO roleDTO) {
        Role role = convertToEntity(roleDTO);
        Role savedRole = roleService.save(role);
        return convertToDTO(savedRole);
    }

    @PutMapping("/{id}")
    public RoleDTO updateRole(@PathVariable Long id, @RequestBody RoleDTO roleDTO) {
        Role role = convertToEntity(roleDTO);
        role.setId(id);
        Role updatedRole = roleService.update(role);
        return convertToDTO(updatedRole);
    }

    @DeleteMapping("/{id}")
    public void deleteRole(@PathVariable Long id) {
        roleService.delete(id);
    }

    private RoleDTO convertToDTO(Role role) {
        return new RoleDTO(role.getId(), role.getName());
    }

    private Role convertToEntity(RoleDTO roleDTO) {
        Role role = new Role();
        role.setId(roleDTO.getId());
        role.setName(roleDTO.getName());
        return role;
    }
}
