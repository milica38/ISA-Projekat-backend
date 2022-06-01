package com.ISA.domain.dto;

import java.util.Date;

public class AdventureNotFreeTermsDTO {

    private Long id;
    private Date startDate;
    private Date endDate;
    private Long adventureId;


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Date getStartDate() { return startDate; }
    public void setStartDate(Date startDate) { this.startDate = startDate; }
    public Date getEndDate() { return  endDate; }
    public void  setEndDate(Date endDate) { this.endDate = endDate; }
    public Long getAdventureId() { return adventureId; }
    public void setAdventureId(Long adventureId) { this.adventureId = adventureId; }

}
