package com.stockmanagement.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Response DTO for product data
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class test {

    private Long idtest;

    private String nameTest;

    private String descriptionTest;

    private Double priceTest;

    private Integer TotalQuantityTest;

    private Integer AvailableQuantityTest;
}