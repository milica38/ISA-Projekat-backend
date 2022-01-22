package com.ISA.domain.dto;

public class BoatReviewsDTO {

    private Long id;
    private String content;
    private Long ownerId;
    private Long boatReservationId;
    private boolean isAppear;
    private boolean isBadComment;
    private boolean onePenalty;

    public boolean isAppear() {
        return isAppear;
    }

    public boolean isBadComment() {
        return isBadComment;
    }

    public void setIsAppear(boolean appear) {
        isAppear = appear;
    }

    public void setIsBadComment(boolean badComment) {
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
    public Long getBoatReservationId(){return  boatReservationId;}
    public void setBoatReservationId(Long boatReservationId){this.boatReservationId = boatReservationId;}

}
