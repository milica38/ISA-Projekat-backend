package com.ISA.domain.dto;

import java.util.Date;

public class AdventureProfileDTO {
    private Long id;
    private String name;
    private String address;
    private double latitude;
    private double longitude;
    private String promoDescription;
    private String instructorBiography;
    private String ambientImage;
    private int maxNumberOfPeople;
    private Date freeTerm;
    private String behaviourRules;
    private String fishingEquipment;
    private double priceList;
    private String extraService;
    private String cancelConditions;
    private boolean deleted;

    public Long getId(){ return id; }
    public void setId(Long id){ this.id = id;}
    public String getName(){ return name; }
    public void setName(String name){ this.name = name; }
    public String getAddress(){ return address; }
    public void setAddress(String address){ this.address = address;}
    public double getLatitude(){ return latitude; }
    public void setLatitude(double latitude){ this.latitude = latitude; }
    public double getLongitude(){ return longitude; }
    public void setLongitude(double longitude){ this.longitude = longitude; }
    public String getPromoDescription(){ return promoDescription; }
    public void setPromoDescription(String promoDescription){ this.promoDescription = promoDescription; }
    public String getInstructorBiography(){ return instructorBiography; }
    public void setInstructorBiography(String instructorBiography){ this.instructorBiography = instructorBiography; }
    public String getAmbientImage(){ return ambientImage; }
    public void setAmbientImage(String ambientImage){ this.ambientImage = ambientImage; }
    public int getMaxNumberOfPeople(){ return maxNumberOfPeople; }
    public void setMaxNumberOfPeople(int maxNumberOfPeople){ this.maxNumberOfPeople = maxNumberOfPeople; }
    public Date getFreeTerm(){ return freeTerm; }
    public void setFreeTerm(Date freeTerm){ this.freeTerm = freeTerm; }
    public String getBehaviourRules(){ return behaviourRules; }
    public void setBehaviourRules(String behaviourRules){ this.behaviourRules = behaviourRules; }
    public String getFishingEquipment(){ return fishingEquipment; }
    public void setFishingEquipment(String fishingEquipment){ this.fishingEquipment = fishingEquipment; }
    public double getPriceList(){ return priceList; }
    public void setPriceList(double priceList){ this.priceList = priceList; }
    public String getExtraService(){ return extraService; }
    public void setExtraService(String extraService){ this.extraService = extraService; }
    public String getCancelConditions(){ return cancelConditions; }
    public void setCancelConditions(String cancelConditions){ this.cancelConditions = cancelConditions; }
    public boolean getDeleted(){ return deleted; }
    public void setDeleted(boolean deleted){ this.deleted = deleted; }

}
