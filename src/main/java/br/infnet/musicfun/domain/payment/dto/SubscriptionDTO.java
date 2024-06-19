package br.infnet.musicfun.domain.payment.dto;

import java.time.LocalDate;

public class SubscriptionDTO {
    private Long id;
    private String planName;
    private double price;
    private String duration;
    private LocalDate startDate;
    private LocalDate endDate;
    private String type;

    public SubscriptionDTO() {}

    // Constructor with type parameter
    public SubscriptionDTO(Long id, String planName, String duration, double price, LocalDate startDate, LocalDate endDate, String type) {
        this.id = id;
        this.planName = planName;
        this.duration = duration;
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
        this.type = type;
    }

    // Constructor without type parameter
    public SubscriptionDTO(Long id, String planName, String duration, double price, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.planName = planName;
        this.duration = duration;
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // Getters and setters for all fields
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
