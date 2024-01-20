package com.springbootjwt.security.order;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
        try {
            List<Order> orders = service.findOrder(connectedUser);
            return ResponseEntity.ok(orders);
        } catch (Exception e) {
            e.printStackTrace();  // Log the exception for debugging
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/all")
    @PreAuthorize("permitAll()")
    public ResponseEntity<List<Order>> findAllOrders() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping("/clear")
    public ResponseEntity<?> clearOrder(Principal connectedUser) {
        service.clearOrder(connectedUser);
        return ResponseEntity.ok().build();
    }
}
