package com.experimentality.Store.persistence.crud;

import com.experimentality.Store.persistence.entity.PurchaseEntity;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface PurchaseCrudRepository extends CrudRepository<PurchaseEntity, Integer> {
    List<PurchaseEntity> findByUsId(int usId);
    List<PurchaseEntity> findByUsIdAndStId(int usId, int stId);
}
