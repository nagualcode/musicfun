package br.infnet.musicfun.domain.user.service;

import br.infnet.musicfun.domain.user.model.Role;
import br.infnet.musicfun.domain.user.repository.RoleRepository;
import br.infnet.musicfun.domain.core.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService extends BaseService<Role> {

    @Autowired
    private RoleRepository roleRepository;

    public Optional<Role> findByName(String name) {
        return Optional.ofNullable(roleRepository.findByName(name));
    }
}
