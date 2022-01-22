package com.ISA.domain.dto;

public class BoatComplaintsDTO {

    private Long id;
    private String content;
    private Long clientId;
    private Long boatReservationId;
    private String complaintResponse;

    public Long getId(){return id;}
    public void setId(Long id){this.id = id;}
    public String getContent(){return content;}
    public void setContent(String content){this.content = content;}
    public Long getClientId(){return clientId;}
    public void setClientId(Long clientId){this.clientId = clientId;}
    public Long getBoatReservationId(){return  boatReservationId;}
    public void setBoatReservationId(Long boatReservationId){this.boatReservationId = boatReservationId;}
    public String getComplaintResponse(){return complaintResponse;}
    public void setComplaintResponse(String complaintResponse){this.complaintResponse = complaintResponse;}
}
