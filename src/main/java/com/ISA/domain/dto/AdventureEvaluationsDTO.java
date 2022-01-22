package com.ISA.domain.dto;

public class AdventureEvaluationsDTO {
    private Long id;
    private String content;
    private Long rate;
    private Long clientId;
    private Long adventureReservationId;
    private boolean isApproved;
    private boolean isDeclined;

    public Long getId(){return id;}
    public void setId(Long id){this.id = id;}
    public String getContent(){return content;}
    public void setContent(String content){this.content = content;}
    public Long getClientId(){return clientId;}
    public void setClientId(Long clientId){this.clientId = clientId;}
    public Long getAdventureReservationId(){return  adventureReservationId;}
    public void setAdventureReservationId(Long adventureReservationId){this.adventureReservationId = adventureReservationId;}
    public Long getRate(){return rate;}
    public void setRate(Long rate){this.rate = rate;}
    public boolean getIsApproved(){return isApproved;}
    public void setApproved(boolean approved){ isApproved = approved;}
    public boolean getIsDeclined(){return isDeclined;}
    public void setDeclined(boolean declined){ isDeclined= declined;}
}
