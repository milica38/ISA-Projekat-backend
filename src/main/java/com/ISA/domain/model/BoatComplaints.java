package com.ISA.domain.model;

import javax.persistence.*;

@Entity
@Table(name = "boat_complaints")
public class BoatComplaints {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String content;
    private Long clientId;
    @ManyToOne
    private BoatReservation boatReservation;

    public Long getId(){return id;}
    public void setId(Long id){this.id = id;}
    public String getContent(){return content;}
    public void setContent(String content){this.content = content;}
    public Long getClientId(){return clientId;}
    public void setClientId(Long clientId){this.clientId = clientId;}
    public BoatReservation getBoatReservation(){return  boatReservation;}
    public void setBoatReservation(BoatReservation boatReservation){this.boatReservation = boatReservation;}

}
