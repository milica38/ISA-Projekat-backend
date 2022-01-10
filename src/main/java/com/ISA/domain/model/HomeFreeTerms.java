package com.ISA.domain.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "home_free_terms")
public class HomeFreeTerms {
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
    private HomeProfile homeProfile;
   // private Long clientId;
  //  private boolean isSubscribed;

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
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Date getStartDate() { return startDate; }
    public void setStartDate(Date startDate) { this.startDate = startDate; }
    public Date getEndDate() { return  endDate; }
    public void  setEndDate(Date endDate) { this.endDate = endDate; }
    public HomeProfile getHomeProfile() {
        return homeProfile;
    }
    public void setHomeProfile(HomeProfile homeProfile) {
        this.homeProfile = homeProfile;
    }
   // public Long getClientId(){return clientId;}
  //  public void setClientId(Long clientId){this.clientId = clientId;}
    //public boolean isSubscribed() {return isSubscribed;}
   // public void setSubscribed(boolean subscribed) {isSubscribed = subscribed;}
}
