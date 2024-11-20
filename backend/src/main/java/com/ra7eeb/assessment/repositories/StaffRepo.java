package com.ra7eeb.assessment.repositories;

import com.ra7eeb.assessment.models.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepo extends JpaRepository<Staff, Long> {
}
