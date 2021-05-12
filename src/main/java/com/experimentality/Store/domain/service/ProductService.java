package com.experimentality.Store.domain.service;

import com.experimentality.Store.domain.dto.DynamicFilterDto;
import com.experimentality.Store.domain.dto.NewProductsDto;
import com.experimentality.Store.domain.dto.ShowProductDto;
import com.experimentality.Store.domain.repository.ProductDomainRepository;
import com.experimentality.Store.domain.repository.SubcategoryDomainRepository;
import com.experimentality.Store.persistence.entity.ProductEntity;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Service
public class ProductService {

    @Autowired
    private ProductDomainRepository productDomainRepository;

    @Autowired
    private SubcategoryDomainRepository subcategoryCrudRepository;


    public Map<String, Object> newProducts(List<NewProductsDto> productsPayload) {
        Map<String, Object> map = new HashMap<>();
        ModelMapper modelMapper = new ModelMapper();
        List<ProductEntity> pProducts = new ArrayList<>();
        productsPayload.forEach(newProductsDto -> pProducts.add(modelMapper.map(newProductsDto,ProductEntity.class)));

        pProducts.forEach(productEntity -> {
            productEntity.setDiscountPrice((productEntity.getPrice()*(100-productEntity.getDiscountPrct())/100));
            productEntity.setSearchCounter(0);
            productEntity.setActive(true);
        });

        productDomainRepository.newProducts(pProducts);

        map.put("Message","Products saved succesfully");

        return map;
    }


    public Map<String, Object> dynamicFilter(String result, int limit, int offset) {

        Map<String, Object> map = new HashMap<>();
        Map<String, Object> page = new HashMap<>();
        ModelMapper modelMapper = new ModelMapper();
        List<DynamicFilterDto> products = new ArrayList<>();

        List<ProductEntity> pProducts = productDomainRepository.dynamicFilter(result,limit, offset);
        Long counter = productDomainRepository.dynamicFilterCounter(result);

        pProducts.forEach(productEntity -> products.add(modelMapper.map(productEntity, DynamicFilterDto.class)));

        page.put("limit", limit);
        page.put("offset", offset);
        page.put("total", counter);

        map.put("results", products);
        map.put("page request", page);

        return map;
    }

    public Map<String, Object> getByName(String name) throws NotFoundException {

        Map<String, Object> map = new HashMap<>();
        ModelMapper modelMapper = new ModelMapper();

        ProductEntity pProduct = productDomainRepository.getByName(name)
                .orElseThrow(() -> new NotFoundException(String.format("The product with name: %s does not exist",name)));

        ShowProductDto product = modelMapper.map(pProduct, ShowProductDto.class);

        Integer counter = pProduct.getSearchCounter();
        counter += 1;
        pProduct.setSearchCounter(counter);
        productDomainRepository.updateProduct(pProduct);

        map.put("name", product.getName());
        map.put("description", product.getDescription());
        map.put("price", product.getPrice());
        map.put("discountPrice", product.getDiscountPrice());
        map.put("discountPrct", product.getDiscountPrct());
        map.put("images", product.getImages());

        return map;
    }
}
