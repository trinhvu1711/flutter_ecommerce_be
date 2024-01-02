package com.springbootjwt.security.cart;

import com.springbootjwt.security.user.ChangePasswordRequest;
import com.springbootjwt.security.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    public void save(CartRequest request){
        var cart = Cart.builder()
                .id(request.getId())
                .product_id(request.getProduct_id())
                .quantity(request.getQuantity())
                .removed(request.is_removed())
                .build();
        cartRepository.save(cart);
    }
    public List<Cart> findCart(Principal connectedUser) {
        var user = ((User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal());
        int userId = user.getId();
        return cartRepository.findByCreatedBy(userId);
    }

    public List<Cart> findCartOrder(Principal connectedUser, int order_id) {
        var user = ((User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal());
        int userId = user.getId();
        return cartRepository.findByOrderIdAndCreatedBy(order_id ,userId);
    }

    public void addCartToOrder(Principal connectedUser, List<Integer> id_cart, Integer order_id){
        var user = ((User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal());
        int userId = user.getId();
        List<Cart> carts = cartRepository.findByCreatedBy(userId);

        carts.forEach(cart -> {
            if (id_cart.contains(cart.getId())) {
                cart.setOrderId(order_id);
            }
        });
        cartRepository.saveAll(carts);
    }

    public void clearCart(Principal connectedUser) {
        var user = ((User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal());
        int userId = user.getId();
        List<Cart> list =  cartRepository.findByCreatedBy(userId);
        list.forEach(cart -> cart.setRemoved(true));
        cartRepository.saveAll(list);
    }

}