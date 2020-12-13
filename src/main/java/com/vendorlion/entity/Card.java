package com.vendorlion.entity;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "card")
@Where(clause = "ISNULL(is_deleted) OR is_deleted = 0")
@SQLDelete(sql = "UPDATE card SET is_deleted = 1 WHERE id = ?")
public class Card {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private long id;

  @Column(name = "balance", nullable = false)
  private long balance;

  @ManyToOne(targetEntity = Currency.class, fetch = FetchType.EAGER)
  @JoinColumn(name = "currency_id", nullable = false)
  private Currency currency;

  @Column(name = "activation_date", nullable = false)
  private Date activationDate;

  @Column(name = "expire_date", nullable = false)
  private Date expireDate;

  @Column(name = "reference", nullable = false)
  private String reference;

  @Column(name = "card_number", nullable = false)
  private String cardNumber;

  @Column(name = "cvc", nullable = false)
  private String cvc;

  @Column(name = "active", nullable = false)
  private boolean active = true;

  @Column(name = "is_deleted")
  private boolean isDeleted = false;

  public long getId() {
    return id;
  }

  public Card setId(long id) {
    this.id = id;
    return this;
  }

  public long getBalance() {
    return balance;
  }

  public Card setBalance(long balance) {
    this.balance = balance;
    return this;
  }

  public Currency getCurrency() {
    return currency;
  }

  public Card setCurrency(Currency currency) {
    this.currency = currency;
    return this;
  }

  public Date getActivationDate() {
    return activationDate;
  }

  public Card setActivationDate(Date activationDate) {
    this.activationDate = activationDate;
    return this;
  }

  public Date getExpireDate() {
    return expireDate;
  }

  public Card setExpireDate(Date expireDate) {
    this.expireDate = expireDate;
    return this;
  }

  public String getReference() {
    return reference;
  }

  public Card setReference(String reference) {
    this.reference = reference;
    return this;
  }

  public String getCardNumber() {
    return cardNumber;
  }

  public Card setCardNumber(String cardNumber) {
    this.cardNumber = cardNumber;
    return this;
  }

  public String getCvc() {
    return cvc;
  }

  public Card setCvc(String cvc) {
    this.cvc = cvc;
    return this;
  }

  public boolean isActive() {
    return active;
  }

  public Card setActive(boolean active) {
    this.active = active;
    return this;
  }

  public boolean isDeleted() {
    return isDeleted;
  }

  public Card setDeleted(boolean deleted) {
    isDeleted = deleted;
    return this;
  }
}
