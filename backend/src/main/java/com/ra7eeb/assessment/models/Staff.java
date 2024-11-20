package com.ra7eeb.assessment.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "staff")
public class Staff extends User {
    @Column(name = "position")
    @NotEmpty
    String position;

    @Column(name = "salary")
    @NotNull
    int salary;


    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return super.equals(obj);
    }

    public int hashCode() {
        return super.hashCode();
    }
}
