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
public class Transaction implements Serializable {
    private static final long serialVersionUID = 1L;

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
