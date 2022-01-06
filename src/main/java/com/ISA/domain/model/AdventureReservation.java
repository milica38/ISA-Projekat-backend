package com.ISA.domain.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "adventure_reservation")
public class AdventureReservation {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private long id;
    private Date startDate;
    private Date endDate;
    private String reservationPlace;
    private String durationReservation;
    private int maximumNumberOfPeople;
    private String extraServices;
    private double price;
    private boolean cancelled;
    private long clientId;
    @ManyToOne
    private AdventureProfile adventureProfile;


    public Long getId(){ return id; }
    public void setId(Long id){ this.id = id; }
    public Date getStartDate(){ return startDate; }
    public void setStartDate(Date startDate){ this.startDate = startDate; }
    public Date getEndDate(){ return endDate; }
    public void setEndDate(Date endDate){ this.endDate = endDate; }
    public String getReservationPlace(){ return reservationPlace; }
    public void setReservationPlace(String reservationPlace){ this.reservationPlace = reservationPlace; }
    public String getDurationReservation(){ return durationReservation; }
    public void setDurationReservation(String durationReservation){ this.durationReservation = durationReservation;}
    public int getMaximumNumberOfPeople(){ return maximumNumberOfPeople; }
    public void setMaximumNumberOfPeople(int maximumNumberOfPeople){ this.maximumNumberOfPeople = maximumNumberOfPeople;}
    public String getExtraServices(){ return extraServices; }
    public void setExtraServices(String extraServices){ this.extraServices = extraServices; }
    public double getPrice(){ return price; }
    public void setPrice(double price){ this.price = price; }
    public boolean getCancelled(){ return cancelled; }
    public void setCancelled(boolean cancelled){ this.cancelled = cancelled; }
    public long getClientId(){return clientId;}
    public void setClientId(long clientId){this.clientId = clientId;}
    public AdventureProfile getAdventureProfile(){return adventureProfile;}
    public void setAdventureProfile(AdventureProfile adventureProfile) {
        this.adventureProfile = adventureProfile;
    }
}
