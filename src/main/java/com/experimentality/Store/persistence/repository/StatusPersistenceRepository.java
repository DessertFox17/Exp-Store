package com.experimentality.Store.persistence.repository;

import com.experimentality.Store.domain.repository.StatusDomainRepository;
import com.experimentality.Store.persistence.crud.StatusCrudRepository;
import com.experimentality.Store.persistence.entity.StatusEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class StatusPersistenceRepository implements StatusDomainRepository {

    @Autowired
    private StatusCrudRepository statusCrudRepository;

    @Override
    public Optional<StatusEntity> findStatusById(int stId) {
        return statusCrudRepository.findById(stId);
    }
}
