package com.experimentality.Store.domain.service;

import com.experimentality.Store.domain.dto.SecurityUserDto;
import com.experimentality.Store.domain.dto.UserDto;
import com.experimentality.Store.domain.repository.UserDomainRepository;
import com.experimentality.Store.persistence.entity.UserEntity;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Service
public class UserService {

    @Autowired
    private final UserDomainRepository userDomainRepository;


    public Map<String, Object> newUser(UserDto userPayload) {

        int roId = 3;

        Map<String, Object> map = new HashMap<>();
        ModelMapper modelMapper = new ModelMapper();

        UserEntity userEntity = modelMapper.map(userPayload, UserEntity.class);

        userEntity.setRoId(roId);
        userEntity.setRegDate(LocalDateTime.now());

        userDomainRepository.newUser(userEntity);

        map.put("Message", "User "+userEntity.getName()+" created succesfully");
        return map;
    }

    public Map<String, Object> getUserById(int usId) throws NotFoundException {

        Map<String, Object> map = new HashMap<>();
        ModelMapper modelMapper = new ModelMapper();

        UserEntity pUser = userDomainRepository.findUserById(usId)
                .orElseThrow(() -> new NotFoundException(String.format("The user with id: %s does not exist", usId)));


        UserDto user = modelMapper.map(pUser, UserDto.class);

        map.put("usId", user.getUsId());
        map.put("name", user.getName());
        map.put("address", user.getAddress());
        map.put("phoneNumber", user.getPhoneNumber());
        map.put("email", user.getEmail());

        return map;
    }
    public SecurityUserDto getByEmail(String email) {

        ModelMapper modelMapper = new ModelMapper();

        UserEntity pUser = userDomainRepository.getByEmail(email)
                .orElseThrow(() -> new SecurityException("Incorrect username or password"));

        return modelMapper.map(pUser, SecurityUserDto.class);
    }
}
