package com.example.telecom_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse {

  private boolean success;
  private String message;

}
