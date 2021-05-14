package com.experimentality.Store.web.controller;

import com.experimentality.Store.domain.dto.AuthenticationRequest;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc(addFilters = false)
public class AuthControllerShould {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper()
            .enable(SerializationFeature.INDENT_OUTPUT);

    @Test
    public void authenticateAnUserBadRequestException() throws Exception {

        AuthenticationRequest request = new AuthenticationRequest();

        mockMvc.perform(post("/auth").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))).andExpect(status().isBadRequest());
    }

    @Test
    public void authenticateAnUserUnauthorizedException() throws Exception {

        AuthenticationRequest request = new AuthenticationRequest();
        request.setUsername("jblackheart@yopmail.com");
        request.setPassword("test");

        mockMvc.perform(post("/auth").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))).andExpect(status().isUnauthorized());
    }

    @Test
    public void authenticateAnUser() throws Exception {

        AuthenticationRequest request = new AuthenticationRequest();
        request.setUsername("jblackheart@yopmail.com");
        request.setPassword("holahola");

        mockMvc.perform(post("/auth").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))).andExpect(status().isOk());
    }


}