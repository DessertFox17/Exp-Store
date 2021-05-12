package com.experimentality.Store.persistence.crud;

import com.experimentality.Store.persistence.entity.SubcategoryEntity;
import org.springframework.data.repository.CrudRepository;

public interface SubcategoryCrudRepository extends CrudRepository<SubcategoryEntity,Integer> {
}
