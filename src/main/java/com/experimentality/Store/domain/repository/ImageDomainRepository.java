package com.experimentality.Store.domain.repository;

import com.experimentality.Store.persistence.entity.ImageEntity;

import java.util.List;

public interface ImageDomainRepository {
    List<ImageEntity> newImages(List<ImageEntity> imagesPayload);
}
