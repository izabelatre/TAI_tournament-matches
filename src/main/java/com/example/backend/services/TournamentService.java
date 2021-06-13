package com.example.backend.services;

import com.example.backend.dtos.TournamentDto;
import com.example.backend.entities.TournamentEntity;
import com.example.backend.repositiories.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TournamentService {
    private final TournamentRepository tournamentRepository;

    @Autowired
    public TournamentService(TournamentRepository tournamentRepository) {
        this.tournamentRepository = tournamentRepository;
    }

    public List <TournamentEntity > getAllTournaments() {
        return tournamentRepository.findAll();
    }

    public TournamentEntity getTournament (Long tournamentId) {
        return findTournament(tournamentId);
    }

    public TournamentEntity addTournament(TournamentDto tournamentDto) {
        if (!tournamentDto.hasInvalidAttributes()) {
            TournamentEntity tournamentEntity = new TournamentEntity();
            tournamentEntity.setTournamentName(tournamentDto.getTournamentName());
            tournamentRepository.save(tournamentEntity);
            return tournamentEntity;
        }
        throw new RuntimeException("Bad request data");
    }

    public TournamentEntity updateTournament (Long tournamentId, TournamentDto tournamentDto) {
        TournamentEntity tournamentEntity = findTournament(tournamentId);
        tournamentEntity.setTournamentName(tournamentDto.getTournamentName());
        tournamentRepository.save(tournamentEntity);
        return tournamentEntity;
    }

    public void deleteTournament (Long tournamentId) {
        TournamentEntity tournamentEntity = findTournament(tournamentId);
        tournamentRepository.delete(tournamentEntity);
    }

    private TournamentEntity findTournament (Long tournamentId) {
        return tournamentRepository
                .findById(tournamentId)
                .orElseThrow(() -> new RuntimeException("Could not find contact with id " + tournamentId));
    }
}
