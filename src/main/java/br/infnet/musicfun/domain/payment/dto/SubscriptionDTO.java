package br.infnet.musicfun.domain.payment.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class SubscriptionDTO {
    private Long id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String status;
}
