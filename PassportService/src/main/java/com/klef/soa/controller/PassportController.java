package com.klef.soa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import com.klef.soa.entity.Passport;
import com.klef.soa.service.PassportService;

@RestController
@RequestMapping("/passport")
@CrossOrigin("*")
public class PassportController {

    @Autowired
    private PassportService service;

    @Value("${server.port:2027}")
    private String port;

    // 1. Insert a new record
    @PostMapping
    public Passport insertPassport(@RequestBody Passport passport) {
        return service.insertPassport(passport);
    }

    // 2. Update an existing record
    @PutMapping("/{id}")
    public Passport updatePassport(@PathVariable Long id, @RequestBody Passport passport) {
        return service.updatePassport(id, passport);
    }

    // 3. Delete a record
    @DeleteMapping("/{id}")
    public String deletePassport(@PathVariable Long id) {
        return service.deletePassport(id);
    }

    // 4. Display all records
    @GetMapping({"", "/displayall"})
    public List<Passport> getAllPassports() {
        return service.getAllPassports();
    }

    // 5. Display a record by ID
    @GetMapping("/{id}")
    public Passport getPassportById(@PathVariable Long id) {
        return service.getPassportById(id);
    }

    // Derived Query: Find by Name
    @GetMapping("/name/{name}")
    public List<Passport> findByName(@PathVariable String name) {
        return service.findByName(name);
    }

    // Derived Query: Find by Type
    @GetMapping("/type/{type}")
    public List<Passport> findByType(@PathVariable String type) {
        return service.findByType(type);
    }

    // Instance endpoint for verification
    @GetMapping("/instance")
    public String instance() {
        return "Passport Service instance running on port: " + port;
    }
}
