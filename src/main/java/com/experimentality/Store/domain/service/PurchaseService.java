package com.experimentality.Store.domain.service;

import com.experimentality.Store.domain.dto.ShowPurchaseDto;
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
                .orElseThrow(() -> new NotFoundException(String.format("User with id: %s not found",usId)));

        List<PurchaseEntity> pPurchases = purchaseDomainRepository.getByClient(usId);

        pPurchases.forEach(purchaseEntity -> purchases.add(modelMapper.map(purchaseEntity, ShowPurchaseDto.class)));

        purchases.forEach(showPurchaseDto -> showPurchaseDto.getProducts().remove("images"));

        map.put("Purchases", purchases);

        return map;
    }
}
