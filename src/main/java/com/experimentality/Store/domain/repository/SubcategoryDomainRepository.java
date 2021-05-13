package com.experimentality.Store.domain.repository;

import java.util.List;

public interface SubcategoryDomainRepository {
    List<String> smartFilter(String name);
}
