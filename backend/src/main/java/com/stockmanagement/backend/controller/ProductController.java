package com.stockmanagement.backend.controller;

import com.stockmanagement.backend.dto.ProductDto;
import com.stockmanagement.backend.dto.ProductResponse;
import com.stockmanagement.backend.dto.ProductSearchRequest;
import com.stockmanagement.backend.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    // Existing endpoints
    @GetMapping("/search")
    public Page<ProductDto> search(@Valid ProductSearchRequest request) {
        return productService.search(request);
    }

    @Transactional(readOnly = true)
    @GetMapping("/search/name")
    public Page<ProductResponse> searchByName(@RequestParam String name, Pageable pageable) {
        return productService.searchByName(name, pageable);
    }

    @GetMapping("/search/name/{name:.+}/page/{pageNumber:\\d+}/size/{pageSize:\\d+}")
    public Page<ProductResponse> searchByNamePaginated(
            @PathVariable String name,
            @PathVariable int pageNumber,
            @PathVariable int pageSize) {
        return productService.searchByName(name, PageRequest.of(pageNumber, pageSize));
    }

    // Rest of the controller methods...
}