package com.ra7eeb.assessment.repositories;

import com.ra7eeb.assessment.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {

}
