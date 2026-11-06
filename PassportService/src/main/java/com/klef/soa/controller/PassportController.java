package com.klef.soa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.klef.soa.entity.Passport;
import com.klef.soa.service.PassportService;

@RestController
@RequestMapping("/passport")
@CrossOrigin("*")
public class PassportController {

    @Autowired
    private PassportService service;

    @PostMapping
    public Passport insertPassport(@RequestBody Passport passport) {
        return service.insertPassport(passport);
    }

    @PutMapping("/{id}")
    public Passport updatePassport(@PathVariable Long id,
                                   @RequestBody Passport passport) {
        return service.updatePassport(id, passport);
    }

    @DeleteMapping("/{id}")
    public String deletePassport(@PathVariable Long id) {
        return service.deletePassport(id);
    }

    @GetMapping
    public List<Passport> getAllPassports() {
        return service.getAllPassports();
    }

    @GetMapping("/{id}")
    public Passport getPassportById(@PathVariable Long id) {
        return service.getPassportById(id);
    }
    
    @GetMapping("/name/{name}")
    public List<Passport> findByName(@PathVariable String name) {
        return service.findByName(name);
    }

    @GetMapping("/type/{type}")
    public List<Passport> findByType(@PathVariable String type) {
        return service.findByType(type);
    }

}