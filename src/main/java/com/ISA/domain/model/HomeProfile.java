package com.ISA.domain.model;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "boat_profile")

public class HomeProfile {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
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

    private double latitude;
    private double longitude;
    private boolean deleted;

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
    public boolean getDeleted(){
        return deleted;
    }
    public void setDeleted(boolean deleted){
        this.deleted = deleted;
    }
}
