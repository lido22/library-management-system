package com.ra7eeb.assessment.dtos;

import com.ra7eeb.assessment.models.User;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserDTO implements Serializable {
    @NotEmpty
    private String name;
    @NotEmpty
    private String email;
    @NotEmpty
    private String role;

    public User toEntity(){
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setRole(role);
        return user;
    }
}
