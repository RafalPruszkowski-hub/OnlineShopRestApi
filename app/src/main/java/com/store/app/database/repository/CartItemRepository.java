package com.store.app.database.repository;

import com.store.app.database.entity.CartItemEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends CrudRepository<CartItemEntity, Integer> {
}