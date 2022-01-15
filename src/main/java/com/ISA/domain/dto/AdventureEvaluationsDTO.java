package com.ISA.domain.dto;

public class AdventureEvaluationsDTO {
    private Long id;
    private String content;
    private Long rate;
    private Long clientId;
    private Long adventureReservationId;

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
}
