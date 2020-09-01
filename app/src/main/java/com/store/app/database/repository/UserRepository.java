package com.store.app.database.repository;

import com.store.app.database.entity.UserEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Integer> {
    UserEntity findByEmail(String userId);

    UserEntity findByPublicUserId(String userId);

    UserEntity findByUserId(int id);
}
