package com.example.pizza_shop_api;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Order {

  private String number;
  private LocalDateTime createdAt;

}