package com.springbootjwt.security.order;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OrderRequest {
    private Integer id;
    private Integer location_id;
    private Integer payment_id;
    private Integer status_shipping_id;
    private int shipping_fee;
    private boolean removed;
}
