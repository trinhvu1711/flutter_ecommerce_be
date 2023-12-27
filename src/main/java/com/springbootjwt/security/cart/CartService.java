package com.springbootjwt.security.cart;

import com.springbootjwt.security.user.ChangePasswordRequest;
import com.springbootjwt.security.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    public void save(CartRequest request){
        var cart = Cart.builder()
                .id(request.getId())
                .product_id(request.getProduct_id())
                .quantity(request.getQuantity())
                .build();
        cartRepository.save(cart);
    }
    public List<Cart> findCart(Principal connectedUser) {
        var user = ((User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal());
        int userId = user.getId();
        return cartRepository.findByCreatedBy(userId);
    }

}
