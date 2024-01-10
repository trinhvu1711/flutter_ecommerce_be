package com.springbootjwt.security.review;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ReviewRequest {
    private Integer id;
    private Integer product_id;
    private String user_name;
    private String user_img;
    private int rating;
    private String review;
    private boolean is_removed;

}
