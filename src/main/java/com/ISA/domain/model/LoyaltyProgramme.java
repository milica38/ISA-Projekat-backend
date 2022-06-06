package com.ISA.domain.model;

import javax.persistence.*;

@Entity
@Table(name = "loyalty_programme")
public class LoyaltyProgramme {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )

    private int numberOfAllReservations;
    private int numberOfBoatReservations;
    private int numberOfHouseReservations;
    private int numberOfInstructorReservations;
    private double action;
    private long loyaltyId;
    private boolean deleted;
    private String name;

    @Version
    private Integer version;

    public Integer getVersion() { return version;}
    public void setVersion(Integer version) { this.version = version; }

    public int getNumberOfAllReservations(){ return numberOfAllReservations; }
    public void setNumberOfAllReservations(int numberOfAllReservations){ this.numberOfAllReservations = numberOfAllReservations; }
    public int getNumberOfBoatReservations(){ return numberOfBoatReservations; }
    public void setNumberOfBoatReservations(int numberOfBoatReservations){ this.numberOfBoatReservations = numberOfBoatReservations; }
    public int getNumberOfHouseReservations(){ return numberOfHouseReservations; }
    public void setNumberOfHouseReservations(int numberOfHouseReservations){ this.numberOfHouseReservations = numberOfHouseReservations; }
    public int getNumberOfInstructorReservations(){ return numberOfInstructorReservations; }
    public void setNumberOfInstructorReservations(int numberOfInstructorReservations){ this.numberOfInstructorReservations = numberOfInstructorReservations; }
    public double getAction(){ return action; }
    public void setAction(double action){ this.action = action; }
    public long getLoyaltyId(){ return loyaltyId; }
    public void setLoyaltyId(long loyaltyId){ this.loyaltyId = loyaltyId; }
    public boolean getDeleted() { return deleted; }
    public void setDeleted(boolean deleted){
        this.deleted = deleted;
    }
    public String getName() { return name; }
    public void setName(String name){
        this.name = name;
    }
}
