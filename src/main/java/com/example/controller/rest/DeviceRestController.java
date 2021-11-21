package com.example.controller.rest;

import io.swagger.v3.oas.annotations.tags.Tag;

import com.example.persist.entity.Device;
import com.example.persist.repo.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;


@RequestMapping("/api/v1/devices")
@RestController
public class DeviceRestController {


    private final DeviceRepository deviceRepository;

    @Autowired
    public DeviceRestController(DeviceRepository deviceRepository) {

        this.deviceRepository = deviceRepository;
    }

    @GetMapping(path = "/all", produces = "application/json")
    public List<Device> findAll() {
        return deviceRepository.findAll();
    }


    @GetMapping(path = "/{id}/id", produces = "application/json")
    public Optional<Device> findById(@PathVariable("id") int id) {
        return deviceRepository.findById(id);

    }

    @GetMapping(path = "/{nunber}/number", produces = "application/json")
    public List<Device> findBySerialNumber(@PathVariable("number") String serialNumber) {
        return deviceRepository.findBySerialNumber(serialNumber);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public Device createDevice(@RequestBody Device device) {

        deviceRepository.save(device);
        return device;
    }


    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Device updateDevice(@RequestBody Device device) {

        deviceRepository.save(device);
        return device;

    }

    @DeleteMapping(path = "/{id}", produces = "application/json")
    public void deleteById(@PathVariable("id") Integer id) {

        System.out.println("in deleteByID");
       deviceRepository.deleteById(id);
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