package br.infnet.musicfun.domain.user.service;

import br.infnet.musicfun.domain.user.model.User;
import br.infnet.musicfun.domain.user.repository.UserRepository;
import br.infnet.musicfun.domain.core.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService extends BaseService<User> {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> findByUsername(String username) {
        return Optional.ofNullable(userRepository.findByUsername(username));
    }
}
