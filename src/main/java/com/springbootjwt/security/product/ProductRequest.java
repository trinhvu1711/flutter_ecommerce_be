package com.springbootjwt.security.product;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProductRequest {
    private Integer id;
    private String name;
    private String description;
    private double price;
}
