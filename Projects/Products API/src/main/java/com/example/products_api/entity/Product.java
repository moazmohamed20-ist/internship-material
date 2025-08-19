package com.example.products_api.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Product {

  @Id
  @GeneratedValue
  private int id;
  private String name;
  private double price;

}