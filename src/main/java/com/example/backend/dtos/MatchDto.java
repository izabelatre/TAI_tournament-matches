package com.example.backend.dtos;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class MatchDto {

    @JsonProperty("match_name")
    @JsonAlias("match_name")
    private String matchName;

    @JsonProperty("match_date")
    @JsonAlias("match_name")
    private LocalDate matchDate;

    public boolean hasInvalidAttributes() {
        return matchName == null || matchDate == null;
    }
}
