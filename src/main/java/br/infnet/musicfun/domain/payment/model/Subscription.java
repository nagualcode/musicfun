package br.infnet.musicfun.domain.payment.model;

import br.infnet.musicfun.domain.core.model.BaseEntity;
import jakarta.persistence.Entity;
import java.time.LocalDate;

@Entity
public class Subscription extends BaseEntity {
    private String type;
    private String planName;
    private double price;
    private LocalDate startDate;
    private LocalDate endDate;

    public Subscription() {
    }

    public Subscription(Long id, String type, String planName, double price, LocalDate startDate, LocalDate endDate) {
        this.setId(id);
        this.type = type;
        this.planName = planName;
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getDuration() {
        return String.format("%d days", endDate.toEpochDay() - startDate.toEpochDay());
    }
}
