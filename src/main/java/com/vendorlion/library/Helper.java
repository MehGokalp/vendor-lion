package com.vendorlion.library;

import java.util.UUID;

public class Helper {
  private final CreditCardNumberGenerator creditCardNumberGenerator =
      new CreditCardNumberGenerator();
  private final CvcNumberGenerator cvcNumberGenerator = new CvcNumberGenerator();

  public String generateCardNumber() {
    return this.creditCardNumberGenerator.generate("4948", 16);
  }

  public String generateCvc() {
    return Integer.toString(this.cvcNumberGenerator.generate());
  }

  public String generateReference() {
    return UUID.randomUUID().toString();
  }
}
