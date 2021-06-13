package com.example.backend.repositiories;

import com.example.backend.entities.TournamentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TournamentRepository extends JpaRepository <TournamentEntity, Long > {
}
