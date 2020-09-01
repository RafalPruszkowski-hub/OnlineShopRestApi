package com.store.app.database.repository;

import com.store.app.database.entity.ProductEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<ProductEntity, Integer> {
    ProductEntity findByPublicProductId(String id);
}
