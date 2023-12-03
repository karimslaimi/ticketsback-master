package com.tekup.ticketsproject.Controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.tekup.ticketsproject.DTO.TicketsDTO;
import com.tekup.ticketsproject.Entities.Tickets;
import com.tekup.ticketsproject.Repositories.TicketsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class TicketController {

    @Autowired
    TicketsRepository ticketsRepository;
    @Autowired
    ModelMapper modelMapper;

    @GetMapping("/tickets")
    public ResponseEntity<List<TicketsDTO>> getAllTutorials(@RequestParam(required = false) String title) {
        try {
            List<Tickets> ticketsDTOList = new ArrayList<Tickets>();

            if (title == null)
                ticketsDTOList.addAll(ticketsRepository.findAll());
            else
                ticketsDTOList.addAll(ticketsRepository.findByTitleContaining(title));

            if (ticketsDTOList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(ticketsDTOList.stream().map(x->modelMapper.map(x, TicketsDTO.class)).collect(Collectors.toList()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/tickets/{id}")
    public ResponseEntity<Tickets> getTutorialById(@PathVariable("id") long id) {
        Optional<Tickets> ticketData = ticketsRepository.findById(id);

        return ticketData.map(ticketsDTO -> new ResponseEntity<>(ticketsDTO, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/tickets")
    public ResponseEntity<Tickets> createTutorial(@RequestBody Tickets tutorial) {
        try {
            Tickets _tutorial = ticketsRepository
                    .save(new Tickets(tutorial.getId(), tutorial.getTitle(), tutorial.getDescription(), tutorial.getCreated_at(),tutorial.getClosed_at(),tutorial.getStatus(),tutorial.getType(),tutorial.getCreatedBy(),tutorial.getTreatedBy()));
            return new ResponseEntity<>(_tutorial, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/tickets/{id}")
    public ResponseEntity<Tickets> updateTutorial(@PathVariable("id") long id, @RequestBody Tickets tutorial) {
        Optional<Tickets> tutorialData = ticketsRepository.findById(id);

        if (tutorialData.isPresent()) {
            Tickets _tutorial = tutorialData.get();
            _tutorial.setTitle(tutorial.getTitle());
            _tutorial.setDescription(tutorial.getDescription());
            _tutorial.setStatus(tutorial.getStatus());
            return new ResponseEntity<>(ticketsRepository.save(_tutorial), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/tickets/{id}")
    public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
        try {
            ticketsRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/tickets")
    public ResponseEntity<HttpStatus> deleteAllTutorials() {
        try {
            ticketsRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


}