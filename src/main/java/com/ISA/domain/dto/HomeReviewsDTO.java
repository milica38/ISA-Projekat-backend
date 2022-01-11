package com.ISA.domain.dto;

import com.ISA.domain.model.HomeReservation;

public class HomeReviewsDTO {

    private Long id;
    private String content;
    private Long ownerId;
    private Long homeReservationId;
    private boolean isAppear;
    private boolean isBadComment;

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

    public Long getId(){return id;}
    public void setId(Long id){this.id = id;}
    public String getContent(){return content;}
    public void setContent(String content){this.content = content;}
    public Long getOwnerId(){return ownerId;}
    public void setOwnerId(Long ownerId){this.ownerId = ownerId;}
    public Long getHomeReservationId(){return  homeReservationId;}
    public void setHomeReservationId(Long homeReservationId){this.homeReservationId = homeReservationId;}

}
