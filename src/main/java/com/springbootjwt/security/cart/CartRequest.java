package com.springbootjwt.security.cart;

import com.springbootjwt.security.order.Order;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CartRequest {
    private Integer id;
    private Integer product_id;
    private int quantity;
    private boolean is_removed;
    private Order order;

}
