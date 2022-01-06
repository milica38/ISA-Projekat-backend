package com.ISA.domain.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "adventure_free_terms")
public class AdventureFreeTerms {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private Date startDate;
    private Date endDate;
    @ManyToOne
    private AdventureProfile adventureProfile;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Date getStartDate() { return startDate; }
    public void setStartDate(Date startDate) { this.startDate = startDate; }
    public Date getEndDate() { return  endDate; }
    public void  setEndDate(Date endDate) { this.endDate = endDate; }

    public AdventureProfile getAdventureProfile() {
        return adventureProfile;
    }

    public void setAdventureProfile(AdventureProfile adventureProfile) {
        this.adventureProfile = adventureProfile;
    }
}
