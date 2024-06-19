package br.infnet.musicfun.domain.user.repository;

import br.infnet.musicfun.domain.user.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
