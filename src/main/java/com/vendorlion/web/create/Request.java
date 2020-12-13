package com.vendorlion.web.create;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Request {
  private long balance;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date activationDate;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date expireDate;

  private String currency;

  public long getBalance() {
    return balance;
  }

  public Request setBalance(long balance) {
    this.balance = balance;
    return this;
  }

  public Date getActivationDate() {
    return activationDate;
  }

  public Request setActivationDate(Date activationDate) {
    this.activationDate = activationDate;
    return this;
  }

  public Date getExpireDate() {
    return expireDate;
  }

  public Request setExpireDate(Date expireDate) {
    this.expireDate = expireDate;
    return this;
  }

  public String getCurrency() {
    return currency;
  }

  public Request setCurrency(String currency) {
    this.currency = currency;
    return this;
  }
}
