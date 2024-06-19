package br.infnet.musicfun.domain.payment.model;

import br.infnet.musicfun.domain.core.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Transaction extends BaseEntity { // Extending BaseEntity
    private static final long serialVersionUID = 1L;

    private double amount;
    private String merchant;
    private String status;
    private LocalDate transactionDate;

    // Using Jakarta Persistence annotations and Lombok's @Data for automatic getter/setter generation
}
