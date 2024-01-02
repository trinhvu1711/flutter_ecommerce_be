package com.springbootjwt.security.order;

import com.springbootjwt.security.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository repository;
    public void save(OrderRequest request){
        var order = Order.builder()
                .id(request.getId())
                .shipping_fee(request.getShipping_fee())
                .status_shipping_id(request.getStatus_shipping_id())
                .payment_id(request.getPayment_id())
                .location_id(request.getLocation_id())
                .removed(request.isRemoved())
                .build();
        repository.save(order);
    }
    public List<Order> findOrder(Principal connectedUser) {
        var user = ((User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal());
        int userId = user.getId();
        return repository.findByCreatedBy(userId);
    }

    public void clearOrder(Principal connectedUser) {
        var user = ((User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal());
        int userId = user.getId();
        List<Order> list =  repository.findByCreatedBy(userId);
        list.forEach(cart -> cart.setRemoved(true));
        repository.saveAll(list);
    }
}
