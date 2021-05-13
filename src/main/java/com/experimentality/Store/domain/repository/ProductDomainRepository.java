package com.experimentality.Store.domain.repository;

import com.experimentality.Store.persistence.entity.ProductEntity;

import java.util.List;
import java.util.Optional;

public interface ProductDomainRepository {
    List<ProductEntity> dynamicFilter(String result, int limit, int offset, String request);
    Long dynamicFilterCounter(String result);
    Optional<ProductEntity> getByName(String name);
    ProductEntity updateProduct(ProductEntity productPayload);
    List<ProductEntity> newProducts(List<ProductEntity> productsPayload);
}
