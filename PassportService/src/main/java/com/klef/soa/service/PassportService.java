package com.klef.soa.service;

import java.util.List;

import com.klef.soa.entity.Passport;

public interface PassportService {

    Passport insertPassport(Passport passport);
    Passport updatePassport(Long id, Passport passport);
    String deletePassport(Long id);
    List<Passport> getAllPassports();
    Passport getPassportById(Long id);
    List<Passport> findByName(String name);
    List<Passport> findByType(String type);

}