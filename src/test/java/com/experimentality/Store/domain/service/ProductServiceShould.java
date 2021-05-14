package com.experimentality.Store.domain.service;

import com.experimentality.Store.domain.dto.DynamicFilterDto;
import com.experimentality.Store.domain.dto.ImageDto;
import com.experimentality.Store.domain.dto.NewProductsDto;
import com.experimentality.Store.domain.repository.ImageDomainRepository;
import com.experimentality.Store.domain.repository.ProductDomainRepository;
import com.experimentality.Store.domain.repository.SubcategoryDomainRepository;
import javassist.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceShould {

    @Autowired
    private ProductDomainRepository product;

    @Autowired
    private SubcategoryDomainRepository subcategory;

    @Autowired
    private ImageDomainRepository image;

    @Test
    @Transactional
    public void createsNewProdtcs() {

        int scId = 1;
        int discountPrct = 10;
        double price = 10000.0;
        String name = "nuevo producto";
        String description = "Nuevo producto prueba";
        String frontImage = "imagen frontal url";
        String backImage = "imagen trasera url";
        String message = "Products saved succesfully";

        ProductService productService = new ProductService(product, subcategory, image);

        NewProductsDto product = new NewProductsDto();
        List<NewProductsDto> newProducts = new ArrayList<>();

        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setDiscountPrct(discountPrct);
        product.setBackImage(backImage);
        product.setFrontImage(frontImage);
        product.setScId(scId);
        newProducts.add(product);

        Map<String, Object> response = productService.newProducts(newProducts);

        assertEquals(message, response.get("Message"));
    }


    @Test
    public void getProductsByNameOrderedByADynamicFilterAndPaged() {

        int limit = 1;
        int offset = 0;
        String name = "Pantalon";
        String request = "";

        ProductService productService = new ProductService(product, subcategory, image);

        Map<String, Object> productList = productService.dynamicFilter(name, limit, offset, request);

        List<DynamicFilterDto> products = (List<DynamicFilterDto>) productList.get("results");
        products.forEach(dynamicFilterDto -> {
            assertNotNull(dynamicFilterDto.getName());
            assertNotNull(dynamicFilterDto.getFrontImage());
            assertNotNull(dynamicFilterDto.getBackImage());
            assertNotNull(dynamicFilterDto.getSearchCounter());
            assertNotNull(dynamicFilterDto.getPrice());
            assertNotNull(dynamicFilterDto.getDiscountPrct());
            assertNotNull(dynamicFilterDto.getDiscountPrice());
        });

        HashMap<String, Object> pageRequest = (HashMap<String, Object>) productList.get("page request");

        assertEquals(limit, pageRequest.get("limit"));
        assertEquals(offset, pageRequest.get("offset"));
        assertNotNull(pageRequest.get("total"));

    }

    @Test
    public void getASpecificProductByItsId() throws NotFoundException {

        String name = "Pantalón dama drill azul";
        String description = "Pantalón dama drill VO5 azul";
        double price = 80000.0;
        double discountPrice = 60000.0;
        int discountPrct = 25;
        int prId = 6;

        ProductService productService = new ProductService(product, subcategory, image);

        Map<String, Object> product = productService.getById(prId);

        assertEquals(prId, product.get("prId"));
        assertEquals(name, product.get("name"));
        assertEquals(description, product.get("description"));
        assertEquals(price, product.get("price"));
        assertEquals(discountPrice, product.get("discountPrice"));
        assertEquals(discountPrct, product.get("discountPrct"));
        assertNotNull(product.get("images"));
    }

    @Test
    public void getAListOfNamesFromTheDatabase() {

        String name = "Pantalones";

        ProductService productService = new ProductService(product, subcategory, image);

        Map<String, Object> results = productService.smartFilter(name);
        List<String> titles = (List<String>) results.get("results");

        assertNotNull(titles);
    }

    @Test
    @Transactional
    public void createsAListOfNewImages() {
        int prId = 1;
        String url = "test";
        String message = "Images saved succesfully";

        ProductService productService = new ProductService(product, subcategory, image);

        ImageDto image = new ImageDto();
        List<ImageDto> images = new ArrayList<>();

        image.setPrId(prId);
        image.setUrl(url);
        images.add(image);

        Map<String, Object> response = productService.newImages(images);

        assertEquals(message, response.get("Message"));

    }
}