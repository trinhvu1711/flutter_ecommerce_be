package com.springbootjwt.security.cart;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    List<Cart> findByCreatedBy(int id);
//    List<Cart> findByOrderIdAndCreatedBy (int order_id, int user_id);
}
