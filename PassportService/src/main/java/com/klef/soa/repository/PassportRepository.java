package com.klef.soa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.klef.soa.entity.Passport;

@Repository
public interface PassportRepository extends JpaRepository<Passport, Long> {

    List<Passport> findByName(String name);

    List<Passport> findByType(String type);

}