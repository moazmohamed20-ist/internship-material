package com.example.pizza_shop_api;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class Controller {

  private final Map<String, Order> orders = new ConcurrentHashMap<>();

  private final Random random = new Random();

  @GetMapping("/check-status")
  public ResponseEntity<String> checkStatus() {
    LocalTime now = LocalTime.now();
    LocalTime open = LocalTime.of(8, 0);
    LocalTime close = LocalTime.of(13, 0);

    boolean isOpen = now.isAfter(open) && now.isBefore(close);
    return ResponseEntity.ok(isOpen ? "open" : "closed");
  }

  @PostMapping("/order")
  public ResponseEntity<OrderResponse> createOrder() {
    Order order = new Order();
    order.setCreatedAt(LocalDateTime.now());
    order.setNumber(generateOrderNumber());
  orders.put(order.getNumber(), order);

    OrderResponse response = new OrderResponse();
    response.setOrderNumber(order.getNumber());

    log.info("New Order With Number: {}", response.getOrderNumber());
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @GetMapping("/order/{orderNumber}")
  public ResponseEntity<TrackResponse> trackOrder(@PathVariable String orderNumber) {
  Order found = orders.get(orderNumber);

    if (found == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    LocalDateTime readyAt = found.getCreatedAt().plusMinutes(20);
    long minutesLeft = LocalDateTime.now().until(readyAt, ChronoUnit.MINUTES);

    TrackResponse response = new TrackResponse();
    if (minutesLeft > 0)
      response.setTimeLeft(minutesLeft);

    log.info("Tracking Order: {} - Time Left: {} minutes", orderNumber, minutesLeft);
    return ResponseEntity.ok(response);
  }

  private String generateOrderNumber() {
    String candidate;
    int attempts = 0;
    do {
      candidate = String.valueOf(random.nextInt(9000) + 1000); // 4-digit number
      attempts++;
    } while (isOrderExist(candidate) && attempts < 100);
    return candidate;
  }

  private boolean isOrderExist(String number) {
  return orders.containsKey(number);
  }

}
