package com.springDataProject.springDataProject.repositories;

import com.springDataProject.springDataProject.entities.Officer;
import com.springDataProject.springDataProject.entities.Rank;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JdbcOfficerDaoTest {

    @Autowired
    JdbcOfficerDao jdbcOfficerDao;

    @Test
    void countOfficers() {
        Long count = jdbcOfficerDao.count();
        assertTrue(count > 0);
    }

    @Test
    void findAllOfficers() {
        List<Officer> officers = jdbcOfficerDao.findAll();
        assertFalse(officers.isEmpty());
        officers.forEach(System.out::println);
    }

    @Test
    void findOfficerById() {
        Optional<Officer> officer = jdbcOfficerDao.findById(3L);
        assertTrue(officer.isPresent());
        System.out.println(officer);
    }

    @Test
    void createNewOfficer() {
        Officer officer = new Officer(Rank.LIEUTENANT, "Nyota", "Uhuru");
        jdbcOfficerDao.save(officer);
        assertNotNull(officer.getId());
        System.out.println(officer);
    }
    @Test
    void deleteOfficer() {
        Officer officer = new Officer(Rank.LIEUTENANT, "Nyota", "Uhuru");
        jdbcOfficerDao.save(officer);
        assertEquals(jdbcOfficerDao.delete(officer.getId()), true);
        assertEquals(jdbcOfficerDao.delete(2L), true);
    }

}