package com.experimentality.Store.persistence.repository;

import com.experimentality.Store.domain.repository.SubcategoryDomainRepository;
import com.experimentality.Store.persistence.crud.SubcategoryCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SubcategoryPersistenceRepository implements SubcategoryDomainRepository {
    @Autowired
    private SubcategoryCrudRepository subcategoryCrudRepository;

    @Override
    public List<String> smartFilter(String name) {
        return subcategoryCrudRepository.smartFilter(name);
    }
}
