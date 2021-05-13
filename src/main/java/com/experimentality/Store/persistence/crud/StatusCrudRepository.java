package com.experimentality.Store.persistence.crud;

import com.experimentality.Store.persistence.entity.StatusEntity;
import org.springframework.data.repository.CrudRepository;

public interface StatusCrudRepository extends CrudRepository<StatusEntity, Integer> {
}
