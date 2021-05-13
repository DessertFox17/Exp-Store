package com.experimentality.Store.web.controller;


import com.experimentality.Store.domain.dto.UserDto;
import com.experimentality.Store.domain.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/new")
    @Transactional
    @ApiOperation(value = "Creation of a new user", notes = "This endpoint creates a new user")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    public Map<String, Object> newUser(@Valid @RequestBody UserDto userPayload, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            throw new IllegalArgumentException("All or some mandatory fields are incomplete");
        }

        return userService.newUser(userPayload);
    }

    @GetMapping("/specific/{id}")
    @ApiOperation(value = "Get a specific user", notes = "This endpoint gets a user by its id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 403, message = "Forbiden"),
            @ApiResponse(code = 404, message = "Not found")
    })
    public Map<String, Object> findUserByID(@PathVariable("id") int id) throws NotFoundException {
        return userService.getUserById(id);
    }


}
