package com.stockmanagement.backend.service;

import com.stockmanagement.backend.dto.CreateProductRequest;
import com.stockmanagement.backend.dto.UpdateProductRequest;
import com.stockmanagement.backend.dto.ProductResponse;
import com.stockmanagement.backend.entity.Product;
import com.stockmanagement.backend.exception.ResourceNotFoundException;
import com.stockmanagement.backend.mapper.ProductMapper;
import com.stockmanagement.backend.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUpdateProduct_success() {
        // given
        Long id = 1L;
        Product existing = Product.builder()
                .id(id)
                .name("Old")
                .quantity(10)
                .price(5.0)
                .build();
        when(productRepository.findById(id)).thenReturn(Optional.of(existing));
        when(productRepository.save(any(Product.class))).thenAnswer(i -> i.getArgument(0));

        UpdateProductRequest req = new UpdateProductRequest();
        req.setName("New");
        req.setQuantity(20);
        req.setPrice(7.5);

        // when
        ProductResponse response = productService.updateProduct(id, req);

        // then
        assertEquals("New", response.getName());
        assertEquals(20, response.getQuantity());
        assertEquals(7.5, response.getPrice());
        verify(productRepository).save(existing);
    }

    @Test
    void testUpdateProduct_notFound() {
        Long id = 2L;
        when(productRepository.findById(id)).thenReturn(Optional.empty());
        UpdateProductRequest req = new UpdateProductRequest();
        req.setName("Anything");
        req.setQuantity(1);
        req.setPrice(1.0);
        assertThrows(ResourceNotFoundException.class, () -> productService.updateProduct(id, req));
    }
}
