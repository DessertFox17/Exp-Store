package com.experimentality.Store.domain.service;

import com.experimentality.Store.domain.dto.DynamicFilterDto;
import com.experimentality.Store.domain.dto.NewProductsDto;
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
    private ProductDomainRepository productDomainRepository;

    @Autowired
    private SubcategoryDomainRepository subcategoryDomainRepository;

    @Test
    @Transactional
    public void create_new_products(){

        int scId = 1, discountPrct = 10;
        double price = 10000.0;
        String name = "nuevo producto", description = "Nuevo producto prueba", frontImage = "imagen frontal url",
                backImage = "imagen trasera url", message = "Products saved succesfully";

        ProductService productService = new ProductService(productDomainRepository, subcategoryDomainRepository);

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
    public void get_products_by_name_ordered_by_search_counter_and_paged() {

        int limit = 1, offset = 0;
        String name = "Pantalon";

        ProductService productService = new ProductService(productDomainRepository, subcategoryDomainRepository);

        Map<String, Object> productList = productService.dynamicFilter(name, limit, offset);

        List<DynamicFilterDto> products = (List<DynamicFilterDto>) productList.get("results");
        products.forEach(dynamicFilterDto -> {
            assertNotNull(dynamicFilterDto.getName());
            assertNotNull(dynamicFilterDto.getFrontImage());
            assertNotNull(dynamicFilterDto.getBackImage());
            assertNotNull(dynamicFilterDto.getSearchCounter());
            assertNotNull(dynamicFilterDto.getPrice());
            assertNotNull(dynamicFilterDto.getDiscountPrice());
        });

        HashMap<String, Object> pageRequest = (HashMap<String, Object>) productList.get("page request");

        assertEquals(limit, pageRequest.get("limit"));
        assertEquals(offset, pageRequest.get("offset"));
        assertNotNull(pageRequest.get("total"));

    }

    @Test
    public void get_a_specific_product_by_its_name() throws NotFoundException {

        String name = "Pantalón dama drill azul", description = "Pantalón dama drill VO5 azul";
        double price = 80000.0, discountPrice = 60000.0;
        int discountPrct = 25;

        ProductService productService = new ProductService(productDomainRepository, subcategoryDomainRepository);

        Map<String, Object> product = productService.getByName(name);

        assertEquals(name,product.get("name"));
        assertEquals(description,product.get("description"));
        assertEquals(price,product.get("price"));
        assertEquals(discountPrice,product.get("discountPrice"));
        assertEquals(discountPrct,product.get("discountPrct"));
        assertNotNull(product.get("images"));
    }
}