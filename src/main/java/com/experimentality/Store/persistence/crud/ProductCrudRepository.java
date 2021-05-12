package com.experimentality.Store.persistence.crud;

import com.experimentality.Store.persistence.entity.ProductEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductCrudRepository extends CrudRepository<ProductEntity,Integer> {

    @Query("select p from ProductEntity p" +
            " join p.subcategory s" +
            " join s.category c" +
            " where lower(p.name) like %?1%" +
            " or lower(s.name) like %?1%" +
            " or lower(c.name) like %?1%")
    List<ProductEntity> dynamicFilter(String result, PageRequest sort);

    @Query("select count (p) from ProductEntity p" +
            " join p.subcategory s" +
            " join s.category c" +
            " where lower(p.name) like %?1%" +
            " or lower(s.name) like %?1%" +
            " or lower(c.name) like %?1%")
    Long dynamicFilterCounter(String result);

    Optional<ProductEntity> findByName(String name);
}
