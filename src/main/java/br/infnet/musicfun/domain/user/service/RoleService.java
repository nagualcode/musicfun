package br.infnet.musicfun.domain.user.service;

import br.infnet.musicfun.domain.user.model.Role;
import br.infnet.musicfun.domain.user.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    public Optional<Role> findById(Long id) {
        return roleRepository.findById(id);
    }

    public Role save(Role role) {
        return roleRepository.save(role);
    }

    public Role update(Role role) {
        return roleRepository.save(role); // save method works as update if the ID exists
    }

    public void delete(Long id) {
        roleRepository.deleteById(id);
    }
}
