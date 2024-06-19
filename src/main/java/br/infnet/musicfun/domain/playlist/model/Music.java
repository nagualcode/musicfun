package br.infnet.musicfun.domain.playlist.model;

import br.infnet.musicfun.domain.core.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Music extends BaseEntity { // Extending BaseEntity
    private static final long serialVersionUID = 1L;

    private String title;
    private String artist;
    private int duration;

    // Using Jakarta Persistence annotations and Lombok's @Data for automatic getter/setter generation
}
