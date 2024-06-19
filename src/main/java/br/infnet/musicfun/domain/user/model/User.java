package br.infnet.musicfun.domain.user.model;

import br.infnet.musicfun.domain.core.model.BaseEntity;
import java.io.Serializable;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity { // Extending BaseEntity
    private static final long serialVersionUID = 1L;

    private Long id;
    private String username;
    private String password;
    private Set<Role> roles;

    // Overriding getId method to ensure consistency
    @Override
    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Set<Role> getRoles() {
        return roles;
    }
}
