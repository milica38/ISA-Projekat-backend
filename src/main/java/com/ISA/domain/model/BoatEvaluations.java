package com.ISA.domain.model;

import javax.persistence.*;

@Entity
@Table(name = "boat_evaluations")
public class BoatEvaluations {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String content;
    private Long rate;
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
    public Long getRate(){return rate;}
    public void setRate(Long rate){this.rate = rate;}
}
