package com.springbootjwt.security.wishlist;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WishlistRepository extends JpaRepository<Wishlist, Integer> {
    List<Wishlist> findByCreatedBy(int id);
}
