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
        p1.setType("Diplomatic");
        p1.setStatus("Active");
        p1.setLocation("Hyderabad");
        savedPassport = repository.save(p1);

        Passport p2 = new Passport();
        p2.setName("Alice Wonder");
        p2.setType("Regular");
        p2.setStatus("Approved");
        p2.setLocation("London");
        repository.save(p2);
    }

    // Test 1 - Find All: Call findAll(). Verify returned list is not null and validate records
    @Test
    @DisplayName("Test 1 - Find All Passports")
    void testFindAll() {
        List<Passport> list = repository.findAll();
        assertNotNull(list);
        assertFalse(list.isEmpty());
        assertEquals(2, list.size());
        System.out.println("Test 1 Passed: Total passports retrieved = " + list.size());
    }

    // Test 2 - Find by ID: Call findById() with existing ID and non-existing ID
    @Test
    @DisplayName("Test 2 - Find Passport by ID")
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

    // Test 3 - Find by Derived Query: Test findByName()
    @Test
    @DisplayName("Test 3 - Find by Derived Query (findByName)")
    void testFindByDerivedQuery() {
        List<Passport> results = repository.findByName("Shashir Prem");
        assertNotNull(results);
        assertFalse(results.isEmpty());
        assertEquals("Shashir Prem", results.get(0).getName());
        System.out.println("Test 3 Passed: Found " + results.size() + " record(s) with name Shashir Prem.");
    }

    // Test 4 - Count: Call count() and verify record count
    @Test
    @DisplayName("Test 4 - Count Passports")
    void testCount() {
        long count = repository.count();
        assertEquals(2, count);
        System.out.println("Test 4 Passed: Exact record count verified = " + count);
    }

    // Test 5 - Entity Details / Property-Based Result: Verify required properties and category method (findByType)
    @Test
    @DisplayName("Test 5 - Entity Details & Property-Based Query (findByType)")
    void testEntityDetailsAndPropertyBasedResult() {
        Optional<Passport> optional = repository.findById(savedPassport.getPassportId());
        assertTrue(optional.isPresent());
        Passport p = optional.get();

        assertNotNull(p.getName());
        assertNotNull(p.getType());
        assertNotNull(p.getStatus());
        assertNotNull(p.getLocation());

        // Category/property query: findByType
        List<Passport> diplomatic = repository.findByType("Diplomatic");
        assertNotNull(diplomatic);
        assertFalse(diplomatic.isEmpty());
        assertEquals("Diplomatic", diplomatic.get(0).getType());
        System.out.println("Test 5 Passed: Entity properties verified and category query matched.");
    }
}
