package br.infnet.musicfun.domain.user.model;

import br.infnet.musicfun.domain.core.model.BaseEntity;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Role extends BaseEntity { // Extending BaseEntity
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;

    // Overriding getId method to ensure consistency
    @Override
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
