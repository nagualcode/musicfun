package br.infnet.musicfun.domain.user.model;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Role implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;

    // Getter methods
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
