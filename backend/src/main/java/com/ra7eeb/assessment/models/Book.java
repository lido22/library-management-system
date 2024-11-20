package com.ra7eeb.assessment.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "books")
public class Book implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name = "title")
    @NotEmpty
    String title;

    @Column(name = "author")
    @NotEmpty
    String author;

    @Column(name = "isbn")
    @NotEmpty
    String isbn;

    @Column(name = "copies_available")
    @NotNull
    int availableCopies;

    @OneToMany(mappedBy = "bookId")
    private List<BorrowingHistory> borrowingHistories;
}
