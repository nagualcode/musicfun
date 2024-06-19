package br.infnet.musicfun.domain.payment.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SubscriptionDTO {
    private Long id;
    private String planName;
    private double price;
    private String duration;
}
