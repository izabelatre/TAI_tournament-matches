package com.example.backend.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "matches")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class MatchEntity {
    @Id
    @Column(name = "match_id", nullable = false, updatable = false)
    @JsonProperty("match_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long matchId;

    @Column(name = "match_name", nullable = false)
    @JsonProperty("match_name")
    private String matchName;

    @Column(name = "match_date", nullable = false)
    @JsonProperty("match_date")
    private LocalDate matchDate;

    @ToString.Exclude
    @JsonBackReference
    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.DETACH, CascadeType.PERSIST})
    @JoinColumn(name = "tournament_id", nullable = false)
    private TournamentEntity tournament;
}
