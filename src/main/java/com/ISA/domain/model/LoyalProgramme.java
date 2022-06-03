package com.ISA.domain.model;

import javax.persistence.*;


    @Entity
    @Table(name = "loyal_programme")
    public class LoyalProgramme {
        @Id
        @GeneratedValue(
                strategy = GenerationType.IDENTITY
        )
        private int id;
        private int silverPoints;
        private int goldPoints;
        private double silverAction;
        private double goldAction;
        private boolean deleted;
        private int reservationPoints;
        private int reservedPoints;

        @Version
        private Integer version;

        public Integer getVersion() { return version;}
        public void setVersion(Integer version) { this.version = version; }

        public int getId() { return id; }
        public void setId(int id) { this.id = id; }
        public int getSilverPoints() { return silverPoints; }
        public void setSilverPoints(int silverPoints) { this.silverPoints = silverPoints; }
        public int getGoldPoints() { return goldPoints; }
        public void setGoldPoints(int goldPoints) { this.goldPoints = goldPoints; }
        public double getSilverAction() { return silverAction; }
        public void setSilverAction(double silverAction) { this.silverAction = silverAction; }
        public double getGoldAction() { return goldAction; }
        public void setGoldAction(double goldAction) { this.goldAction = goldAction; }
        public boolean getDeleted() { return deleted; }
        public void setDeleted(boolean deleted){
            this.deleted = deleted;
        }
        public int getReservationPoints() { return reservationPoints; }
        public void setReservationPoints(int reservationPoints) { this.reservationPoints = reservationPoints; }
        public int getReservedPoints() { return reservedPoints; }
        public void setReservedPoints(int reservedPoints) { this.reservedPoints = reservedPoints; }


    }

