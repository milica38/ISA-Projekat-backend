package com.ISA.domain.dto;

import java.util.Date;

public class HomeProfileDTO {

    private Long id;
    private String name;
    private String address;
    private String promoDescription;
    private int numberOfRooms;
    private int numberOfBeds;
    private Date freeTerms;
    private String behaviourRules;
    private double pricelist;
    private String extraService;
    private String interiorImage;
    private String exteriorImage;
    private Long ownerId;

    public Long getownerId(){ return ownerId; }
    public void setOwnerId(Long id){
        this.ownerId = ownerId;
    }
    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public String getName(){ return name; }
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
    public int getNumberOfRooms(){
        return numberOfRooms;
    }
    public void setNumberOfRooms(int numberOfRooms){
        this.numberOfRooms = numberOfRooms;
    }
    public int getNumberOfBeds(){
        return numberOfBeds;
    }
    public void setNumberOfBeds(int numberOfBeds){
        this.numberOfBeds = numberOfBeds;
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
}
