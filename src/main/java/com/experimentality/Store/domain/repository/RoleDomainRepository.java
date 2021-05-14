package com.experimentality.Store.domain.repository;

import com.experimentality.Store.persistence.entity.RoleEntity;

import java.util.Optional;

public interface RoleDomainRepository {
    Optional<RoleEntity> getByRoleId(int roId);
}
