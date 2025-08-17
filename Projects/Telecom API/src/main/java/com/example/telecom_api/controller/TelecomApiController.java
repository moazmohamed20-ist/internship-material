package com.example.telecom_api.controller;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.telecom_api.dto.ApiResponse;
import com.example.telecom_api.dto.RechargeRequest;
import com.example.telecom_api.dto.SmsRequest;
import com.example.telecom_api.dto.TransferRequest;
import com.example.telecom_api.dto.VipStatusResponse;

@RestController
public class TelecomApiController {

  @GetMapping("/customer/{phoneNumber}/vip-status")
  public ResponseEntity<VipStatusResponse> checkVipStatus(@PathVariable String phoneNumber) {
    boolean isVip = isVipCustomer(phoneNumber);
    return ResponseEntity.ok(new VipStatusResponse(isVip));
  }

  @PostMapping("/balance/recharge")
  public ResponseEntity<ApiResponse> rechargeBalance(@RequestBody RechargeRequest request) {
    // Validate amount
    if (request.getAmount() == null || request.getAmount() > 1000)
      return ResponseEntity.badRequest().body(new ApiResponse(false, "Invalid data received"));

    // Validate expiry date
    if (!isValidExpiryDate(request.getExpiryDate()))
      return ResponseEntity.badRequest().body(new ApiResponse(false, "Invalid data received"));

    // If all validations pass
    return ResponseEntity.ok(new ApiResponse(true, "Balance has been recharged successfully"));
  }

  @PostMapping("/balance/transfer")
  public ResponseEntity<ApiResponse> transferBalance(@RequestBody TransferRequest request) {
    // Validate amount
    if (request.getAmount() == null || request.getAmount() > 1000)
      return ResponseEntity.badRequest().body(new ApiResponse(false, "Invalid data received"));

    // Validate phone numbers
    if (!isValidTransfer(request.getFromNumber(), request.getToNumber()))
      return ResponseEntity.badRequest().body(new ApiResponse(false, "Invalid data received"));

    // If all validations pass
    return ResponseEntity.ok(new ApiResponse(true, "Balance has been transferred successfully"));
  }

  @PostMapping("/sms")
  public ResponseEntity<ApiResponse> sendSms(@RequestBody SmsRequest request) {
    if (!Arrays.asList("INTERNET_PACKAGES", "CALL_TONES", "PROMOTIONS").contains(request.getTemplateCode()))
      return ResponseEntity.badRequest().body(new ApiResponse(false, "Invalid template code"));

    return ResponseEntity.ok(new ApiResponse(true, "The SMS has been sent successfully."));
  }

  // region Helper methods

  /**
   * Check if a customer is VIP by calculating if the sum of phone number digits
   * is prime
   */
  private boolean isVipCustomer(String phoneNumber) {
    int digitSum = phoneNumber.chars()
        .filter(Character::isDigit)
        .map(c -> c - '0')
        .sum();

    return isPrime(digitSum);
  }

  /**
   * Check if a number is prime
   */
  private boolean isPrime(int number) {
    if (number <= 1)
      return false;
    if (number <= 3)
      return true;
    if (number % 2 == 0 || number % 3 == 0)
      return false;

    for (int i = 5; i * i <= number; i += 6)
      if (number % i == 0 || number % (i + 2) == 0)
        return false;

    return true;
  }

  /**
   * Validate expiry date is in the future
   * Expected format: MM/yy
   */
  private boolean isValidExpiryDate(String expiryDate) {
    if (expiryDate == null || expiryDate.length() != 5)
      return false;

    try {
      String[] parts = expiryDate.split("/");

      if (parts.length != 2)
        return false;

      int month = Integer.parseInt(parts[0]);
      int year = Integer.parseInt("20" + parts[1]); // Convert yy to yyyy

      // Create date for the last day of the expiry month
      LocalDate expiryDateParsed = LocalDate.of(year, month, 1)
          .plusMonths(1)
          .minusDays(1);

      return expiryDateParsed.isAfter(LocalDate.now());

    } catch (DateTimeParseException | NumberFormatException e) {
      return false;
    }
  }

  /**
   * Validate balance transfer conditions:
   * - toNumber must start with same 3 digits as fromNumber
   * - toNumber must be same length as fromNumber
   */
  private boolean isValidTransfer(String fromNumber, String toNumber) {
    if (fromNumber == null || toNumber == null)
      return false;

    // Check same length
    if (fromNumber.length() != toNumber.length())
      return false;

    // Check same first 3 digits
    if (fromNumber.length() < 3 || toNumber.length() < 3)
      return false;

    return fromNumber.substring(0, 3).equals(toNumber.substring(0, 3));
  }

  // endregion

}
