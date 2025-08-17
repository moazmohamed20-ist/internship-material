package com.example.telecom_api.dto;

import lombok.Data;

@Data
public class SmsRequest {

  private String phoneNumber;
  private String templateCode;

}
