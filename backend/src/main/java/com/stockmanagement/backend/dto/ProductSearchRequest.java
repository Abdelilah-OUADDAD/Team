package com.stockmanagement.backend.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Request DTO for product search parameters with validation annotations
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductSearchRequest {

    @Size(max = 200, message = "Search query must not exceed 200 characters")
    private String query;

    private Double minPrice;

    private Double maxPrice;

    private Integer minQty;

    private Integer maxQty;

    @Pattern(regexp = "id|name|price|quantity", message = "Sort column must be one of: id, name, price, quantity")
    private String sortColumn = "id";

    @Pattern(regexp = "ASC|DESC", message = "Sort direction must be either ASC or DESC")
    private String sortDirection = "ASC";

    @Min(value = 1, message = "Page size must be at least 1")
    @Max(value = 200, message = "Page size must not exceed 200")
    private Integer pageSize = 20;

    @Min(value = 0, message = "Page number must not be negative")
    private Integer pageNumber = 0;
}