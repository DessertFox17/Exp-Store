package com.experimentality.Store.web.controller;

import com.experimentality.Store.domain.dto.NewProductPurchaseDto;
import com.experimentality.Store.domain.dto.NewPurchaseDto;
import com.experimentality.Store.domain.dto.UpdatePurchaseStatusDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc(addFilters = false)
public class PurchaseControllerShould {

    @Autowired
    private MockMvc mockMvc;


    private ObjectMapper objectMapper = new ObjectMapper()
            .enable(SerializationFeature.INDENT_OUTPUT);

    @Test
    public void getAPurchaseByClientId() throws Exception {
        mockMvc.perform(get("/purchase/user/1")).andExpect(status().isOk());
    }

    @Test
    public void getAPurchaseByClientIdException() throws Exception {
        mockMvc.perform(get("/purchase/user/0")).andExpect(status().isNotFound());
    }

    @Test
    public void getProductsInCart() throws Exception {
        mockMvc.perform(get("/purchase/cart/1")).andExpect(status().isOk());
    }

    @Test
    public void getProductsInCartException() throws Exception {
        mockMvc.perform(get("/purchase/cart/0")).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAPurchaseStatus() throws Exception {
        UpdatePurchaseStatusDto update = new UpdatePurchaseStatusDto();
        update.setPuId(6);
        update.setStId(2);

        mockMvc.perform(put("/purchase/update").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(update))).andExpect(status().isOk());
    }

    @Test
    @Transactional
    public void updateAPurchaseStatusException() throws Exception {
        UpdatePurchaseStatusDto update = new UpdatePurchaseStatusDto();

        mockMvc.perform(put("/purchase/update").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(update))).andExpect(status().isBadRequest());
    }

    @Test
    @Transactional
    public void createAPurchase() throws Exception {
        NewPurchaseDto purchase = new NewPurchaseDto();
        NewProductPurchaseDto productPurchase = new NewProductPurchaseDto();
        List<NewProductPurchaseDto> products = new ArrayList<>();

        productPurchase.setPrId(1);
        productPurchase.setQuantity(1);
        products.add(productPurchase);

        purchase.setStId(1);
        purchase.setUsId(1);
        purchase.setPayMethod("Ok");
        purchase.setComment("test");
        purchase.setProducts(products);

        mockMvc.perform(post("/purchase/new").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(purchase))).andExpect(status().isOk());
    }

    @Test
    @Transactional
    public void createAPurchaseException() throws Exception {
        NewPurchaseDto purchase = new NewPurchaseDto();

        mockMvc.perform(post("/purchase/new").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(purchase))).andExpect(status().isBadRequest());
    }




}