package com.springbootjwt.security.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public void save(ProductRequest request) {
        var product = Product.builder()
                .id(request.getId())
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .img(request.getImg())
                .category(request.getCategory())
                .quantity(request.getQuantity())
                .build();
        productRepository.save(product);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }
}