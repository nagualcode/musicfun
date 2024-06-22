package br.infnet.musicfun.domain.payment.model;

import br.infnet.musicfun.domain.core.model.BaseEntity;
import jakarta.persistence.Entity;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
public class Transaction extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private double amount;
    private Long subscriptionId;
    private String merchant;
    private String status;
    private LocalDateTime timestamp;
    private Long userId;

    // Getters and setters

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

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
