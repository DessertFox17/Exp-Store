package com.experimentality.Store.web.controller;

import com.experimentality.Store.domain.dto.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc(addFilters = false)
public class UserControllerShould {

    @Autowired
    private MockMvc mockMvc;


    private ObjectMapper objectMapper = new ObjectMapper()
            .enable(SerializationFeature.INDENT_OUTPUT);

    @Test
    @Transactional
    public void createANewUser() throws Exception {

        UserDto user = new UserDto();
        user.setName("test");
        user.setAddress("test");
        user.setPhoneNumber(123);
        user.setEmail("test@test.com");
        user.setPassword("test");

        mockMvc.perform(post("/user/new").contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(user))).andExpect(status().isOk());

    }

    @Test
    @Transactional
    public void createANewUserException() throws Exception {

        UserDto user = new UserDto();

        mockMvc.perform(post("/user/new").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user))).andExpect(status().isBadRequest());

    }

    @Test
    public void getAnUserById() throws Exception {
        mockMvc.perform(get("/user/1")).andExpect(status().isOk());
    }

    @Test
    public void getAnUserByIdException() throws Exception {
        mockMvc.perform(get("/user/0")).andExpect(status().isNotFound());
    }

}