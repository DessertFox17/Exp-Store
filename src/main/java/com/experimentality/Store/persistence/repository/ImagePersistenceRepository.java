package com.experimentality.Store.persistence.repository;

import com.experimentality.Store.domain.repository.ImageDomainRepository;
import com.experimentality.Store.persistence.crud.ImageCrudRepository;
import com.experimentality.Store.persistence.entity.ImageEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ImagePersistenceRepository implements ImageDomainRepository {

    @Autowired
    private ImageCrudRepository imageCrudRepository;

    @Override
    public List<ImageEntity> newImages(List<ImageEntity> imagesPayload) {
        return (List<ImageEntity>) imageCrudRepository.saveAll(imagesPayload);
    }
}
