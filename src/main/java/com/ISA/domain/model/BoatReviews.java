package com.ISA.domain.model;

import javax.persistence.*;

@Entity
@Table(name = "boat_reviews")
public class BoatReviews {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String content;
    private Long ownerId;
    @OneToOne
    private BoatReservation boatReservation;
    private boolean isAppear;
    private boolean isBadComment;
    private boolean onePenalty;

    public boolean isAppear() {
        return isAppear;
    }

    public boolean isBadComment() {
        return isBadComment;
    }

    public void setAppear(boolean appear) {
        isAppear = appear;
    }

    public void setBadComment(boolean badComment) {
        isBadComment = badComment;
    }

    public boolean onePenalty() { return onePenalty; }

    public void setPenalty(boolean penalty) {onePenalty = penalty;}

    public Long getId(){return id;}
    public void setId(Long id){this.id = id;}
    public String getContent(){return content;}
    public void setContent(String content){this.content = content;}
    public Long getOwnerId(){return ownerId;}
    public void setOwnerId(Long ownerId){this.ownerId = ownerId;}
    public BoatReservation getBoatReservation() { return boatReservation; }
    public void setBoatReservation(BoatReservation boatReservation) { this.boatReservation = boatReservation; }

}
