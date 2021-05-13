package com.experimentality.Store.persistence.repository;

import com.experimentality.Store.domain.repository.UserDomainRepository;
import com.experimentality.Store.persistence.crud.UserCrudRepository;
import com.experimentality.Store.persistence.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public class UserPersistenceRepository implements UserDomainRepository {

    @Autowired
    private UserCrudRepository userCrudRepository;

    @Override
    public UserEntity newUser(UserEntity userPayload) {
        return userCrudRepository.save(userPayload);
    }

    @Override
    public Optional<UserEntity> findUserById(int usId) {
        return userCrudRepository.findById(usId);
    }

    @Override
    public Optional<UserEntity> getByEmail(String email) {
        return userCrudRepository.findByEmail(email);
    }
}
