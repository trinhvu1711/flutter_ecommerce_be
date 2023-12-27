package com.springbootjwt.security.cart;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    @PostMapping
    public ResponseEntity<?> save(@RequestBody CartRequest request) {
        cartService.save(request);
        return ResponseEntity.accepted().build();
    }

    @GetMapping
    public ResponseEntity<List<Cart>> findCart(Principal connectedUser) {
        return ResponseEntity.ok(cartService.findCart(connectedUser));
    }
}
