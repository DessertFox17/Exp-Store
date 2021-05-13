package com.experimentality.Store.persistence.repository;

import com.experimentality.Store.domain.repository.PurchaseDomainRepository;
import com.experimentality.Store.persistence.crud.PurchaseCrudRepository;
import com.experimentality.Store.persistence.entity.PurchaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PurchasePersistenceRepository implements PurchaseDomainRepository {


    @Autowired
    private PurchaseCrudRepository purchaseCrudRepository;

    @Override
    public List<PurchaseEntity> getByClient(int usId) {
        return purchaseCrudRepository.findByUsId(usId);
    }

    @Override
    public List<PurchaseEntity> getByUserandStatus(int usId, int stId) {
        return purchaseCrudRepository.findByUsIdAndStId(usId,stId);
    }

    @Override
    public Optional<PurchaseEntity> getById(int puId) {
        return purchaseCrudRepository.findById(puId);
    }

    @Override
    public PurchaseEntity newPurchase(PurchaseEntity purchaseEntity) {
        return purchaseCrudRepository.save(purchaseEntity);
    }

    @Override
    public PurchaseEntity updatePurchase(PurchaseEntity purchaseEntity) {
        return purchaseCrudRepository.save(purchaseEntity);
    }
}
