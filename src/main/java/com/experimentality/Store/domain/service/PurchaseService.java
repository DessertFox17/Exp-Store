package com.experimentality.Store.domain.service;

import com.experimentality.Store.domain.dto.InCartDto;
import com.experimentality.Store.domain.dto.NewPurchaseDto;
import com.experimentality.Store.domain.dto.ShowPurchaseDto;
import com.experimentality.Store.domain.dto.UpdatePurchaseStatusDto;
import com.experimentality.Store.domain.repository.ProductDomainRepository;
import com.experimentality.Store.domain.repository.PurchaseDomainRepository;
import com.experimentality.Store.domain.repository.StatusDomainRepository;
import com.experimentality.Store.domain.repository.UserDomainRepository;
import com.experimentality.Store.persistence.entity.PurchaseEntity;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@AllArgsConstructor
@Service
public class PurchaseService {

    @Autowired
    private final StatusDomainRepository statusDomainRepository;

    @Autowired
    private final PurchaseDomainRepository purchaseDomainRepository;

    @Autowired
    private final ProductDomainRepository productDomainRepository;

    @Autowired
    private final UserDomainRepository userDomainRepository;

    public Map<String, Object> getByClient(int usId) throws NotFoundException {

        Map<String, Object> map = new HashMap<>();
        ModelMapper modelMapper = new ModelMapper();
        List<ShowPurchaseDto> purchases = new ArrayList<>();

        userDomainRepository.findUserById(usId)
                .orElseThrow(() -> new NotFoundException(String.format("User with id: %s not found", usId)));

        List<PurchaseEntity> pPurchases = purchaseDomainRepository.getByClient(usId);

        pPurchases.forEach(purchaseEntity -> purchases.add(modelMapper.map(purchaseEntity, ShowPurchaseDto.class)));

        map.put("Purchases", purchases);

        return map;
    }

    public Map<String, Object> getByUserandStatus(int usId) throws NotFoundException {

        int stId = 1;

        Map<String, Object> map = new HashMap<>();
        ModelMapper modelMapper = new ModelMapper();
        List<InCartDto> purchases = new ArrayList<>();

        userDomainRepository.findUserById(usId)
                .orElseThrow(() -> new NotFoundException(String.format("User with id: %s not found", usId)));

        List<PurchaseEntity> pPurchases = purchaseDomainRepository.getByUserandStatus(usId, stId);
        pPurchases.forEach(purchaseEntity -> purchases.add(modelMapper.map(purchaseEntity, InCartDto.class)));

        map.put("Products in Cart", purchases);

        return map;
    }

    public Map<String, Object> updatePurchase(UpdatePurchaseStatusDto purchasePayload) throws NotFoundException {

        Map<String, Object> map = new HashMap<>();

        statusDomainRepository.findStatusById(purchasePayload.getStId())
                .orElseThrow(() -> new NotFoundException(String.format("Status with id: %s not found", purchasePayload.getStId())));

        PurchaseEntity purchase = purchaseDomainRepository.getById(purchasePayload.getPuId())
                .orElseThrow(() -> new NotFoundException(String.format("Purchase with id: %s does not exist", purchasePayload.getPuId())));

        purchase.setStId(purchasePayload.getStId());
        purchaseDomainRepository.updatePurchase(purchase);

        map.put("Message", "Purchase updated succesfully");
        map.put("New Status", statusDomainRepository.findStatusById(purchasePayload.getStId()).get().getName());
        return map;
    }

    public Map<String, Object> newPurchase(NewPurchaseDto purchasePayload) throws NotFoundException {

        Map<String, Object> map = new HashMap<>();
        ModelMapper modelMapper = new ModelMapper();

        userDomainRepository.findUserById(purchasePayload.getUsId())
                .orElseThrow(() -> new NotFoundException(String
                        .format("User with id: %s not found", purchasePayload.getUsId())));

        statusDomainRepository.findStatusById(purchasePayload.getStId())
                .orElseThrow(() -> new NotFoundException(String
                        .format("Status with id: %s not found", purchasePayload.getStId())));

        purchasePayload.getProducts().forEach(newProductPurchaseDto -> {
            try {
                productDomainRepository
                        .getById(newProductPurchaseDto.getPrId()).orElseThrow(() -> new NotFoundException(String
                                .format("The product with name: %s does not exist",newProductPurchaseDto.getPrId())));
            } catch (NotFoundException e) {
                throw new RuntimeException(e);
            }
        });


        PurchaseEntity purchase = modelMapper.map(purchasePayload, PurchaseEntity.class);

        purchase.getProducts().forEach(productPurchaseEntity -> productPurchaseEntity.setPurchase(purchase));

        purchase.setDate(LocalDateTime.now());

        purchase.getProducts().forEach(productPurchaseEntity -> {
                    productPurchaseEntity.setTotPrcntDisc(productDomainRepository.getPcntDiscount(productPurchaseEntity
                    .getProduct().getPrId()));

                    productPurchaseEntity.setTotProdsCost(productDomainRepository.getDiscountPrice(productPurchaseEntity
                    .getProduct().getPrId())*productPurchaseEntity.getQuantity());

                    productPurchaseEntity.setPurchaseCost(productPurchaseEntity.getTotProdsCost());
                });

        purchaseDomainRepository.newPurchase(purchase);

        map.put("Message", "Products purchased succesfully");
        return map;
    }
}
