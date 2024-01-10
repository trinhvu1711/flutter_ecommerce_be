package com.springbootjwt.security.review;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository repository;
    public void save(ReviewRequest request){
        var review = Review.builder()
                .id(request.getId())
                .productId(request.getProduct_id())
                .rating(request.getRating())
                .user_img(request.getUser_img())
                .user_name(request.getUser_name())
                .review(request.getReview())
                .removed(request.is_removed())
                .build();
        repository.save(review);
    }
    public List<Review> findReviewByProduct(int product_id) {
        return repository.findByProductId(product_id);
    }
}