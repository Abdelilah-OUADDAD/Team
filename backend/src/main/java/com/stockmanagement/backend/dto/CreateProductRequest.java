package com.stockmanagement.backend.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CreateProductRequest {

    @NotBlank
    private String name;

    @NotNull
    @Min(0)
    private Integer quantity;

    @NotNull
    @Positive
    private Double price;
}