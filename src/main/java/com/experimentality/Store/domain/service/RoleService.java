package com.experimentality.Store.domain.service;

import com.experimentality.Store.domain.dto.RoleDto;
import com.experimentality.Store.domain.repository.RoleDomainRepository;
import com.experimentality.Store.persistence.entity.RoleEntity;
import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleDomainRepository roleDomainRepository;


    public RoleDto getRoleById(int roId) throws NotFoundException {
        ModelMapper modelMapper = new ModelMapper();

        RoleEntity role = roleDomainRepository.getByRoleId(roId)
                .orElseThrow(() -> new NotFoundException(String.format("The role with id: %s does not exist",roId)));

        return modelMapper.map(role,RoleDto.class);
    }
}
