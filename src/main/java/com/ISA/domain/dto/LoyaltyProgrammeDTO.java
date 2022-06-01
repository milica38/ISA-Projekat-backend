package com.ISA.domain.dto;

import javax.persistence.Version;

public class LoyaltyProgrammeDTO {
    private int numberOfAllReservations;
    private int numberOfBoatReservations;
    private int numberOfHouseReservations;
    private int numberOfInstructorReservations;
    private double action;
    private long loyaltyId;
    private boolean deleted;

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
}
