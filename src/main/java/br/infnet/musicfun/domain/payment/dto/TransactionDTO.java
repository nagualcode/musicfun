package br.infnet.musicfun.domain.payment.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransactionDTO {
    private Long id;
    private double amount;
    private String merchant;
    private String date;
}
