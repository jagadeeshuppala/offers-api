package com.worldpay.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Offer {

    @Id @GeneratedValue
    private Long id;
    private String offer;
    private String description;
    private LocalDate createdDate;
    private LocalDate expiryDate;
    private Boolean cancelOffer;

    public Offer() {
        this.createdDate = LocalDate.now();
        this.cancelOffer = Boolean.FALSE;
    }

    public Offer(String offer, String description, LocalDate expiryDate) {
        this.offer = offer;
        this.description = description;
        this.createdDate = LocalDate.now();
        this.expiryDate = expiryDate;
        this.cancelOffer = Boolean.FALSE;
    }

    public Offer(String offer, String description, LocalDate expiryDate, Boolean cancelOffer) {
        this.offer = offer;
        this.description = description;
        this.createdDate = LocalDate.now();
        this.expiryDate = expiryDate;
        this.cancelOffer = cancelOffer;
    }

    public Offer(Long id, String offer, String description, LocalDate createdDate, LocalDate expiryDate, Boolean cancelOffer) {
        this.id = id;
        this.offer = offer;
        this.description = description;
        this.createdDate = createdDate;
        this.expiryDate = expiryDate;
        this.cancelOffer = cancelOffer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOffer() {
        return offer;
    }

    public void setOffer(String offer) {
        this.offer = offer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Boolean getCancelOffer() {
        return cancelOffer;
    }

    public void setCancelOffer(Boolean cancelOffer) {
        this.cancelOffer = cancelOffer;
    }



    @Override
    public String toString() {
        return "Offer{" +
                "id=" + id +
                ", offer='" + offer + '\'' +
                ", description='" + description + '\'' +
                ", createdDate=" + createdDate +
                ", expiryDate=" + expiryDate +
                ", cancelOffer=" + cancelOffer +
                '}';
    }
}
