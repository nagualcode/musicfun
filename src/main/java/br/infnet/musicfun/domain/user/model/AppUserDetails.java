package br.infnet.musicfun.domain.user.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.stream.Collectors;

public class AppUserDetails implements UserDetails, Serializable {
    private static final long serialVersionUID = 1L;

    private final AppUser appUser;

    public AppUserDetails(AppUser appUser) {
        this.appUser = appUser;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return appUser.getRoles().stream()
                .map(role -> (GrantedAuthority) role::getName)
                .collect(Collectors.toSet());
    }

    @Override
    public String getPassword() {
        return appUser.getPassword();
    }

    @Override
    public String getUsername() {
        return appUser.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return appUser.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return appUser.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return appUser.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return appUser.isEnabled();
    }
}
