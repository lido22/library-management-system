package com.ra7eeb.assessment.services;

import com.ra7eeb.assessment.dtos.BookDTO;
import com.ra7eeb.assessment.dtos.UserDTO;
import com.ra7eeb.assessment.exceptions.BadRequestException;
import com.ra7eeb.assessment.exceptions.ForbiddenException;
import com.ra7eeb.assessment.exceptions.ResourceNotFoundException;
import com.ra7eeb.assessment.models.Book;
import com.ra7eeb.assessment.models.BorrowingHistory;
import com.ra7eeb.assessment.models.Staff;
import com.ra7eeb.assessment.models.User;
import com.ra7eeb.assessment.repositories.BookRepo;
import com.ra7eeb.assessment.repositories.BorrowingHistoryRepo;
import com.ra7eeb.assessment.repositories.StaffRepo;
import com.ra7eeb.assessment.repositories.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibraryService {
    private final UserRepo userRepo;
    private final StaffRepo staffRepo;
    private final BorrowingHistoryRepo borrowingHistoryRepo;
    private final BookRepo bookRepo;

    public LibraryService(UserRepo userRepo, StaffRepo staffRepo, BorrowingHistoryRepo borrowingHistoryRepo, BookRepo bookRepo) {
        this.userRepo = userRepo;
        this.staffRepo = staffRepo;
        this.borrowingHistoryRepo = borrowingHistoryRepo;
        this.bookRepo = bookRepo;
    }

    public User addUser(UserDTO user) {
        return userRepo.save(user.toEntity());
    }

    public List<Book> getBooks(String query) {
        return bookRepo.findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(query, query);
    }

    public Book addBook(BookDTO book) {
        return bookRepo.save(book.toEntity());
    }

    public BorrowingHistory borrowBook(BorrowingHistory borrowingRequest) {

        //check if return date is after the borrowing date
        if(!borrowingRequest.getBorrowingDate().before(borrowingRequest.getReturnDate())){
            throw new BadRequestException("bad request, borrowing date should be before the return date");
        }

        //checking if the book and the user are really available
        Book requestedBook = bookRepo.findById(borrowingRequest.getBookId()).orElseThrow(
                () -> new BadRequestException("bad request, invalid Book id")
        );
        User borrower = userRepo.findById(borrowingRequest.getUserId()).orElseThrow(
                () -> new BadRequestException("bad request, invalid User id")
        );

        //checking if the user already borrowed the book
        if(borrowingHistoryRepo.findByUserIdAndBookId(borrower.getId(), requestedBook.getId()).size() > 0){
            throw new ForbiddenException("bad request, user already borrowed this book");
        }
        
        //checking if the book is available
        if (requestedBook.getAvailableCopies() <= 0) {
            throw new ResourceNotFoundException("No available copies for this book");
        }
        requestedBook.setAvailableCopies(requestedBook.getAvailableCopies() - 1);

        bookRepo.save(requestedBook);
        return borrowingHistoryRepo.save(borrowingRequest);
    }


    public List<BorrowingHistory> getBorrowingHistory(Long userId) {
        return borrowingHistoryRepo.findByUserId(userId);
    }

    public List<User> getUsers() {
        return userRepo.findAll();
    }

    public List<BorrowingHistory> getBorrowingHistories() {
        return borrowingHistoryRepo.findAll();
    }

    public List<Staff> getStaffs() {
        return staffRepo.findAll();
    }

}
