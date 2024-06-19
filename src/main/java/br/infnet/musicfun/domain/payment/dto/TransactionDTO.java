package br.infnet.musicfun.domain.payment.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class TransactionDTO {
    private Long id;
    private BigDecimal amount;
    private LocalDateTime transactionDate;
    private String status;
    private String merchant;
}
