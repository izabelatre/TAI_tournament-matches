package com.example.backend.services;

import com.example.backend.dtos.MatchDto;
import com.example.backend.dtos.MatchDtoRequest;
import com.example.backend.entities.MatchEntity;
import com.example.backend.entities.TournamentEntity;
import com.example.backend.repositiories.MatchRepository;
import com.example.backend.repositiories.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MatchService {

    private final TournamentRepository tournamentRepository;
    private final MatchRepository matchRepository;

    @Autowired
    public MatchService(TournamentRepository tournamentRepository, MatchRepository matchRepository) {
        this.tournamentRepository = tournamentRepository;
        this.matchRepository = matchRepository;
    }

    public List <MatchEntity> getAllMatches (Long tounamentId) {
        TournamentEntity tournamentEntity = findTournament(tounamentId);
        return new ArrayList <> (tournamentEntity.getMatches());
    }

    public MatchEntity getMatch (Long matchId) {
        return findMatch(matchId);
    }

    public MatchEntity addMatch (Long tournamentId, MatchDtoRequest matchDtoRequest) {
        if ((!matchDtoRequest.hasInvalidAttributes()) && !(tournamentId == null || tournamentId <= 0)) {
            MatchEntity match = new MatchEntity();
            match.setMatchName(matchDtoRequest.getMatchName());
            match.setMatchDate(matchDtoRequest.getMatchDate());
            match.setTournament(findTournament(tournamentId));
            matchRepository.save(match);
            return match;
        }
        throw new RuntimeException("Bad request data");
    }

    public MatchEntity updateMatch (Long matchId, MatchDto matchDto) {
        if (!matchDto.hasInvalidAttributes() && !(matchId == null || matchId <= 0)) {
            MatchEntity match  = findMatch (matchId);
            match.setMatchName(matchDto.getMatchName());
            match.setMatchDate(matchDto.getMatchDate());
            matchRepository.save(match);
            return match;
        }
        throw new RuntimeException("Bad request data");
    }

    public void deleteMatch (Long matchId) {
        MatchEntity match = findMatch (matchId);
        matchRepository.delete(match);
    }

    private TournamentEntity findTournament (Long tournamentId) {
        return tournamentRepository
                .findById(tournamentId)
                .orElseThrow(() -> new RuntimeException("Could not find tournament with id " + tournamentId));
    }

    private MatchEntity findMatch (Long matchId) {
        return matchRepository
                .findById(matchId)
                .orElseThrow(() -> new RuntimeException("Could not find match with id" + matchId));
    }
}
