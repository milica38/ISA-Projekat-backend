package com.ISA.domain.dto;

public class AdventureSubscriptionsDTO {

    private Long id;
    private Long adventureId;
    private Long clientId;
    private boolean isSubscribed;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getClientId(){return clientId;}
    public void setClientId(Long clientId){this.clientId = clientId;}
    public boolean isSubscribed() {
        return isSubscribed;
    }
    public void setSubscribed(boolean subscribed) {
        isSubscribed = subscribed;
    }
    public Long getAdventureId(){return adventureId;}
    public void setAdventureId(Long adventureId){this.adventureId = adventureId;}
}
