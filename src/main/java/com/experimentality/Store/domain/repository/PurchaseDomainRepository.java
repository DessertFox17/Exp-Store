package com.experimentality.Store.domain.repository;

import com.experimentality.Store.persistence.entity.PurchaseEntity;
import java.util.List;
import java.util.Optional;

public interface PurchaseDomainRepository {
    List<PurchaseEntity> getByClient(int usId);
    List<PurchaseEntity> getByUserandStatus(int usId, int stId);
    Optional<PurchaseEntity> getById(int puId);
    PurchaseEntity newPurchase(PurchaseEntity purchaseEntity);
    PurchaseEntity updatePurchase(PurchaseEntity purchaseEntity);
}
