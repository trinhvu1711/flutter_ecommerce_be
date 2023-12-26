package com.springbootjwt.security.cart;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    public void save(CartRequest request){
        var cart = Cart.builder()
                .id(request.getId())
                .product_id(request.getProduct_id())
                .user_id(request.getUser_id())
                .quantity(request.getQuantity())
                .build();
        cartRepository.save(cart);
    }
    public List<Cart> findCartById(int cartId) {
        return cartRepository.findById(cartId);
    }
}
