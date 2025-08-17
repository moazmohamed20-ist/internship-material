package com.example.telecom_api.dto;

import lombok.Data;

@Data
public class TransferRequest {

  private String fromNumber;
  private String toNumber;
  private Double amount;

}
