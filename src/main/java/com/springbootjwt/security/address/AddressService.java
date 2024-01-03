package com.springbootjwt.security.address;

import com.springbootjwt.security.user.User;
import com.springbootjwt.security.wishlist.Wishlist;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository repository;
    public void save(AddressRequest request){
        var address = Address.builder()
                .id(request.getId())
                .name(request.getName())
                .address_detail(request.getAddress_detail())
                .district(request.getDistrict())
                .phone(request.getPhone())
                .city(request.getCity())
                .ward(request.getWard())
                .removed(request.isRemoved())
                .order(request.getOrder())
                .build();
        repository.save(address);
    }

    public List<Address> findAddressList(Principal connectedUser) {
        var user = ((User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal());
        int userId = user.getId();
        return repository.findByCreatedBy(userId);
    }

    public void clearAddress(Principal connectedUser) {
        var user = ((User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal());
        int userId = user.getId();
        List<Address> list =  repository.findByCreatedBy(userId);
        list.forEach(address -> address.setRemoved(true));
        repository.saveAll(list);
    }
}
