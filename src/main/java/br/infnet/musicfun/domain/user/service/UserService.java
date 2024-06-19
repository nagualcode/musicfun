package br.infnet.musicfun.domain.user.service;

import br.infnet.musicfun.domain.user.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(user -> User.builder()
                    .username(user.getUsername())
                    .password(user.getPassword())
                    .authorities("USER") // Adapt according to your actual authority management
                    .build())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
