package com.springbootjwt.security.wishlist;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/wishlist")
@RequiredArgsConstructor
public class WishlistController {
    private final WishlistService wishlistService;
    @PostMapping
    public ResponseEntity<?> save(@RequestBody WishlistRequest request) {
        wishlistService.save(request);
        return ResponseEntity.accepted().build();
    }

    @GetMapping
    public ResponseEntity<List<Wishlist>> findCart(Principal connectedUser) {
        return ResponseEntity.ok(wishlistService.findWishlist(connectedUser));
    }
    @PostMapping("/clear")
    public ResponseEntity<?> clearCart(Principal connectedUser) {
        wishlistService.clearWishlist(connectedUser);
        return ResponseEntity.ok().build();
    }
}
