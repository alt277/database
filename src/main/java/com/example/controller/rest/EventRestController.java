package com.example.controller.rest;

//import io.swagger.v3.oas.annotations.tags.Tag;

import com.example.persist.entity.Event;
import com.example.persist.repo.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.controller.NotFoundException;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;

//@Tag(name = "User resource API", description = "API to operate User resource ...")
//@CrossOrigin(origins = "http://localhost:63342")

@RequestMapping("/api/v1/events")
@RestController
public class EventRestController {


    private final EventRepository eventRepository;

    @Autowired
    public EventRestController( EventRepository eventRepository) {

        this.eventRepository = eventRepository;
    }

    @GetMapping(path = "/all", produces = "application/json")
    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    @GetMapping(path = "/{id}/id", produces = "application/json")
    public Optional findById(@PathVariable("id") int id) {
        return eventRepository.findById(id);

    }



    @PostMapping(consumes = "application/json", produces = "application/json")
    public Event createEvent(@RequestBody Event event) {

        eventRepository.save(event);
        return event;
    }


    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Event updateEvent(@RequestBody Event event) {

     eventRepository.save(event);
        return event;

    }

    @DeleteMapping(path = "/{id}", produces = "application/json")
    public void deleteById(@PathVariable("id") Integer id) {

        System.out.println("in deleteByID");
        eventRepository.deleteById(id);
    }




    @ExceptionHandler
    public ResponseEntity<String> illegalArgumentExceptionHandler(IllegalArgumentException ex) {
        return new ResponseEntity<>(ex.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<String> sqlIntegrityConstraintViolationExceptionHandler(SQLIntegrityConstraintViolationException ex) {
        return new ResponseEntity<>(ex.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}