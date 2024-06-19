package br.infnet.musicfun.domain.user.repository;

import br.infnet.musicfun.domain.user.model.Role;
import br.infnet.musicfun.domain.core.repository.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends BaseRepository<Role> {
    Role findByName(String name);
}
