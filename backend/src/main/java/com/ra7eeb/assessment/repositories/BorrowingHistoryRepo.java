package com.ra7eeb.assessment.repositories;

import com.ra7eeb.assessment.models.BorrowingHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BorrowingHistoryRepo extends JpaRepository<BorrowingHistory, Long> {

    List<BorrowingHistory> findByUserId(Long userId);
    List<BorrowingHistory> findByUserIdAndBookId(Long userId, Long bookId);
}
