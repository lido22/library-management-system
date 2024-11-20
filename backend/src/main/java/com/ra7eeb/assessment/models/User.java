package com.ra7eeb.assessment.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "users")
@Inheritance(
        strategy = InheritanceType.TABLE_PER_CLASS
)
public class User implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name = "name")
    @NotEmpty
    String name;

    @Column(name = "email")
    @NotEmpty
    String email;

    @Column(name = "role")
    @NotEmpty
    String role;

    @OneToMany(mappedBy = "userId")
    private List<BorrowingHistory> borrowingHistories;
}
