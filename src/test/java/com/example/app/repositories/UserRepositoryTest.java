package com.example.app.repositories;

import com.example.app.model.User;
import com.example.app.room_reservation.TestFixtures;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.PersistenceException;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@ActiveProfiles("test-db")
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    void getByUsername() {

        User user = TestFixtures.user();
        testEntityManager.persistAndFlush(user);
        testEntityManager.clear();

        User retrievedUser = userRepository.getByUsername(user.getUsername());
        assertNotNull(retrievedUser);
    }
    @Test
    public void givenTwoUsersWithSameUsername_whenSaved_thenPersistenceException() {

        User user1 = TestFixtures.user();
        testEntityManager.persist(user1);

        User user2 = TestFixtures.user();

        assertThrows(DataIntegrityViolationException.class, () -> {
            userRepository.saveAndFlush(user2);
        });
    }
}