package br.infnet.musicfun.domain.payment.model;

import br.infnet.musicfun.domain.core.model.BaseEntity;
import jakarta.persistence.Entity;
import java.time.LocalDate;

@Entity
public class Transaction extends BaseEntity {
    private static final long serialVersionUID = 1L;
    
    private double amount;
    private String merchant;
    private String status;
    private LocalDate transactionDate;

    public Transaction() {
    }

    public Transaction(Long id, double amount, String merchant, String status, LocalDate transactionDate) {
        this.setId(id);
        this.amount = amount;
        this.merchant = merchant;
        this.status = status;
        this.transactionDate = transactionDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }
}
