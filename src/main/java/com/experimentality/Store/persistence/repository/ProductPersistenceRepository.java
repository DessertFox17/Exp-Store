package com.experimentality.Store.persistence.repository;

import com.experimentality.Store.domain.repository.ProductDomainRepository;
import com.experimentality.Store.persistence.crud.ProductCrudRepository;
import com.experimentality.Store.persistence.entity.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Repository
public class ProductPersistenceRepository implements ProductDomainRepository {

    @Autowired
    private ProductCrudRepository productCrudRepository;

    @Override
    public List<ProductEntity> dynamicFilter(String name, int limit, int offset, String request) {

        if(request == "counter"){
            return productCrudRepository
                    .dynamicFilter(name.toLowerCase(Locale.ROOT), PageRequest
                            .of(offset,limit,Sort.by(Sort.Direction.ASC,"searchCounter")));
        }
        if(request == "min"){
            return productCrudRepository
                    .dynamicFilter(name.toLowerCase(Locale.ROOT), PageRequest
                            .of(offset,limit,Sort.by(Sort.Direction.ASC, "discountPrice")));
        }
        if(request == "max"){
            return productCrudRepository
                    .dynamicFilter(name.toLowerCase(Locale.ROOT),  PageRequest
                            .of(offset,limit,Sort.by(Sort.Direction.DESC, "discountPrice")));
        }

        return productCrudRepository.dynamicFilter(name.toLowerCase(Locale.ROOT),  PageRequest
                .of(offset,limit, Sort.by(Sort.Direction.ASC, "name")));
    }

    @Override
    public Long dynamicFilterCounter(String result) {
        return productCrudRepository.dynamicFilterCounter(result.toLowerCase(Locale.ROOT));
    }

    @Override
    public Optional<ProductEntity> getByName(String name) {
        return productCrudRepository.findByName(name);
    }

    @Override
    public ProductEntity updateProduct(ProductEntity productPayload) {
        return productCrudRepository.save(productPayload);
    }

    @Override
    public List<ProductEntity> newProducts(List<ProductEntity> productsPayload) {
        return (List<ProductEntity>) productCrudRepository.saveAll(productsPayload);
    }


}
