package com.ISA.domain.model;

import javax.persistence.*;

@Entity
@Table(name = "adventure_subscriptions")
public class AdventureSubscriptions {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    @ManyToOne
    private AdventureProfile adventureProfile;
    @ManyToOne
    private User client;
    private boolean isSubscribed;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public AdventureProfile getAdventureProfile() {
        return adventureProfile;
    }
    public void setAdventureProfile(AdventureProfile adventureProfile) {
        this.adventureProfile = adventureProfile;
    }
    public User getClient(){return client;}
    public void setClient(User client){this.client = client;}
    public boolean isSubscribed() {return isSubscribed;}
    public void setSubscribed(boolean subscribed) {isSubscribed = subscribed;}
}
