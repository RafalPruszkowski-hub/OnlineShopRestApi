package com.store.app.database.repository;

import com.store.app.database.entity.AuthoritiesEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthoritiesRepository extends CrudRepository<AuthoritiesEntity, Long> {
    AuthoritiesEntity findByName(String name);
}