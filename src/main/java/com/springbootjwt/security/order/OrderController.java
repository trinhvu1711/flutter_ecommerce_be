package com.springbootjwt.security.order;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService service;
    @PostMapping
    public ResponseEntity<?> save(@RequestBody OrderRequest request) {
        service.save(request);
        return ResponseEntity.accepted().build();
    }

    @GetMapping
    public ResponseEntity<List<Order>> findOrder(Principal connectedUser) {
        return ResponseEntity.ok(service.findOrder(connectedUser));
    }
    @PostMapping("/clear")
    public ResponseEntity<?> clearOrder(Principal connectedUser) {
        service.clearOrder(connectedUser);
        return ResponseEntity.ok().build();
    }
}
