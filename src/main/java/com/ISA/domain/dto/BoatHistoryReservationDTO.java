package com.ISA.domain.dto;

import java.util.Date;

public class BoatHistoryReservationDTO {

    private long id;
    private Date startDate;
    private Date endDate;
    private double price;
    private boolean cancelled;
    private long boatId;
    private long clientId;
    private long ownerId;

    public void setBoatId(long boatId) {
        this.boatId = boatId;
    }

    public long getBoatId() {
        return boatId;
    }

    public long getId(){ return id; }
    public void setId(Long id){ this.id = id; }
    public Date getStartDate(){ return startDate; }
    public void setStartDate(Date startDate){ this.startDate = startDate; }
    public Date getEndDate(){ return endDate; }
    public void setEndDate(Date endDate){ this.endDate = endDate; }
    public double getPrice(){ return price; }
    public void setPrice(double price){ this.price = price; }
    public boolean getCancelled(){ return cancelled; }
    public void setCancelled(boolean cancelled){ this.cancelled = cancelled; }
    public long getClientId(){return clientId;}
    public void setClientId(long clientId){this.clientId = clientId;}
    public long getOwnerId(){return ownerId;}
    public void setOwnerId(long ownerId){this.ownerId = ownerId;}

}
