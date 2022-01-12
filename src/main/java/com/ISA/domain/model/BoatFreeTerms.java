package com.ISA.domain.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "boat_free_terms")

public class BoatFreeTerms {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private Date startDate;
    private Date endDate;
    private double actionPrice;
    private boolean isAction;

    @ManyToOne
    private BoatProfile boatProfile;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Date getStartDate() { return startDate; }
    public void setStartDate(Date startDate) { this.startDate = startDate; }
    public Date getEndDate() { return  endDate; }
    public void  setEndDate(Date endDate) { this.endDate = endDate; }
    public BoatProfile getBoatProfile() { return  boatProfile; }
    public void setBoatProfile(BoatProfile boatProfile) {
        this.boatProfile = boatProfile;
    }
    public void setActionPrice(double actionPrice) {
        this.actionPrice = actionPrice;
    }
    public double getActionPrice() {
        return actionPrice;
    }
    public boolean isAction() {
        return isAction;
    }
    public void setAction(boolean action) {
        isAction = action;
    }
}
