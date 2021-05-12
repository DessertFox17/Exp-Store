package com.experimentality.Store.persistence.repository;

import com.experimentality.Store.domain.repository.SubcategoryDomainRepository;
import com.experimentality.Store.persistence.crud.SubcategoryCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SubcategoryPersistenceRepository implements SubcategoryDomainRepository {
    @Autowired
    private SubcategoryCrudRepository subcategoryCrudRepository;
}
