package com.experimentality.Store.domain.service;

import com.experimentality.Store.domain.dto.RoleDto;
import com.experimentality.Store.domain.repository.RoleDomainRepository;
import javassist.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@RunWith(SpringRunner.class)
@SpringBootTest
public class RoleServiceShould {

    @Autowired
    private RoleDomainRepository roleDomainRepository;

    @Test
    public void getARoleByItsId() throws NotFoundException {
        int roId = 1;
        String name = "Super";

        RoleService role = new RoleService(roleDomainRepository);

        RoleDto roleDto = role.getRoleById(roId);

        assertEquals(roId,roleDto.getRoId());
        assertEquals(name,roleDto.getName());

    }

    @Test
    public void lauchAnNotFoundException() throws NotFoundException {
        int roId = 5;
        String name = "Super";

        RoleService role = new RoleService(roleDomainRepository);

        assertThrows(NotFoundException.class, () -> role.getRoleById(roId));

    }
}