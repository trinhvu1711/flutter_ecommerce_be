package com.springbootjwt.security.review;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/review")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService service;
    @PostMapping
    public ResponseEntity<?> save(@RequestBody ReviewRequest request) {
        service.save(request);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/{id_product}")
    public ResponseEntity<List<Review>> findReviewProduct(@PathVariable Integer id_product) {
        return ResponseEntity.ok(service.findReviewByProduct(id_product));
    }
    @GetMapping
    @PreAuthorize("permitAll()")
    public ResponseEntity<List<Review>> findAllReview() {
        return ResponseEntity.ok(service.findAllReview());
    }
}
