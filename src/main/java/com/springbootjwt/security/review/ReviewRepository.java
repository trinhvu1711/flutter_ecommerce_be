package com.springbootjwt.security.review;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    List<Review> findByProductId(int productId);
    List<Review> findAll();
//    List<Cart> findByOrderIdAndCreatedBy (int order_id, int user_id);
}
