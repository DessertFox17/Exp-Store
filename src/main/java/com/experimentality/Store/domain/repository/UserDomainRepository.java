package com.experimentality.Store.domain.repository;

import com.experimentality.Store.persistence.entity.UserEntity;

import java.util.Optional;

public interface UserDomainRepository {
    UserEntity newUser(UserEntity userPayload);
    Optional<UserEntity> findUserById(int usId);
    Optional<UserEntity> getByEmail(String email);
}
