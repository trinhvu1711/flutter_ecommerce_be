package com.springbootjwt.security.order;

import com.springbootjwt.security.address.Address;
import com.springbootjwt.security.address.AddressRepository;
import com.springbootjwt.security.cart.Cart;
import com.springbootjwt.security.cart.CartRepository;
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
    private final CartRepository cartRepository;
    private final AddressRepository addressRepository;
    public void save(OrderRequest request){
        var order = Order.builder()
                .id(request.getId())
                .shipping_fee(request.getShipping_fee())
                .status_shipping_id(request.getStatus_shipping_id())
                .payment_id(request.getPayment_id())
//                .location(request.getLocation())
                .productCost(request.getProductCost())
                .total_cost(request.getTotal_cost())
                .removed(request.isRemoved())
//                .carts(request.getCarts())
                .build();
        Order savedOrder = repository.save(order);
        if (request.getCarts() != null && !request.getCarts().isEmpty()) {
            for (Cart cart : request.getCarts()) {
                cart.setOrder(savedOrder);
                cartRepository.save(cart);
            }
        }
        if (request.getLocation() != null) {
            Address location =  request.getLocation();
            location.setOrder(savedOrder);
            addressRepository.save(location);
        }
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

    public List<Order> findAll() {
        return repository.findAll();
    }
}
