package com.ISA.domain.dto;

public class BoatSubscriptionsDTO {

    private Long id;
    private Long boatId;
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
    public Long getHouseId(){return boatId;}
    public void setHouseId(Long boatId){this.boatId = boatId;}
}
