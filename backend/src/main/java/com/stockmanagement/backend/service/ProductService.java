package com.stockmanagement.backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stockmanagement.backend.dto.CreateProductRequest;
import com.stockmanagement.backend.dto.UpdateProductRequest;
import com.stockmanagement.backend.dto.ProductResponse;
import com.stockmanagement.backend.dto.ProductSearchRequest;
import com.stockmanagement.backend.dto.ProductDto;
import com.stockmanagement.backend.entity.Product;
import com.stockmanagement.backend.mapper.ProductMapper;
import com.stockmanagement.backend.exception.ProductNotFoundException;
import com.stockmanagement.backend.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;


@Service
@RequiredArgsConstructor
public class ProductService {

    public ProductResponse updateProduct(Long id, UpdateProductRequest request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        ProductMapper.toEntity(request, product);
        Product saved = productRepository.save(product);
        return ProductMapper.toResponse(saved);
    }

    private final ProductRepository productRepository;

    public ProductResponse getProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        return ProductMapper.toResponse(product);
    }

    public ProductResponse createProduct(CreateProductRequest request) {

        Product product = ProductMapper.toEntity(request);

        Product savedProduct = productRepository.save(product);

        return ProductMapper.toResponse(savedProduct);
    }

    public Page<ProductResponse> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable)
                .map(ProductMapper::toResponse);
    }

    // New method for paginated product search via stored procedure
    public Page<ProductDto> search(ProductSearchRequest req) {
        String q = req.getQuery() != null ? "%" + req.getQuery() + "%" : null;
        List<ProductDto> content = productRepository.searchProducts(q, req.getMinPrice(), req.getMaxPrice(),
                req.getMinQty(), req.getMaxQty(), req.getPageNumber(), req.getPageSize(),
                req.getSortColumn(), req.getSortDirection());
        Long total = productRepository.countProducts(q, req.getMinPrice(), req.getMaxPrice(),
                req.getMinQty(), req.getMaxQty());
        return new PageImpl<>(content, PageRequest.of(req.getPageNumber(), req.getPageSize()), total);
    }

    // New method for search by name using JPA
    @Transactional(readOnly = true)
    public Page<ProductResponse> searchByName(String name, Pageable pageable) {
        return productRepository.findByNameContainingIgnoreCase(name, pageable)
                .map(product -> ProductResponse.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .quantity(product.getQuantity())
                        .price(product.getPrice())
                        .build());
    }
}