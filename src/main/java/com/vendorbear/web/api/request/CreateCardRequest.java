package com.vendorbear.web.api.request;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class CreateCardRequest {
    private long balance;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date activationDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date expireDate;
    private String currency;

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public Date getActivationDate() {
        return activationDate;
    }

    public void setActivationDate(Date activationDate) {
        this.activationDate = activationDate;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
