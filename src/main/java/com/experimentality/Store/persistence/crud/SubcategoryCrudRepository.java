package com.experimentality.Store.persistence.crud;

import com.experimentality.Store.persistence.entity.SubcategoryEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubcategoryCrudRepository extends CrudRepository<SubcategoryEntity,Integer> {

    @Query(
            value = " SELECT s_name FROM subcategory s " +
                    " WHERE LOWER(s.s_name)  like %:name% " +
                    " UNION" +
                    " SELECT cat_name  FROM category c " +
                    " WHERE LOWER(c.cat_name)  like %:name% " +
                    " UNION" +
                    " SELECT pr_name FROM product p " +
                    " WHERE LOWER(p.pr_name) like %:name%"
            ,nativeQuery = true)
    List<String> smartFilter(@Param("name") String name);
}
