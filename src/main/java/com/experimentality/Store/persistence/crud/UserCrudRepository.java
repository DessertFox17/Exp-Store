package com.experimentality.Store.persistence.crud;

import com.experimentality.Store.persistence.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserCrudRepository extends CrudRepository<UserEntity,Integer> {
    Optional<UserEntity> findByEmail(String email);
}
