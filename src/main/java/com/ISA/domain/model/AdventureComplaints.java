package com.ISA.domain.model;

import javax.persistence.*;

@Entity
@Table(name = "adventure_complaints")
public class AdventureComplaints {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String content;
    private Long clientId;
    @ManyToOne
    private AdventureReservation adventureReservation;
    private String complaintResponse;

    public Long getId(){return id;}
    public void setId(Long id){this.id = id;}
    public String getContent(){return content;}
    public void setContent(String content){this.content = content;}
    public Long getClientId(){return clientId;}
    public void setClientId(Long clientId){this.clientId = clientId;}
    public AdventureReservation getAdventureReservation(){return  adventureReservation;}
    public void setAdventureReservation(AdventureReservation adventureReservation){this.adventureReservation = adventureReservation;}
    public String getComplaintResponse(){return complaintResponse;}
    public void setComplaintResponse(String complaintResponse){this.complaintResponse = complaintResponse;}
}
