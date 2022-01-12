package com.ISA.domain.model;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "home_profile")
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
    private String behaviourRules;
    private double pricelist;
    private String extraService;
    private double extraPrice;
    @Column(columnDefinition = "TEXT")
    private String interiorImage;
    @Column(columnDefinition = "TEXT")
    private String exteriorImage;
    private Long ownerId;
    private boolean deleted;
    private int rateCounter;
    private double avgRate;


    public double getExtraPrice() {
        return extraPrice;
    }
    public void setExtraPrice(double extraPrice) {
        this.extraPrice = extraPrice;
    }
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
    public void setAddress(String address) { this.address = address; }
    public Long getownerId(){ return ownerId; }
    public void setOwnerId(Long id){
        this.ownerId = id;
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
    public boolean getDeleted() { return deleted; }
    public void setDeleted(boolean deleted){
        this.deleted = deleted;
    }
    public int getRateCounter(){return rateCounter;}
    public void setRateCounter(int rateCounter){this.rateCounter = rateCounter;}
    public double getAvgRate(){return avgRate;}
    public void setAvgRate(double avgRate){this.avgRate = avgRate;}

}
