package com.vendorlion.library;

import java.util.Random;

public class CvcNumberGenerator {

    private Random random = new Random(System.currentTimeMillis());

    public int generate() {
        // 900 means = 999 - 100 + 1 means = generate number between 100 and 999
        return this.random.nextInt(900);
    }
}
