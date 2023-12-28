package com.springbootjwt.security.wishlist;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class WishlistRequest {
    private Integer id;
    private Integer product_id;
    private boolean removed;
}
