package com.example.products_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.products_api.entity.Product;

public interface ProductsRepo extends JpaRepository<Product, Integer> {
  // Keep It Empty
}
