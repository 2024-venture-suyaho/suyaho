package com.venture.suyaho.repository;

import java.util.Optional;

import com.venture.suyaho.dto.UserDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.venture.suyaho.model.User;
import com.venture.suyaho.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindByUserEmailAndUserPwd() {
        // Given
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("les1234@syuin.ac.kr");
        userDTO.setPassword("8888");

        // When
        Optional<User> optionalUser = userRepository.findByUserEmailAndUserPwd(userDTO.getEmail(), userDTO.getPassword());

        // Then
        assertNotNull(optionalUser);
        assertTrue(optionalUser.isPresent());
        User user = optionalUser.get();
        assertNotNull(user);
    }
}
