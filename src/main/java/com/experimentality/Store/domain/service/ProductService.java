package com.experimentality.Store.domain.service;

import com.experimentality.Store.domain.dto.DynamicFilterDto;
import com.experimentality.Store.domain.dto.ImageDto;
import com.experimentality.Store.domain.dto.NewProductsDto;
import com.experimentality.Store.domain.dto.ShowProductDto;
import com.experimentality.Store.domain.repository.ImageDomainRepository;
import com.experimentality.Store.domain.repository.ProductDomainRepository;
import com.experimentality.Store.domain.repository.SubcategoryDomainRepository;
import com.experimentality.Store.persistence.entity.ImageEntity;
import com.experimentality.Store.persistence.entity.ProductEntity;
import com.google.common.base.Throwables;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@AllArgsConstructor
@Service
public class ProductService {

    @Autowired
    private final ProductDomainRepository productDomainRepository;

    @Autowired
    private final SubcategoryDomainRepository subcategoryCrudRepository;

    @Autowired
    private final ImageDomainRepository imageDomainRepository;


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

    public Map<String, Object> newImages(List<ImageDto> imagesPayload){

        Map<String, Object> map = new HashMap<>();
        ModelMapper modelMapper = new ModelMapper();
        List<ImageEntity> pImages = new ArrayList<>();

        imagesPayload.forEach(imageDto -> pImages.add(modelMapper.map(imageDto,ImageEntity.class)));

        imagesPayload.forEach(imageDto -> {
            try {
                productDomainRepository.getById(imageDto.getPrId())
                        .orElseThrow(() -> new NotFoundException(String
                                .format("The product with name: %s does not exist", imageDto.getPrId())));
            } catch (NotFoundException e) {
                throw new RuntimeException(e);
            }
        });

        imageDomainRepository.newImages(pImages);

        map.put("Message","Images saved succesfully");

        return map;
    }


    public Map<String, Object> dynamicFilter(String result, int limit, int offset, String request) {

        Map<String, Object> map = new HashMap<>();
        Map<String, Object> page = new HashMap<>();
        ModelMapper modelMapper = new ModelMapper();
        List<DynamicFilterDto> products = new ArrayList<>();

        List<ProductEntity> pProducts = productDomainRepository.dynamicFilter(result,limit, offset, request);
        Long counter = productDomainRepository.dynamicFilterCounter(result);

        pProducts.forEach(productEntity -> products.add(modelMapper.map(productEntity, DynamicFilterDto.class)));

        page.put("limit", limit);
        page.put("offset", offset);
        page.put("total", counter);

        map.put("results", products);
        map.put("page request", page);

        return map;
    }

    public Map<String, Object> getById(int prId) throws NotFoundException {

        Map<String, Object> map = new HashMap<>();
        ModelMapper modelMapper = new ModelMapper();
        List<String> images = new ArrayList<>();

        ProductEntity pProduct = productDomainRepository.getById(prId)
                .orElseThrow(() -> new NotFoundException(String.format("The product with name: %s does not exist",prId)));

        ShowProductDto product = modelMapper.map(pProduct, ShowProductDto.class);

        product.getImages().forEach(imageDto -> images.add(imageDto.getUrl()));
        //System.out.println(images);

        Integer counter = pProduct.getSearchCounter();
        counter += 1;
        pProduct.setSearchCounter(counter);
        productDomainRepository.updateProduct(pProduct);

        map.put("prId", product.getPrId());
        map.put("name", product.getName());
        map.put("description", product.getDescription());
        map.put("price", product.getPrice());
        map.put("discountPrice", product.getDiscountPrice());
        map.put("discountPrct", product.getDiscountPrct());
        map.put("images", images);

        return map;
    }


    public Map<String, Object> smartFilter(String name) {

        Map<String, Object> map = new HashMap<>();

        List<String> pProducts = subcategoryCrudRepository.smartFilter(name);
        List<Object> results = new ArrayList<>(pProducts);
        map.put("results", results);

        return map;
    }

}
