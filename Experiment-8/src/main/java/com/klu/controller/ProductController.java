package com.klu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.klu.model.Product;
import com.klu.repository.ProductRepository;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductRepository repo;

    // Test endpoint
    @GetMapping("/test")
    public String test() {
        return "Product API Working";
    }

    // 1. Add product
    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Product saved = repo.save(product);
        return ResponseEntity.ok(saved);
    }

    // 2. View all products
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(repo.findAll());
    }

    // 3. Search by category
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Product>> getByCategory(@PathVariable String category) {
        return ResponseEntity.ok(repo.findByCategory(category));
    }

    // 4. Filter by price range
    @GetMapping("/filter")
    public ResponseEntity<List<Product>> filterByPrice(
            @RequestParam double min,
            @RequestParam double max) {

        return ResponseEntity.ok(repo.findByPriceBetween(min, max));
    }

    // 5. Sort products by price
    @GetMapping("/sorted")
    public ResponseEntity<List<Product>> sortedProducts() {
        return ResponseEntity.ok(repo.findAllSortedByPrice());
    }

    // 6. Products above price
    @GetMapping("/expensive/{price}")
    public ResponseEntity<List<Product>> expensiveProducts(@PathVariable double price) {
        return ResponseEntity.ok(repo.findExpensiveProducts(price));
    }
}