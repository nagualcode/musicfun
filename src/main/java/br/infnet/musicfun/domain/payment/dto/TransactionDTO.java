package br.infnet.musicfun.domain.payment.dto;

import java.time.LocalDate;

public class TransactionDTO {
    private Long id;
    private double amount;
    private String merchant;
    private String status;
    private LocalDate transactionDate;

    public TransactionDTO() {}

    public TransactionDTO(Long id, double amount, String merchant, String status, LocalDate transactionDate) {
        this.id = id;
        this.amount = amount;
        this.merchant = merchant;
        this.status = status;
        this.transactionDate = transactionDate;
    }

    // Getters and setters for all fields
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
