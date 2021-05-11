package com.experimentality.Store.domain.service;

import com.experimentality.Store.domain.dto.UserDto;
import com.experimentality.Store.domain.repository.UserDomainRepository;
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
class UserServiceShould {

    @Autowired
    private UserDomainRepository userDomainRepository;

    @Test
    @Transactional
    void newUser() {

        String name = "Juan Pepito", address = "Cra 34 #10-25", email = "loco10@yopmail.com",
                password = "loco10", message = "User Juan Pepito created succesfully";
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


}