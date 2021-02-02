package com.store.app.database.repository;

import com.store.app.database.entity.CartEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends CrudRepository<CartEntity, Integer> {
    CartEntity findByCartId(Integer cartId);

    CartEntity findByPublicCartId(String publiCartId);


    // Find cart that is currently used by user
    @Query(value = "SELECT MAX(cart_id) FROM carts where user_Id =:userId", nativeQuery = true)
    Integer getCurrentCartIdForUser(@Param("userId") int userId);
}
