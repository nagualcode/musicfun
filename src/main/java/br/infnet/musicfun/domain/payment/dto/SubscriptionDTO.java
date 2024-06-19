package br.infnet.musicfun.domain.payment.dto;

public class SubscriptionDTO {
    private Long id;
    private String planName;
    private double price;
    private String duration;

    public SubscriptionDTO() {}

    public SubscriptionDTO(Long id, String planName, double price, String duration) {
        this.id = id;
        this.planName = planName;
        this.price = price;
        this.duration = duration;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
