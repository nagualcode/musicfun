package br.infnet.musicfun.domain.payment.dto;

import java.time.LocalDate;

public class TransactionDTO {
    private Long id;
    private double amount;
    private Long subscriptionId;
    private String merchant;
    private String status;
    private LocalDate transactionDate;

    // Full constructor
    public TransactionDTO(Long id, double amount, Long subscriptionId, String merchant, String status, LocalDate transactionDate) {
        this.id = id;
        this.amount = amount;
        this.subscriptionId = subscriptionId;
        this.merchant = merchant;
        this.status = status;
        this.transactionDate = transactionDate;
    }

    // Getters and setters

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

    public Long getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(Long subscriptionId) {
        this.subscriptionId = subscriptionId;
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
