package com.vendorlion.library;

import java.util.concurrent.ThreadLocalRandom;

public class CvcNumberGenerator {
  public int generate() {
    // generate a number between 100 and 999
    return ThreadLocalRandom.current().nextInt(100, 999 + 1);
  }
}
