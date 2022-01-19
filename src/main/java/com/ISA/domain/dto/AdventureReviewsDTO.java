package com.ISA.domain.dto;

import com.ISA.domain.model.AdventureReservation;


public class AdventureReviewsDTO {
    private Long id;
    private String content;
    private Long instructorId;
    private Long adventureReservationId;
    private boolean isAppear;
    private boolean isBadComment;

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

    public Long getId(){return id;}
    public void setId(Long id){this.id = id;}
    public String getContent(){return content;}
    public void setContent(String content){this.content = content;}
    public Long getInstructorId(){return instructorId;}
    public void setInstructorId(Long instructorId){this.instructorId = instructorId;}
    public Long getAdventureReservationId(){return  adventureReservationId;}
    public void setAdventureReservationId(Long adventureReservationId){this.adventureReservationId = adventureReservationId;}
}
