package br.infnet.musicfun.domain.payment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {
    private Long id;
    private double amount;
    private String merchant;
    private String status;
    private LocalDate transactionDate;

    // Getter methods
    public Long getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public String getMerchant() {
        return merchant;
    }

    public String getStatus() {
        return status;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }
}
