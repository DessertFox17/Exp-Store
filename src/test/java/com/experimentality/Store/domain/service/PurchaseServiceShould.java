package com.experimentality.Store.domain.service;


import com.experimentality.Store.domain.dto.InCartDto;
import com.experimentality.Store.domain.dto.NewProductPurchaseDto;
import com.experimentality.Store.domain.dto.NewPurchaseDto;
import com.experimentality.Store.domain.dto.ShowPurchaseDto;
import com.experimentality.Store.domain.dto.UpdatePurchaseStatusDto;
import com.experimentality.Store.domain.repository.ProductDomainRepository;
import com.experimentality.Store.domain.repository.PurchaseDomainRepository;
import com.experimentality.Store.domain.repository.StatusDomainRepository;
import com.experimentality.Store.domain.repository.UserDomainRepository;
import javassist.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PurchaseServiceShould {

    @Autowired
    private StatusDomainRepository statusDR;

    @Autowired
    private PurchaseDomainRepository purchaseDR;

    @Autowired
    private ProductDomainRepository productDR;

    @Autowired
    private UserDomainRepository userDR;

    @Test
    public void getAPurchaseByItsClientId() throws NotFoundException {

        int usId = 1;

        PurchaseService purchase = new PurchaseService(statusDR, purchaseDR, productDR, userDR);

        Map<String, Object> purchaseList = purchase.getByClient(usId);

        List<ShowPurchaseDto> purchases = (List<ShowPurchaseDto>) purchaseList.get("Purchases");

        purchases.forEach(showPurchaseDto -> {
            assertNotNull(showPurchaseDto.getPuId());
            assertNotNull(showPurchaseDto.getDate());
            assertNotNull(showPurchaseDto.getPayMethod());
            assertNotNull(showPurchaseDto.getComment());
            assertNotNull(showPurchaseDto.getStatus());
            assertNotNull(showPurchaseDto.getProducts());
        });
    }

    @Test
    public void getThePurchasesInCartState() throws NotFoundException {

        int usId = 1;

        PurchaseService purchase = new PurchaseService(statusDR, purchaseDR, productDR, userDR);

        Map<String, Object> inCartList = purchase.getByUserandStatus(usId);

        List<InCartDto> inCart = (List<InCartDto>) inCartList.get("Products in Cart");

        inCart.forEach(inCartDto -> {
            assertNotNull(inCartDto.getPuId());
            assertNotNull(inCartDto.getProducts());
        });
    }

    @Test
    @Transactional
    public void updateAPurchaseStatus() throws NotFoundException {
        int puId = 6;
        int stId = 2;

        PurchaseService purchase = new PurchaseService(statusDR, purchaseDR, productDR, userDR);

        UpdatePurchaseStatusDto update = new UpdatePurchaseStatusDto();
        update.setStId(stId);
        update.setPuId(puId);

        Map<String, Object> response = purchase.updatePurchase(update);

        assertNotNull(response.get("Message"));
        assertNotNull(response.get("New Status"));
    }

    @Test
    @Transactional
    public void createANewPurchase() throws NotFoundException {
        int stId = 1;
        int usId = 1;
        int prId = 1;
        int quantity = 1;
        String payMethod = "Cash";
        String comment = "Wonderfull";
        String message = "Products purchased succesfully";

        PurchaseService newPurchase = new PurchaseService(statusDR, purchaseDR, productDR, userDR);

        NewPurchaseDto purchase = new NewPurchaseDto();
        NewProductPurchaseDto productPurchase = new NewProductPurchaseDto();
        List<NewProductPurchaseDto> products = new ArrayList<>();

        productPurchase.setPrId(prId);
        productPurchase.setQuantity(quantity);
        products.add(productPurchase);

        purchase.setStId(stId);
        purchase.setUsId(usId);
        purchase.setPayMethod(payMethod);
        purchase.setComment(comment);
        purchase.setProducts(products);

        Map<String, Object> response = newPurchase.newPurchase(purchase);

        assertEquals(message, response.get("Message"));

    }

}