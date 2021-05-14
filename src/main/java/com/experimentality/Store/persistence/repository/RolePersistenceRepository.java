package com.experimentality.Store.persistence.repository;

import com.experimentality.Store.domain.repository.RoleDomainRepository;
import com.experimentality.Store.persistence.crud.RoleCrudRepository;
import com.experimentality.Store.persistence.entity.RoleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class RolePersistenceRepository implements RoleDomainRepository {

    @Autowired
    private RoleCrudRepository roleCrudRepository;

    @Override
    public Optional<RoleEntity> getByRoleId(int roId) {
        return roleCrudRepository.findById(roId);
    }
}
