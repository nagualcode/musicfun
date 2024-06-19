package br.infnet.musicfun.domain.user.model;

import br.infnet.musicfun.domain.core.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import java.io.Serializable;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User extends BaseEntity implements Serializable { // Extending BaseEntity
    private static final long serialVersionUID = 1L;

    private String username;
    private String password;
    private Set<Role> roles;

    // Using Jakarta Persistence annotations and Lombok's @Data for automatic getter/setter generation
}
