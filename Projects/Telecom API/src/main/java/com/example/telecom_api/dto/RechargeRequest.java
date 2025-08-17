package com.example.telecom_api.dto;

import lombok.Data;

@Data
public class RechargeRequest {

  private String phoneNumber;
  private String cardNumber;
  private String expiryDate;
  private String securityCode;
  private Double amount;

}
