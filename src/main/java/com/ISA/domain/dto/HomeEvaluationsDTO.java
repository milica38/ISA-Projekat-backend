package com.ISA.domain.dto;

public class HomeEvaluationsDTO {
    private Long id;
    private String content;
    private Long rate;
    private Long clientId;
    private Long homeReservationId;
    private boolean isApproved;
    private boolean isDeclined;

    public Long getId(){return id;}
    public void setId(Long id){this.id = id;}
    public String getContent(){return content;}
    public void setContent(String content){this.content = content;}
    public Long getClientId(){return clientId;}
    public void setClientId(Long clientId){this.clientId = clientId;}
    public Long getHomeReservationId(){return  homeReservationId;}
    public void setHomeReservationId(Long homeReservationId){this.homeReservationId = homeReservationId;}
    public Long getRate(){return rate;}
    public void setRate(Long rate){this.rate = rate;}
    public boolean getIsApproved(){return isApproved;}
    public void setApproved(boolean approved){ isApproved = approved;}
    public boolean getIsDeclined(){return isDeclined;}
    public void setDeclined(boolean declined){ isDeclined= declined;}
}
