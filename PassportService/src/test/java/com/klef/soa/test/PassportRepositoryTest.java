package com.klef.soa.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import com.klef.soa.entity.Passport;
import com.klef.soa.repository.PassportRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PassportRepositoryTest {

    @Autowired
    private PassportRepository repository;

    private Passport savedPassport;

    @BeforeEach
    void setUp() {
        repository.deleteAll();

        Passport p1 = new Passport();
        p1.setName("Shashir Prem");
        p1.setType("tatkal");
        p1.setStatus("Active");
        p1.setLocation("Hyderabad");
        savedPassport = repository.save(p1);

        Passport p2 = new Passport();
        p2.setName("hero");
        p2.setType("Regular");
        p2.setStatus("Approved");
        p2.setLocation("London");
        repository.save(p2);
    }

        @Test
    void testFindAll() {
        List<Passport> list = repository.findAll();
        assertNotNull(list);
        assertFalse(list.isEmpty());
        assertEquals(2, list.size());
        System.out.println("Test 1 Passed: Total passports retrieved = " + list.size());
    }

    @Test
    void testFindById() {
        // Test existing ID
        Optional<Passport> existing = repository.findById(savedPassport.getPassportId());
        assertTrue(existing.isPresent());
        assertEquals(savedPassport.getPassportId(), existing.get().getPassportId());

        // Test non-existing ID
        Optional<Passport> nonExisting = repository.findById(999999L);
        assertFalse(nonExisting.isPresent());
        System.out.println("Test 2 Passed: Find by existing and non-existing ID verified.");
    }


    @Test
    void testFindByDerivedQuery() {
        List<Passport> results = repository.findByName("Shashir Prem");
        assertNotNull(results);
        assertFalse(results.isEmpty());
        assertEquals("Shashir Prem", results.get(0).getName());
        System.out.println("Test 3 Passed: Found " + results.size() + " record(s) with name Shashir Prem.");
    }


    @Test
    void testCount() {
        long count = repository.count();
        assertEquals(2, count);
        System.out.println("Test 4 Passed: Exact record count verified = " + count);
    }


    @Test
    void testEntityDetailsAndPropertyBasedResult() {
        Optional<Passport> optional = repository.findById(savedPassport.getPassportId());
        assertTrue(optional.isPresent());
        Passport p = optional.get();

        assertNotNull(p.getName());
        assertNotNull(p.getType());
        assertNotNull(p.getStatus());
        assertNotNull(p.getLocation());

    }
}
