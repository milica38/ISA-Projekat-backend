package com.ISA.domain.model;

import javax.persistence.*;

@Entity
@Table(name = "adventure_reviews")
public class AdventureReviews {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String content;
    private Long instructorId;
    @OneToOne
    private AdventureReservation adventureReservation;
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
    public Long getInstructorId(){return instructorId;}
    public void setInstructorId(Long instructorId){this.instructorId = instructorId;}
    public AdventureReservation getAdventureReservation(){return  adventureReservation;}
    public void setAdventureReservation(AdventureReservation adventureReservation){this.adventureReservation = adventureReservation;}

}
