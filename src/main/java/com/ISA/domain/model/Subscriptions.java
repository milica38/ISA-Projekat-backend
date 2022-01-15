package com.ISA.domain.model;

import javax.persistence.*;

@Entity
@Table(name = "action_subscriptions")
public class Subscriptions {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    @ManyToOne
    private HomeProfile homeProfile;
    @ManyToOne
    private User client;
    private boolean isSubscribed;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public HomeProfile getHomeProfile() {
        return homeProfile;
    }
    public void setHomeProfile(HomeProfile homeProfile) {
        this.homeProfile = homeProfile;
    }
    public User getClient(){return client;}
    public void setClient(User client){this.client = client;}
    public boolean isSubscribed() {return isSubscribed;}
    public void setSubscribed(boolean subscribed) {isSubscribed = subscribed;}
}
