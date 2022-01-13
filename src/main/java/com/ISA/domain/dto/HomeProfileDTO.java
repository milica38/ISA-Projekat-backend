package com.ISA.domain.dto;

import java.util.Date;

public class HomeProfileDTO {

    private Long id;
    private String name;
    private String address;
    private String promoDescription;
    private int numberOfRooms;
    private int numberOfBeds;
    private String behaviourRules;
    private double pricelist;
    private String extraService;
    private String interiorImage;
    private String exteriorImage;
    private Long ownerId;
    private double extraPrice;
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

    public double getExtraPrice() {
        return extraPrice;
    }

    public void setExtraPrice(double extraPrice) {
        this.extraPrice = extraPrice;
    }

    public Long getownerId(){ return ownerId; }
    public void setOwnerId(Long id){
        this.ownerId = id;
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
    public String getBehaviourRules(){
        return behaviourRules;
    }
    public void setBehaviourRules(String behaviourRules){
        this.behaviourRules = behaviourRules;
    }
    public double getPricelist(){
        return pricelist;
    }
    public void setPricelist(double pricelist){
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
    public String getSearchTerm(){return searchTerm;}
    public void setSearchTerm(String searchTerm){this.searchTerm = searchTerm;}
}
