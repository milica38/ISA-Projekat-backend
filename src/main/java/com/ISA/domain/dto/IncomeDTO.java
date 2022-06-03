package com.ISA.domain.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class IncomeDTO {
        private long id;
        private double percentage;

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public double getPercentage(){return percentage;}
        public void setPercentage(double percentage){this.percentage = percentage;}

}
