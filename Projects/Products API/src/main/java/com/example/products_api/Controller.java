package com.example.products_api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

  private List<Product> products = new ArrayList<>();

  @GetMapping("/products")
  public List<Product> getAll() {
    return products;
  }

  @PostMapping("/products")
  public ResponseEntity<Void> addNewProduct(@RequestBody Product p) {
    // Add new product to the list
    products.add(p);
    return ResponseEntity.status(201).build();
  }

  @GetMapping("/products/{id}")
  public ResponseEntity<Product> getById(@PathVariable int id) {
    // Find product by id, then return it
    for (int i = 0; i < products.size(); i++) {
      Product p = products.get(i);
      if (p.getId() == id)
        return ResponseEntity.ok(p);
    }

    // Product not found
    return ResponseEntity.status(404).build();
  }

  @DeleteMapping("/products/{id}")
  public ResponseEntity<Void> deleteById(@PathVariable int id) {
    // Find product by id, then delete it
    for (int i = 0; i < products.size(); i++) {
      Product p = products.get(i);
      if (p.getId() == id) {
        products.remove(i);
        return ResponseEntity.status(204).build();
      }
    }

    // Product not found
    return ResponseEntity.status(404).build();
  }

  @PutMapping("/products/{id}")
  public ResponseEntity<Void> updateById(@PathVariable int id, @RequestBody Product newProduct) {
    // Find product by id, then update it
    for (int i = 0; i < products.size(); i++) {
      Product p = products.get(i);
      if (p.getId() == id) {

        p.setName(newProduct.getName());
        p.setPrice(newProduct.getPrice());

        return ResponseEntity.ok().build();
      }
    }

    // Product not found
    return ResponseEntity.status(404).build();
  }

}
