package br.infnet.musicfun.domain.user.service;

import br.infnet.musicfun.domain.user.model.AppUser;
import br.infnet.musicfun.domain.user.model.AppUserDetails;
import br.infnet.musicfun.domain.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public List<AppUser> findAll() {
        return userRepository.findAll();
    }

    public Optional<AppUser> findById(Long id) {
        return userRepository.findById(id);
    }

    public AppUser save(AppUser user) {
        return userRepository.save(user);
    }

    public AppUser update(AppUser user) {
        return userRepository.save(user); // save method works as update if the ID exists
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new AppUserDetails(appUser);
    }
}
