package com.stockmanagement.backend.repository;

import com.stockmanagement.backend.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

     // New JPA search method for case-insensitive partial name matches
     Page<Product> findByNameContainingIgnoreCase(String name, Pageable pageable);

     // Keep the existing native query method for compatibility
     @Query(value = "CALL dbo.search_products(:query, :minPrice, :maxPrice, :minQty, :maxQty, :pageNumber, :pageSize, :sortColumn, :sortDirection)", nativeQuery = true)
     List<com.stockmanagement.backend.dto.ProductDto> searchProducts(@Param("query") String query,
               @Param("minPrice") Double minPrice,
               @Param("maxPrice") Double maxPrice,
               @Param("minQty") Integer minQty,
               @Param("maxQty") Integer maxQty,
               @Param("pageNumber") Integer pageNumber,
               @Param("pageSize") Integer pageSize,
               @Param("sortColumn") String sortColumn,
               @Param("sortDirection") String sortDirection);

     @Query(value = """
                   SELECT COUNT(*)
                   FROM products p
                   WHERE (:query IS NULL
                          OR LOWER(p.name) LIKE LOWER(CONCAT('%', :query, '%')))
                     AND p.price BETWEEN COALESCE(:minPrice, 0)
                                     AND COALESCE(:maxPrice, 999999999)
                     AND p.quantity BETWEEN COALESCE(:minQty, 0)
                                        AND COALESCE(:maxQty, 999999999)
               """, nativeQuery = true)
     Long countProducts(
               @Param("query") String query,
               @Param("minPrice") Double minPrice,
               @Param("maxPrice") Double maxPrice,
               @Param("minQty") Integer minQty,
               @Param("maxQty") Integer maxQty);
}

// (Rest of the repository content remains unchanged)