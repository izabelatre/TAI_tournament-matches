package com.example.backend.controllers;

import com.example.backend.dtos.MatchDto;
import com.example.backend.entities.MatchEntity;
import com.example.backend.services.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/{tournamentId}/matches")
public class MatchController {
    @Autowired
    MatchService matchService;

    @GetMapping()
    public ResponseEntity<List<MatchEntity>> getAllMatches(@PathVariable Long tournamentId){
        return new ResponseEntity<>(matchService.getAllMatches(tournamentId), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity addNewMatch (@PathVariable Long tournamentId, @RequestBody MatchDto matchDto) {
        matchService.addMatch(tournamentId, matchDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/{matchId}")
    public ResponseEntity updateMatch (@PathVariable Long matchId, @RequestBody MatchDto matchDto) {
        matchService.updateMatch(matchId, matchDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{matchId}")
    public  ResponseEntity deleteMatch(@PathVariable Long matchId){
        matchService.deleteMatch(matchId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
