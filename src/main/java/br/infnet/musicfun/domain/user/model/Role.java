package br.infnet.musicfun.domain.user.model;

import br.infnet.musicfun.domain.core.model.BaseEntity;
import jakarta.persistence.Entity;

@Entity
public class Role extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private String name;

    public Role() {}

    public Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
