package com.experimentality.Store.persistence.crud;

import com.experimentality.Store.persistence.entity.ImageEntity;
import org.springframework.data.repository.CrudRepository;

public interface ImageCrudRepository extends CrudRepository<ImageEntity,Integer> {
}
