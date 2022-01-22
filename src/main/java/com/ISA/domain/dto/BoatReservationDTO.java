package com.ISA.domain.dto;

import java.util.Date;

public class BoatReservationDTO {

    private long id;
    private Date startDate;
    private Date endDate;
    private String extraServices;
    private double price;
    private boolean cancelled;
    private boolean writed;
    private int numberOfDays;
    private int numberOfPeople;
    private long boatId;
    private long clientId;

    public boolean getWrited(){ return writed; }
    public void setWrited(boolean writed){ this.writed = writed; }
    public long getId(){ return id; }
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
    public long getBoatId(){return boatId;}
    public void setBoatId(int boatId){this.boatId = boatId;}
    public long getClientId(){return clientId;}
    public void setClientId(long clientId){this.clientId = clientId;}
}
