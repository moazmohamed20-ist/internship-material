package com.example.products_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.products_api.entity.Product;
import com.example.products_api.repository.ProductsRepo;

@RestController
public class ProductsController {

  @Autowired
  private ProductsRepo productsRepo;

  @GetMapping("/products")
  public List<Product> getAll() {
    return productsRepo.findAll();
  }

  @PostMapping("/products")
  public ResponseEntity<Void> addNewProduct(@RequestBody Product p) {
    // Add new product to the list
    productsRepo.save(p);
    return ResponseEntity.status(201).build();
  }

  @GetMapping("/products/{id}")
  public ResponseEntity<Product> getById(@PathVariable int id) {
    return ResponseEntity.of(productsRepo.findById(id));
  }

  @DeleteMapping("/products/{id}")
  public ResponseEntity<Void> deleteById(@PathVariable int id) {
    Product p = productsRepo.findById(id).orElse(null);

    if (p != null) {
      productsRepo.deleteById(id);
      return ResponseEntity.noContent().build();
    }

    // Product not found
    return ResponseEntity.status(404).build();
  }

  @PutMapping("/products/{id}")
  public ResponseEntity<Void> updateById(@PathVariable int id, @RequestBody Product newProduct) {
    Product p = productsRepo.findById(id).orElse(null);

    if (p != null) {
      p.setName(newProduct.getName());
      p.setPrice(newProduct.getPrice());
      productsRepo.save(p);
      return ResponseEntity.ok().build();
    }

    // Product not found
    return ResponseEntity.status(404).build();
  }

}
