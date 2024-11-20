package com.ra7eeb.assessment.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table(name = "borrowing_history")
public class BorrowingHistory implements Serializable {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    Long id;

    @Column(name="book_id")
    @NotNull
    Long bookId;

    @Column(name="user_id")
    @NotNull
    Long userId;

    @Column(name="borrow_date")
    @NotNull
    Date borrowingDate;

    @Column(name="return_date")
    @NotNull
    Date returnDate;


}
