package com.example.productmanagement.controller;

import com.example.productmanagement.dto.ProductRequest;
import com.example.productmanagement.dto.ProductResponse;
import com.example.productmanagement.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@Tag(name = "Products", description = "API endpoints for managing products")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @Operation(summary = "Create a new product", description = "Creates a new product with the provided details")
    @ApiResponse(responseCode = "201", description = "Product created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid input data")
    @ApiResponse(responseCode = "409", description = "Product with SKU already exists")
    public ResponseEntity<ProductResponse> createProduct(
            @Valid @RequestBody ProductRequest request) {
        ProductResponse response = productService.createProduct(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Get all products", description = "Retrieves a list of all products")
    @ApiResponse(responseCode = "200", description = "Products retrieved successfully")
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        List<ProductResponse> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get product by ID", description = "Retrieves a product by its unique ID")
    @ApiResponse(responseCode = "200", description = "Product found")
    @ApiResponse(responseCode = "404", description = "Product not found")
    public ResponseEntity<ProductResponse> getProductById(
            @Parameter(description = "ID of the product to retrieve")
            @PathVariable Long id) {
        ProductResponse response = productService.getProductById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/sku/{sku}")
    @Operation(summary = "Get product by SKU", description = "Retrieves a product by its SKU")
    @ApiResponse(responseCode = "200", description = "Product found")
    @ApiResponse(responseCode = "404", description = "Product not found")
    public ResponseEntity<ProductResponse> getProductBySku(
            @Parameter(description = "SKU of the product to retrieve")
            @PathVariable String sku) {
        ProductResponse response = productService.getProductBySku(sku);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/search")
    @Operation(summary = "Search products by name", description = "Searches for products containing the specified name pattern")
    @ApiResponse(responseCode = "200", description = "Products found")
    public ResponseEntity<List<ProductResponse>> searchProductsByName(
            @Parameter(description = "Name pattern to search for")
            @RequestParam String name) {
        List<ProductResponse> products = productService.searchProductsByName(name);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/filter")
    @Operation(summary = "Filter products by active status", description = "Retrieves products with the specified active status")
    @ApiResponse(responseCode = "200", description = "Products found")
    public ResponseEntity<List<ProductResponse>> getProductsByActiveStatus(
            @Parameter(description = "Active status to filter by")
            @RequestParam Boolean active) {
        List<ProductResponse> products = productService.getProductsByActiveStatus(active);
        return ResponseEntity.ok(products);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a product", description = "Updates an existing product with the provided details")
    @ApiResponse(responseCode = "200", description = "Product updated successfully")
    @ApiResponse(responseCode = "400", description = "Invalid input data")
    @ApiResponse(responseCode = "404", description = "Product not found")
    @ApiResponse(responseCode = "409", description = "Product with SKU already exists")
    public ResponseEntity<ProductResponse> updateProduct(
            @Parameter(description = "ID of the product to update")
            @PathVariable Long id,
            @Valid @RequestBody ProductRequest request) {
        ProductResponse response = productService.updateProduct(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a product", description = "Deletes a product by its ID")
    @ApiResponse(responseCode = "204", description = "Product deleted successfully")
    @ApiResponse(responseCode = "404", description = "Product not found")
    public ResponseEntity<Void> deleteProduct(
            @Parameter(description = "ID of the product to delete")
            @PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
