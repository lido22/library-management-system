package com.ra7eeb.assessment.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

@Data
public class SearchRequestDTO implements Serializable {

    @NotNull
    String query;
}
