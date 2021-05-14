package com.experimentality.Store.domain.service;

import com.experimentality.Store.domain.dto.SecurityUserDto;
import com.experimentality.Store.domain.dto.UserDto;
import com.experimentality.Store.domain.repository.UserDomainRepository;
import javassist.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceShould {

    @Autowired
    private UserDomainRepository userDomainRepository;

    @Test
    @Transactional
    public void createANewUser() {



        String name = "Juan Pepito";
        String address = "Cra 34 #10-25";
        String email = "loco10@yopmail.com";
        String password = "loco10";
        String message = "User Juan Pepito created succesfully";
        long phoneNumber = 8607302;

        UserService userService = new UserService(userDomainRepository);

        UserDto user = new UserDto();
        user.setName(name);
        user.setAddress(address);
        user.setPhoneNumber(phoneNumber);
        user.setEmail(email);
        user.setPassword(password);

        Map<String, Object> response = userService.newUser(user);

        assertEquals(message, response.get("Message"));
    }

    @Test
    public void findAnUserByItsId() throws NotFoundException {

        int usId = 1;

        UserService userService = new UserService(userDomainRepository);

        Map<String, Object> user = userService.getUserById(usId);

        assertEquals(usId, user.get("usId"));

    }

    @Test
    public void findAnUserByItsEmail() throws SecurityException {

        String email = "jblackheart@yopmail.com";

        UserService userService = new UserService(userDomainRepository);

        SecurityUserDto user = userService.getByEmail(email);

        assertEquals(email, user.getEmail());

    }
}