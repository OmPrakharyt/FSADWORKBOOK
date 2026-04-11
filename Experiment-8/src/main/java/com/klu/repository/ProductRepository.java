package com.klu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.klu.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // Derived Query Methods

    List<Product> findByCategory(String category);

    List<Product> findByPriceBetween(double min, double max);


    // JPQL Query - sort by price
    @Query("SELECT p FROM Product p ORDER BY p.price ASC")
    List<Product> findAllSortedByPrice();


    // JPQL Query - expensive products
    @Query("SELECT p FROM Product p WHERE p.price > ?1")
    List<Product> findExpensiveProducts(double price);


    // JPQL Query - category search
    @Query("SELECT p FROM Product p WHERE p.category = ?1")
    List<Product> findProductsByCategory(String category);

}