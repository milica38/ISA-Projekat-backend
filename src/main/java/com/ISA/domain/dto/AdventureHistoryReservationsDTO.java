package com.ISA.domain.dto;

import java.util.Date;

public class AdventureHistoryReservationsDTO {

    private long id;
    private Date startDate;
    private Date endDate;
    private double price;
    private boolean cancelled;
    private long adventureId;
    private long clientId;
    private long instructorId;


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
    public long getAdventureId(){return adventureId;}
    public void setAdventureId(int adventureId){this.adventureId = adventureId;}
    public long getClientId(){return clientId;}
    public void setClientId(long clientId){this.clientId = clientId;}
    public long getInstructorId(){return instructorId;}
    public void setInstructorId(long instructorId){this.instructorId = instructorId;}
}
