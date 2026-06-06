package com.example.productmanagement.repository;

import com.example.productmanagement.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for Product entity.
 * Provides CRUD operations and custom query methods.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    /**
     * Find a product by its SKU.
     *
     * @param sku the SKU of the product
     * @return an Optional containing the product if found
     */
    Optional<Product> findBySku(String sku);

    /**
     * Find all active products.
     *
     * @param active the active status
     * @return list of products with the specified active status
     */
    List<Product> findByActive(Boolean active);

    /**
     * Find products by name containing the specified string.
     *
     * @param name the name pattern to search for
     * @return list of products matching the name pattern
     */
    List<Product> findByNameContainingIgnoreCase(String name);

    /**
     * Check if a product with the given SKU exists.
     *
     * @param sku the SKU to check
     * @return true if a product with the SKU exists
     */
    boolean existsBySku(String sku);

    /**
     * Check if a product with the given SKU exists, excluding the specified ID.
     *
     * @param sku the SKU to check
     * @param id  the ID to exclude
     * @return true if a product with the SKU exists (excluding the given ID)
     */
    boolean existsBySkuAndIdNot(String sku, Long id);
}