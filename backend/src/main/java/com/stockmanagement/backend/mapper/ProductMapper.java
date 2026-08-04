package com.stockmanagement.backend.mapper;

import com.stockmanagement.backend.dto.CreateProductRequest;
import com.stockmanagement.backend.dto.ProductDto;
import com.stockmanagement.backend.dto.UpdateProductRequest;
import com.stockmanagement.backend.dto.ProductResponse;
import com.stockmanagement.backend.entity.Product;

public class ProductMapper {

    public static Product toEntity(UpdateProductRequest request, Product product) {
        product.setName(request.getName());
        product.setQuantity(request.getQuantity());
        product.setPrice(request.getPrice());
        return product;
    }

    public static Product toEntity(CreateProductRequest request) {
        return Product.builder()
                .name(request.getName())
                .quantity(request.getQuantity())
                .price(request.getPrice())
                .build();
    }

    public static ProductDto toDto(Product product) {
        return ProductDto.builder()
                .name(product.getName())
                .quantity(product.getQuantity())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }

    public static ProductResponse toResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .quantity(product.getQuantity())
                .price(product.getPrice())
                .build();
    }

}