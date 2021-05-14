package com.experimentality.Store.persistence.crud;

import com.experimentality.Store.persistence.entity.RoleEntity;
import org.springframework.data.repository.CrudRepository;

public interface RoleCrudRepository extends CrudRepository<RoleEntity,Integer> {
}
