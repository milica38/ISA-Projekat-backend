package com.ISA.domain.model;
import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "boat_profile")

public class BoatProfile {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
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
    private Date freeTerms;
    private String behaviourRules;
    private double pricelist;
    private String extraService;
    private String fishingEquipment;
    private String cancelConditions;

    private double latitude;
    private double longitude;
    private boolean deleted;

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
    public double getLatitude(){
        return latitude;
    }
    public void setLatitude(double latitude){
        this.latitude = latitude;
    }
    public double getLongitude(){
        return longitude;
    }
    public void setLongitude(double longitude){
        this.longitude = longitude;
    }
    public String getPromoDescription(){
        return promoDescription;
    }
    public void setPromoDescription(String promoDescription){
        this.promoDescription = promoDescription;
    }
    public Date getFreeTerms(){
        return freeTerms;
    }
    public void setFreeTerms(Date freeTerms){
        this.freeTerms = freeTerms;
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
    public boolean getDeleted(){
        return deleted;
    }
    public void setDeleted(boolean deleted){
        this.deleted = deleted;
    }

}
