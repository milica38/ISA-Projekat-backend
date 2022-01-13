package com.ISA.domain.dto;

import java.util.Date;

public class BoatProfileDTO {
    
    private Long id;
    private String name;
    private String type;
    private int length;
    private int engineNumber;
    private int enginePower;
    private  int maxSpeed;
    private String navEquipment;
    private String address;
    private String promoDescription;
    private String interiorImage;
    private String exteriorImage;
    private String capacity;
    private String behaviourRules;
    private double pricelist;
    private String extraService;
    private String fishingEquipment;
    private String cancelConditions;
    private double extraPrice;
    private Long ownerId;
    private String searchTerm;
    private double latitude;
    private double longitude;


    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Long getownerId(){ return ownerId; }
    public void setOwnerId(Long id){
        this.ownerId = id;
    }
    public double getExtraPrice() {
        return extraPrice;
    }
    public void setExtraPrice(double extraPrice) {
        this.extraPrice = extraPrice;
    }
    public int getMaxSpeed() { return  maxSpeed; }
    public void setMaxSpeed(int maxSpeed) { this.maxSpeed = maxSpeed; }
    public int getEnginePower() { return  enginePower; }
    public void setEnginePower(int enginePower) { this.enginePower = enginePower; }
    public int getEngineNumber() { return  engineNumber; }
    public void setEngineNumber(int engineNumber) { this.engineNumber = engineNumber; }
    public String getType() { return  type; }
    public void setType(String type) { this.type = type; }
    public int getLength() { return  length; }
    public void setLength(int length) { this.length = length; }
    public String getFishingEquipment() { return fishingEquipment; }
    public void  setFishingEquipment(String fishingEquipment) { this.fishingEquipment = fishingEquipment; }
    public String getCancelConditions() { return  cancelConditions; }
    public void  setCancelConditions(String cancelConditions) { this.cancelConditions = cancelConditions; }
    public String getCapacity() { return  capacity; }
    public void setCapacity(String capacity) { this.capacity = capacity; }
    public String getNavEquipment() { return navEquipment; }
    public void setNavEquipment(String navEquipment) { this.navEquipment = navEquipment; }
    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getAddress(){
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getPromoDescription(){
        return promoDescription;
    }
    public void setPromoDescription(String promoDescription){
        this.promoDescription = promoDescription;
    }
    public String getBehaviourRules(){
        return behaviourRules;
    }
    public void setBehaviourRules(String behaviourRules){
        this.behaviourRules = behaviourRules;
    }
    public double getPricelist(){
        return pricelist;
    }
    public void setPriceList(double pricelist){
        this.pricelist = pricelist;
    }
    public String getExtraService(){
        return extraService;
    }
    public void setExtraService(String extraService){
        this.extraService = extraService;
    }
    public String getInteriorImage(){
        return interiorImage;
    }
    public void setInteriorImage(String interiorImage){
        this.interiorImage = interiorImage;
    }
    public String getExteriorImage(){
        return exteriorImage;
    }
    public void setExteriorImage(String exteriorImage){
        this.exteriorImage = exteriorImage;
    }
    public String getSearchTerm(){return searchTerm;}
    public void setSearchTerm(String searchTerm){this.searchTerm = searchTerm;}

}
