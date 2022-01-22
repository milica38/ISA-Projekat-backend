package com.ISA.domain.model;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "boat_reservations")
public class BoatReservation {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private long id;
    private Date startDate;
    private Date endDate;
    private String extraServices;
    private double price;
    private boolean cancelled;
    private boolean writed;
    private int numberOfDays;
    private int numberOfPeople;
    @ManyToOne
    private BoatProfile boatProfile;
    private Long clientId;

    public boolean getWrited(){ return writed; }
    public void setWrited(boolean writed){ this.writed = writed; }
    public Long getId(){ return id; }
    public void setId(Long id){ this.id = id; }
    public Date getStartDate(){ return startDate; }
    public void setStartDate(Date startDate){ this.startDate = startDate; }
    public Date getEndDate(){ return endDate; }
    public void setEndDate(Date endDate){ this.endDate = endDate; }
    public String getExtraServices(){ return extraServices; }
    public void setExtraServices(String extraServices){ this.extraServices = extraServices; }
    public double getPrice(){ return price; }
    public void setPrice(double price){ this.price = price; }
    public boolean getCancelled(){ return cancelled; }
    public void setCancelled(boolean cancelled){ this.cancelled = cancelled; }
    public int getNumberOfDays() {return numberOfDays;}
    public void setNumberOfDays(int numberOfDays){this.numberOfDays = numberOfDays;}
    public int getNumberOfPeople(){return  numberOfPeople;}
    public void  setNumberOfPeople(int numberOfPeople){this.numberOfPeople = numberOfPeople;}
    public BoatProfile getBoatProfile(){return boatProfile;}
    public void setBoatProfile(BoatProfile boatProfile) {
        this.boatProfile = boatProfile;
    }
    public Long getClientId(){return clientId;}
    public void setClientId(Long clientId){this.clientId = clientId;}
}
