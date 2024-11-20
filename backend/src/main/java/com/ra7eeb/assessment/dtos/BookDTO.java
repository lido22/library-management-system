package com.ra7eeb.assessment.dtos;

import com.ra7eeb.assessment.models.Book;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

@Data
public class BookDTO implements Serializable {
    @NotEmpty
    String title;
    @NotEmpty
    String author;
    @NotEmpty
    String isbn;
    @NotNull
    int availableCopies;
    public Book toEntity() {
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setIsbn(isbn);
        book.setAvailableCopies(availableCopies);
        return book;
    }
}
