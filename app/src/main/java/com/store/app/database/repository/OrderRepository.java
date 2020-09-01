package com.store.app.database.repository;

import com.store.app.database.entity.OrderEntity;
import com.store.app.database.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<OrderEntity, Integer> {
    List<OrderEntity> findByUser(UserEntity userEntity);
}
