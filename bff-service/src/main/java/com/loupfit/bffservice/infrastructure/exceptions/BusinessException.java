package com.loupfit.bffservice.infrastructure.exceptions;

public class BusinessException extends RuntimeException {
  public BusinessException(String message) {
    super(message);
  }
}
