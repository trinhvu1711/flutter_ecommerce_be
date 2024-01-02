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

    @GetMapping("/{id_order}")
    public ResponseEntity<List<Cart>> findCartOrder(Principal connectedUser, @PathVariable Integer id_order) {
        return ResponseEntity.ok(cartService.findCartOrder(connectedUser, id_order));
    }

    @PostMapping("/clear")
    public ResponseEntity<?> clearCart(Principal connectedUser) {
        cartService.clearCart(connectedUser);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/addCartOrder")
    public ResponseEntity<?> addOrder(
            Principal connectedUser,
            @RequestBody List<Integer> idCart,
            @RequestParam Integer idOrder) {

        cartService.addCartToOrder(connectedUser, idCart, idOrder);

        return ResponseEntity.ok().build();
    }
}
