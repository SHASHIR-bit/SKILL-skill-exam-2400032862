package com.klef.soa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.soa.entity.Passport;
import com.klef.soa.repository.PassportRepository;

@Service
public class PassportServiceImpl implements PassportService {

    @Autowired
    private PassportRepository repository;

    @Override
    public Passport insertPassport(Passport passport) {
        return repository.save(passport);
    }

    @Override
    public Passport updatePassport(Long id, Passport passport) {

        Passport existing = repository.findById(id).orElse(null);

        if (existing != null) {
            existing.setName(passport.getName());
            existing.setType(passport.getType());
            existing.setStatus(passport.getStatus());
            existing.setLocation(passport.getLocation());

            return repository.save(existing);
        }

        return null;
    }

    @Override
    public String deletePassport(Long id) {

        if (repository.existsById(id)) {
            repository.deleteById(id);
            return "Passport Deleted Successfully";
        }

        return "Passport Not Found";
    }

    @Override
    public List<Passport> getAllPassports() {
        return repository.findAll();
    }

    @Override
    public Passport getPassportById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Passport> findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public List<Passport> findByType(String type) {
        return repository.findByType(type);
    }

}