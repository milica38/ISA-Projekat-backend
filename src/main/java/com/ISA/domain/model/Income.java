package com.ISA.domain.model;

import javax.persistence.*;

@Entity
@Table(name = "income")
public class Income {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private long id;
    private double percentage;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public double getPercentage(){return percentage;}
    public void setPercentage(double percentage){this.percentage = percentage;}
}
