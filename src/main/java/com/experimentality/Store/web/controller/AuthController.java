package com.experimentality.Store.web.controller;

import com.experimentality.Store.domain.dto.AuthenticationRequest;
import com.experimentality.Store.domain.dto.AuthenticationResponse;
import com.experimentality.Store.domain.service.StoreUserDetailsService;
import com.experimentality.Store.web.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private StoreUserDetailsService storeUserDetailsService;

    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping
    public AuthenticationResponse createToken(@Valid @RequestBody AuthenticationRequest request, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            throw new IllegalArgumentException("all or some mandatory fields are incomplete");
        }

        UserDetails userDetails = storeUserDetailsService.userAndPasswordValidaion(request.getUsername(), request.getPassword());
        String jwt = jwtUtil.generateToken(userDetails);

        return new AuthenticationResponse(jwt);
    }
}
