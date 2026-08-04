package com.stockmanagement.backend.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductResponse {

    private Long id;

    private String name;

    private String description;

    private Integer quantity;

    private Double price;
}