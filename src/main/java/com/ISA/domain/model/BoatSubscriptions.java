package com.ISA.domain.model;

import javax.persistence.*;

@Entity
@Table(name = "boat_subscriptions")
public class BoatSubscriptions {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    @ManyToOne
    private BoatProfile boatProfile;
    @ManyToOne
    private User client;
    private boolean isSubscribed;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public BoatProfile getBoatProfile() { return boatProfile; }
    public void setBoatProfile(BoatProfile boatProfile) {
        this.boatProfile = boatProfile;
    }
    public User getClient(){return client;}
    public void setClient(User client){this.client = client;}
    public boolean isSubscribed() {return isSubscribed;}
    public void setSubscribed(boolean subscribed) {isSubscribed = subscribed;}
}
