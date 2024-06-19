package br.infnet.musicfun.domain.payment.dto;

public class TransactionDTO {
    private Long id;
    private double amount;
    private String merchant;
    private String date;

    public TransactionDTO() {}

    public TransactionDTO(Long id, double amount, String merchant, String date) {
        this.id = id;
        this.amount = amount;
        this.merchant = merchant;
        this.date = date;
    }

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
