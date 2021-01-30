package com.store.app.database.repository;

import com.store.app.database.entity.OrderEntity;
import com.store.app.database.entity.UserEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends PagingAndSortingRepository<OrderEntity, Integer> {
    List<OrderEntity> findByUser(UserEntity userEntity);

    OrderEntity findByPublicOrderId(String publicOrderId);

    OrderEntity findByOrderId(int orderId);
}