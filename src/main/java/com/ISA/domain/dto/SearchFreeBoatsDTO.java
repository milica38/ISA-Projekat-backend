package com.ISA.domain.dto;

import java.util.Date;

public class SearchFreeBoatsDTO {

    private long id;
    private Date startDate;
    private Date endDate;
    private String address;
    private int capacity;

    
    public int getCapacity() { return  capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }
    public Long getId(){ return id; }
    public void setId(Long id){ this.id = id; }
    public Date getStartDate(){ return startDate; }
    public void setStartDate(Date startDate){ this.startDate = startDate; }
    public Date getEndDate(){ return endDate; }
    public void setEndDate(Date endDate){ this.endDate = endDate; }
    public String getAddress(){
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
}
