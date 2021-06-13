package com.example.backend.controllers;

import com.example.backend.dtos.TournamentDto;
import com.example.backend.entities.TournamentEntity;
import com.example.backend.services.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")


public class TournamentController {

    @Autowired
    TournamentService tournamentService;

    @GetMapping("/tournaments")
    public ResponseEntity <List<TournamentEntity>> getAllTournaments(){
        return new ResponseEntity<>(tournamentService.getAllTournaments(), HttpStatus.OK);
    }

    @PostMapping("tournaments")
    public ResponseEntity addNewTournament (@RequestBody TournamentDto tournamentDto) {
        tournamentService.addTournament(tournamentDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @PatchMapping("/tournaments/{tournamentId}")
    public ResponseEntity updateTournament(@PathVariable Long tournamentId, @RequestBody TournamentDto tournamentDto) {
        tournamentService.updateTournament(tournamentId, tournamentDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/tournaments/{tournamentId}")
    public  ResponseEntity deleteTournament (@PathVariable Long tournamentId){
        tournamentService.deleteTournament(tournamentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
