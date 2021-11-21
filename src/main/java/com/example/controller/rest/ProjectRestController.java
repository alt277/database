package com.example.controller.rest;

//import io.swagger.v3.oas.annotations.tags.Tag;

import com.example.persist.entity.Device;
import com.example.persist.entity.Project;
import com.example.persist.repo.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.controller.NotFoundException;


import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

//@Tag(name = "User resource API", description = "API to operate User resource ...")
//@CrossOrigin(origins = "http://localhost:63342")

@RequestMapping("/api/v1/projects")
@RestController
public class ProjectRestController {


    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectRestController(ProjectRepository projectRepository) {

        this.projectRepository = projectRepository;
    }

    @GetMapping(path = "/all", produces = "application/json")
    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    @GetMapping(path = "/{id}/id", produces = "application/json")
    public Project findById(@PathVariable("id") int id) {
        return projectRepository.findById(id)
                .orElseThrow(NotFoundException::new);
    }
    @GetMapping(path = "/{project}/project", produces = "application/json")
    public List<Device> findAllDevicesByProject(@PathVariable("project") String project) {
        return projectRepository.findDevicesByProject(project);

    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public Project createProject(@RequestBody Project project) {

        projectRepository.save(project);
        return project;
    }


    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Project updateProject(@RequestBody Project project) {

     projectRepository.save(project);
        return project;

    }

    @DeleteMapping(path = "/{id}", produces = "application/json")
    public void deleteById(@PathVariable("id") Integer id) {

        System.out.println("in deleteByID");
        projectRepository.deleteById(id);
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