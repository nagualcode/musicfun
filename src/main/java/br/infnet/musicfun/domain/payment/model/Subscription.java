package br.infnet.musicfun.domain.payment.model;

import java.io.Serializable;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Subscription implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;

    // Getter methods
    public Long getId() {
        return id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String getStatus() {
        return status;
    }
}
