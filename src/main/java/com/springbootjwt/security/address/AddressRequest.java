package com.springbootjwt.security.address;

import com.springbootjwt.security.order.Order;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AddressRequest {
    private Integer id;
    private String city;
    private String district;
    private String ward;
    private String name;
    private String phone;
    private String address_detail;
    private boolean removed;
    private Order order;

}
