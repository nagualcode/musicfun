package br.infnet.musicfun.domain.user.repository;

import br.infnet.musicfun.domain.user.model.User;
import br.infnet.musicfun.domain.core.repository.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<User> {
    User findByUsername(String username);
}
