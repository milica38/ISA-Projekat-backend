package com.ISA.domain.model;

import javax.persistence.*;

@Entity
@Table(name = "home_evaluations")
public class HomeEvaluations {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String content;
    private Long rate;
    private Long clientId;
    @ManyToOne
    private HomeReservation homeReservation;

    public Long getId(){return id;}
    public void setId(Long id){this.id = id;}
    public String getContent(){return content;}
    public void setContent(String content){this.content = content;}
    public Long getClientId(){return clientId;}
    public void setClientId(Long clientId){this.clientId = clientId;}
    public HomeReservation getHomeReservation(){return  homeReservation;}
    public void setHomeReservation(HomeReservation homeReservation){this.homeReservation = homeReservation;}
    public Long getRate(){return rate;}
    public void setRate(Long rate){this.rate = rate;}
}
