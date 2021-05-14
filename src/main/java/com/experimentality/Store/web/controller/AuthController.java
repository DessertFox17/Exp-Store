package com.experimentality.Store.web.controller;

import com.experimentality.Store.domain.dto.AuthenticationRequest;
import com.experimentality.Store.domain.dto.AuthenticationResponse;
import com.experimentality.Store.domain.service.StoreUserDetailsService;
import com.experimentality.Store.web.security.JWTUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
    @ApiOperation(value = "Loggin to authenticate", notes = "This returns the JWT fot 10 hours if the user is registered ")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unathorized")
    })
    public AuthenticationResponse createToken(@Valid @RequestBody AuthenticationRequest request, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            throw new IllegalArgumentException("All or some mandatory fields are incomplete");
        }

        UserDetails userDetails = storeUserDetailsService.userAndPasswordValidaion(request.getUsername(), request.getPassword());
        String jwt = jwtUtil.generateToken(userDetails);

        return new AuthenticationResponse(jwt);
    }
}
