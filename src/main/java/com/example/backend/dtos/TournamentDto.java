package com.example.backend.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class TournamentDto {

    @JsonProperty("tournament_name")
    private String tournamentName;

    public boolean hasInvalidAttributes() {
        return (tournamentName == null || tournamentName.length()<=0);
    }
}
