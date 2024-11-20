package com.ra7eeb.assessment.controllers;

import com.ra7eeb.assessment.dtos.BookDTO;
import com.ra7eeb.assessment.dtos.SearchRequestDTO;
import com.ra7eeb.assessment.dtos.UserDTO;
import com.ra7eeb.assessment.models.Book;
import com.ra7eeb.assessment.models.BorrowingHistory;
import com.ra7eeb.assessment.models.Staff;
import com.ra7eeb.assessment.models.User;
import com.ra7eeb.assessment.services.LibraryService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/library")
public class LibraryController {
    private final LibraryService libraryService;

    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @PostMapping("/users")
    public User addUser(@Valid  @RequestBody UserDTO user) {
        return libraryService.addUser(user);
    }

    @PostMapping("/books")
    public Book addBook(@Valid @RequestBody BookDTO book) {
        return libraryService.addBook(book);
    }
    @GetMapping("/books")
    public List<Book> getBooks(@Valid @RequestBody SearchRequestDTO searchRequest) {
        return libraryService.getBooks(searchRequest.getQuery());
    }

    @PostMapping("/borrow")
    public BorrowingHistory borrowBook(@Valid @RequestBody BorrowingHistory borrowingHistory) {
        return libraryService.borrowBook(borrowingHistory);
    }

    @GetMapping("/borrowing/history/{userId}")
    public List<BorrowingHistory> getBorrowingHistory(@PathVariable Long userId) {
        return libraryService.getBorrowingHistory(userId);
    }

    //helpers
    @GetMapping("/users")
    public List<User> getusers() {
        return libraryService.getUsers();
    }

    @GetMapping("/borrowing/history")
    public List<BorrowingHistory> getBorrowingHistory() {
        return libraryService.getBorrowingHistories();
    }

    @GetMapping("/staff")
    public List<Staff> getStaff() {
        return libraryService.getStaffs();
    }
}
