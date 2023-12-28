package com.springbootjwt.security.wishlist;

import com.springbootjwt.security.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
@Service
@RequiredArgsConstructor
public class WishlistService {
    private final WishlistRepository wishlistRepository;
    public void save(WishlistRequest request){
        var wishlist = Wishlist.builder()
                .id(request.getId())
                .product_id(request.getProduct_id())
                .removed(request.isRemoved())
                .build();
        wishlistRepository.save(wishlist);
    }
    public List<Wishlist> findWishlist(Principal connectedUser) {
        var user = ((User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal());
        int userId = user.getId();
        return wishlistRepository.findByCreatedBy(userId);
    }

    public void clearWishlist(Principal connectedUser) {
        var user = ((User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal());
        int userId = user.getId();
        List<Wishlist> list =  wishlistRepository.findByCreatedBy(userId);
        list.forEach(cart -> cart.setRemoved(true));
        wishlistRepository.saveAll(list);
    }
}
