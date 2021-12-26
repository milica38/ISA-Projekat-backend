package com.ISA.domain.dto;

import java.util.Date;

public class AdventureReservationDTO {
    private long id;
    private Date reservationStart;
    private String reservationPlace;
    private String durationReservation;
    private int maximumNumberOfPeople;
    private String extraServices;
    private double price;
    private boolean deleted;

    public Long getId(){ return id; }
    public void setId(Long id){ this.id = id; }
    public Date getReservationStart(){ return reservationStart; }
    public void setReservationStart(Date reservationStart){ this.reservationStart = reservationStart; }
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
    public boolean getDeleted(){ return deleted; }
    public void setDeleted(boolean deleted){ this.deleted = deleted; }
}
