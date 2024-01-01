package com.springbootjwt.security.address;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/address")
@RequiredArgsConstructor
public class AddressController {
    private final AddressService service;
    @PostMapping
    public ResponseEntity<?> save(@RequestBody AddressRequest request) {
        service.save(request);
        return ResponseEntity.accepted().build();
    }
    @GetMapping
    public ResponseEntity<List<Address>> findCart(Principal connectedUser) {
        return ResponseEntity.ok(service.findAddressList(connectedUser));
    }
    @PostMapping("/clear")
    public ResponseEntity<?> clearCart(Principal connectedUser) {
        service.clearAddress(connectedUser);
        return ResponseEntity.ok().build();
    }
}
