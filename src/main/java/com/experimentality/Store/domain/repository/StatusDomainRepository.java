package com.experimentality.Store.domain.repository;

import com.experimentality.Store.persistence.entity.StatusEntity;

import java.util.Optional;

public interface StatusDomainRepository {
    Optional<StatusEntity> findStatusById(int stId);
}
