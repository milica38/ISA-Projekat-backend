package com.ISA.domain.dto;

import java.util.Date;

public class SearchFreeHomesDTO {
    private long id;
    private Date startDate;
    private Date endDate;
    private String location;

    public Long getId(){ return id; }
    public void setId(Long id){ this.id = id; }
    public Date getStartDate(){ return startDate; }
    public void setStartDate(Date startDate){ this.startDate = startDate; }
    public Date getEndDate(){ return endDate; }
    public void setEndDate(Date endDate){ this.endDate = endDate; }
    public String getLocation(){return location;}
    public void setLocation(){String Location;}
}
