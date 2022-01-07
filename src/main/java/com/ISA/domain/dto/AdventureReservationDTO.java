package com.ISA.domain.dto;

import java.util.Date;

public class AdventureReservationDTO {
    private long id;
    private Date startDate;
    private Date endDate;
    private String extraServices;
    private double price;
    private boolean cancelled;
    private long clientId;
    private long adventureId;


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
    public long getClientId(){return clientId;}
    public void setClientId(long clientId){this.clientId = clientId;}
    public long getAdventureId(){return adventureId;}
    public void setAdventureId(long adventureId) {
        this.adventureId = adventureId;
    }
}
