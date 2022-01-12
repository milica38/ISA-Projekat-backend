package com.ISA.domain.dto;

import java.util.Date;

public class HomeReservationDTO {

    private long id;
    private Date startDate;
    private Date endDate;
    private String extraServices;
    private double price;
    private boolean cancelled;
    private int numberOfDays;
    private int numberOfPeople;
    private long houseId;
    private long clientId;
    private String status;

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
    public long getHouseId(){return houseId;}
    public void setHouseId(int houseId){this.houseId = houseId;}
    public long getClientId(){return clientId;}
    public void setClientId(long clientId){this.clientId = clientId;}
    public String getStatus(){return status;}
    public void setStatus(String status){this.status = status;}
}
