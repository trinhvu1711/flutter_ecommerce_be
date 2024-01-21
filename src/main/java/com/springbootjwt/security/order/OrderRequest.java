package com.springbootjwt.security.order;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.springbootjwt.security.address.Address;
import com.springbootjwt.security.cart.Cart;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class OrderRequest {
    private Integer id;
    private Address location;
    private Integer payment_id;
    private Integer status_shipping_id;
    private int shipping_fee;
    private int total_cost;
    private int productCost;
    private boolean removed;
    private boolean canceled;
    private List<Cart> carts;
}
