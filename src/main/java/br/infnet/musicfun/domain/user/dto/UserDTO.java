package br.infnet.musicfun.domain.user.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class UserDTO {
    private Long id;
    private String username;
    private String password;
    private Set<String> roles;
}
