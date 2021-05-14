package com.experimentality.Store.web.controller;

import com.experimentality.Store.domain.dto.ImageDto;
import com.experimentality.Store.domain.dto.NewProductsDto;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc(addFilters = false)
public class ProductControllerShould {

    @Autowired
    private MockMvc mockMvc;


    private ObjectMapper objectMapper = new ObjectMapper()
            .enable(SerializationFeature.INDENT_OUTPUT);

    @Test
    public void getAProductByItsId() throws Exception {
        mockMvc.perform(get("/product/1")).andExpect(status().isOk());
    }

    @Test
    public void getAProductByItsIdException() throws Exception {
        mockMvc.perform(get("/product/0")).andExpect(status().isNotFound());
    }

    @Test
    public void getAListOfProductOrCategoriesOrSubcategosies() throws Exception {
        mockMvc.perform(get("/product/smartfilter")).andExpect(status().isOk());
    }

    @Test
    public void getAListOfProductsByName() throws Exception {
        mockMvc.perform(get("/product/dynamicfilter")).andExpect(status().isOk());
    }

    @Test
    @Transactional
    public void createANewListOfImages() throws Exception {
        ImageDto image = new ImageDto();
        List<ImageDto> imagesList = new ArrayList<>();
        image.setPrId(1);
        image.setUrl("test");
        imagesList.add(image);

        mockMvc.perform(post("/product/image").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(imagesList))).andExpect(status().isOk());
    }

    @Test
    @Transactional
    public void createANewListOfProducts() throws Exception {
        NewProductsDto product = new NewProductsDto();
        List<NewProductsDto> newProducts = new ArrayList<>();

        product.setName("test");
        product.setDescription("test");
        product.setPrice(10000);
        product.setDiscountPrct(10);
        product.setBackImage("test");
        product.setFrontImage("test");
        product.setScId(1);
        newProducts.add(product);

        mockMvc.perform(post("/product/new").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newProducts))).andExpect(status().isOk());
    }


}