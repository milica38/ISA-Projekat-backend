package com.ISA.domain.model;

import javax.persistence.*;

@Entity
@Table(name = "adventure_evaluations")
public class AdventureEvaluations {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String content;
    private Long rate;
    private Long clientId;
    @ManyToOne
    private AdventureReservation adventureReservation;

    public Long getId(){return id;}
    public void setId(Long id){this.id = id;}
    public String getContent(){return content;}
    public void setContent(String content){this.content = content;}
    public Long getClientId(){return clientId;}
    public void setClientId(Long clientId){this.clientId = clientId;}
    public AdventureReservation getAdventureReservation(){return  adventureReservation;}
    public void setAdventureReservation(AdventureReservation adventureReservation){this.adventureReservation = adventureReservation;}
    public Long getRate(){return rate;}
    public void setRate(Long rate){this.rate = rate;}

}
