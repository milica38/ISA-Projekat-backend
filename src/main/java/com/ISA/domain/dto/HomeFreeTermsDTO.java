package com.ISA.domain.dto;

import java.util.Date;

public class HomeFreeTermsDTO {

    private Long id;
    private Date startDate;
    private Date endDate;
    private Long houseId;
    private double actionPrice;
    private boolean isAction;


    public void setActionPrice(double actionPrice) {
        this.actionPrice = actionPrice;
    }
    public double getActionPrice() {
        return actionPrice;
    }
    public boolean isAction() {
        return isAction;
    }
    public void setIsAction(boolean action) {
        isAction = action;
    }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Date getStartDate() { return startDate; }
    public void setStartDate(Date startDate) { this.startDate = startDate; }
    public Date getEndDate() { return  endDate; }
    public void  setEndDate(Date endDate) { this.endDate = endDate; }
    public Long getHouseId() { return houseId; }
    public void setHouseId(Long houseId) { this.houseId = houseId; }

}
